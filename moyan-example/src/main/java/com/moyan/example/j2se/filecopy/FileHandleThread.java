package com.moyan.example.j2se.filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import com.moyan.example.j2se.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * single file handle thread
 * @author Lenovo
 *
 */
public class FileHandleThread extends Thread{
	
	static Log log = LogFactory.getLog("FileHandleThread");
	private File inputFile = null;
	private File outputFile = null;
	private InputStream in = null;
	private OutputStream out = null;
	private boolean needDelete = false;
	private TimeCounter counter = TimeCounter.getInstance();
	
	public FileHandleThread() {
		super();
	}

	public FileHandleThread(File inFile,File outFile,boolean needDeleted) throws FileNotFoundException {
		
		this.in = new FileInputStream(inFile);
		if(!outFile.exists() && !outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}
		this.out = new FileOutputStream(outFile);
		this.inputFile = inFile;
		this.outputFile = outFile;
		this.needDelete = needDeleted;
	}
	
	
	@Override
	public void run() {

		printLog("starting");
		long startTime = System.currentTimeMillis();
		int length = 0;
		byte[] data = new byte[10000];
		final long size = inputFile.length();
		log.info(Thread.currentThread().getName()
				+"the file [" +inputFile.getName()+"] size : " +size
				+ "b");
		//时间计数器
		FileProcessingInfo processingInfo = new FileProcessingInfo();
		processingInfo.setFile(inputFile);
		processingInfo.setSize(size);
		processingInfo.setHasReadSize(0);
		Timer timer = new Timer(Thread.currentThread().getName() + "-" + inputFile.getName() + " timer");
		addTimeTask(timer, processingInfo);
		try {
			while(true) {
//				in = new BufferedInputStream(in);
				length = in.read(data);
				if(length<0) {
					break;
				}
				out.write(data, 0, length);
				processingInfo.setHasReadSize(processingInfo.getHasReadSize()+length);
			}
			close();
			timer.cancel();
			printLog("finished");
			delete(inputFile,needDelete);
			long endTime = System.currentTimeMillis();
			log.info(Thread.currentThread().getName()
					+" cost :" + (endTime - startTime)/1000
					+ " s");
			counter.addCostTime((endTime - startTime)/1000);
			counter.addFileSize(size);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(File file,boolean isDelete) {
		
		if(!isDelete) {
			log.info(Thread.currentThread().getName()+
					" no need to delete the file\n " +
					file.getAbsolutePath());
			return;
		}
		
		if(file.delete()) {
			log.info(Thread.currentThread().getName()+
					" successful to delete the file \n" +
					file.getAbsolutePath());
		}
		else {
			log.info(Thread.currentThread().getName()+
					" failed to delete the file\n " +
					file.getAbsolutePath());
		}
	}
	
	public void  printLog(String operate) {
		StringBuilder builder = new StringBuilder();
		builder.append(Thread.currentThread().getName() + " ")
			.append(operate)
			.append(" to  copy the file [ ")
			.append(inputFile.getAbsolutePath())
			.append(" ]\n to the dest [ ")
			.append(outputFile.getAbsolutePath())
			.append(" ] \nin time ")
			.append(DateUtil.getCurDate());
		log.info(builder.toString());
		
	}
	public void addTimeTask(Timer timer,final FileProcessingInfo processingInfo) {
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(in == null) {
					cancel();
					log.info(Thread.currentThread().getName()
							+" finished 100% and cance the timetask.");
					return;
				}
				log.info(Thread.currentThread().getName()
						+" finished : " +  processingInfo.getSize() + "b," + processingInfo.getHasReadSize()+" b, "
						+processingInfo.getPercent() * 100 + "%");
				
			}
		}, 0 , 20000);
	}
	
	public void close() throws IOException {
		out.flush();
		out.close();
		in = null;
		out = null;
	}
}

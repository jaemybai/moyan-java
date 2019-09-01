package com.moyan.example.j2se.filecopy;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.moyan.example.j2se.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Lenovo
 *
 */
public class ThreadMain {

	static Log log = LogFactory.getLog("ThreadMain");

	public static void main(String[] args) throws IOException {
		
		PropertyConfigurator.configure("src/log4j.properties");
		log.info(Thread.currentThread().getName()+
				" start to run fileCopy Program in "+
				DateUtil.getCurDate());
		final long startTime = System.currentTimeMillis();
		String srcDir = "G:\\Program Files\\download\\xunlei7";
		String desDir = "F:\\电影大全\\古惑仔";
//		String desDir = "G:\\Program Files\\download\\xunlei";
//		String srcDir = "F:\\电影大全\\古惑仔";
		final boolean onlyOne = true;
		final boolean needDelete = false;
		File srcFile = new File(srcDir);
		File desFile = null;
		final File[] allHandlingFile = srcFile.listFiles();
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for(File oneFile : allHandlingFile) {
			
			desFile = new File(desDir, oneFile.getName());
			if(desFile.exists()) {
				log.info(Thread.currentThread().getName()
						+"--the file["
						+desFile.getAbsolutePath()
						+" exists ,\n no need to copy.");
				continue;
			}
			executorService.execute(new FileHandleThread(oneFile, desFile, needDelete));
			if(onlyOne) {
				break;
			}
		}
		executorService.shutdown();
		Runtime.getRuntime().addShutdownHook(new Thread("addShutdownHook"){
			
			@Override
			public void run() {
				long endTime = System.currentTimeMillis();
				long totalCost = endTime - startTime;
				log.info(Thread.currentThread().getName()+
						" end to run fileCopy Program in "+
						DateUtil.getCurDate());
				if(onlyOne) {
					log.info(Thread.currentThread().getName()
							+"copy one file,size: " +TimeCounter.getInstance().getFileSize()
							+"costs:" + totalCost/1000
							+ " s");
				} 
				else {
					log.info(Thread.currentThread().getName()
							+"copy[ "+allHandlingFile.length + " ]file,size: " +TimeCounter.getInstance().getFileSize()
							+"costs:" 	+ totalCost/1000
							+ " s");
				}
			}
		});
	}
}

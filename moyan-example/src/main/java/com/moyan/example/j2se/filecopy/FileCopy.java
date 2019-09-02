package com.moyan.example.j2se.filecopy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 使用阻塞队列进行文件复制
 * @author Lenovo
 *
 */
public class FileCopy {

	private static Logger logger = LoggerFactory.getLogger(FileCopy.class);

	public static BlockingQueue<byte[]> queue = new ArrayBlockingQueue<byte[]>(10000);
	public static void main(String[] args) {

		ExecutorService service = new ThreadPoolExecutor(5, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
		logger.info("start..");
		service.execute(new ReceiveThread());
		service.execute(new SendThread());
		service.shutdown();
	}
	
	
	static class ReceiveThread extends Thread {
		@Override
		public void run() {
			try {	
				byte[] bb;
				File file = null;
				 bb = queue.take();
				 if(bb != null) {
					 file = new File("copy_"+new String(bb));
				 }
				OutputStream out = new FileOutputStream(file);
				int i=0;
				logger.info("start the ReceiveThread...");
				while(true) {
					 bb = queue.poll();
					 if(bb != null) {
//						 logger.info("ReceiveThread----"+Arrays.toString(bb));
						 logger.info("ReceiveThread----"+new String(bb));
						 out.write(bb);
					 }else {
						 if(i>3) {
							 logger.info("" + i);
							 break;
						 }
						 Thread.sleep(2000);
						 i++;
					 }
					 
				}
				out.flush();
				out.close();
				logger.info("end the ReceiveThread...");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}
		}
	}
	static class SendThread extends Thread {

		public static String filePath = "QQ图片20140903220952.jpg";
		
		@Override
		public void run() {
			try {	
				File file = new File(filePath);
				InputStream in = new FileInputStream(file);
				logger.info("start the SendThread...");
				queue.put(filePath.getBytes());
				while(true) {
//					logger.info("SendThread----"+Arrays.toString(bb));
//					logger.info("SendThread----"+new String(bb));
					byte[] bb = new byte[100];
					if(in.read(bb) != -1 ) {
						queue.put(bb);
					} else {
						break;
					}
					
				}
				in.close();
				logger.info("end the SendThread...");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}
		}
		
		
	}

	static void test() {
	}

	static void test2() {
		Timer time = new Timer();
		logger.info("" + 1);
		
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				logger.info("" + 1);

			}
		}, 2);
		logger.info("" + 2);
		time.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				String str = new SimpleDateFormat("yyyyMMdd hh:mm:ss")
						.format(new Date(System.currentTimeMillis()));
				logger.info(str);

			}
		}, 1000, 5000);
	}

	static void test1() {
		logger.info("start...");
		String str = new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(new Date(
				System.currentTimeMillis()));
		for (long i = 0; i < 5000L; i++) {
			logger.info(i
					+ "\t"
					+ new SimpleDateFormat("yyyyMMdd hh:mm:ss")
							.format(new Date(System.currentTimeMillis())));
		}
		logger.info("end...");
		logger.info(str);
		logger.info(new SimpleDateFormat("yyyyMMdd hh:mm:ss")
				.format(new Date(System.currentTimeMillis())));
	}
}

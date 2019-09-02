package com.moyan.example.j2se.filecopy;


/**
 * 计数器单例模式
 * @author Lenovo
 *
 */
public class TimeCounter {

	private long costTime = 0;
	private long fileSize = 0;
	private static TimeCounter counter = null;
	
	private TimeCounter() {
		
	}

	public long getFileSize() {
		synchronized (this) {
			return fileSize;
		}
	}

	public void addFileSize(long fileSize) {
		synchronized (this) {
			this.fileSize = getFileSize() + fileSize;
		}
	}

	public static TimeCounter getInstance() {
	
		synchronized (TimeCounter.class) {
			if(counter == null) {
				counter = new TimeCounter();
			}
		}
		return counter;
		
	}
	public long getCostTime() {
		
//		logger.info("get");
		synchronized (this) {
//			try {
//				logger.info("getbefore");
//				Thread.sleep(5000);
//				logger.info("getafter");
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				logger.error(e.getMessage(),e)
//			}
			return costTime;
		}
	}

	public void addCostTime(long costTime) {
		
//		logger.info("add");
		synchronized (this) {
//			try {
//				logger.info("addbefore");
//				Thread.sleep(5000);
//				logger.info("addafter");
//
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				logger.error(e.getMessage(),e)
//			}
			this.costTime = getCostTime() + costTime;
		}
	}
	
	
	public static void main(String[] args) {
		
		final TimeCounter counter = TimeCounter.getInstance();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				counter.addCostTime(2000);
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				counter.getCostTime();
			}
		}).start();
	}
}

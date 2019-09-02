package com.moyan.example.j2se.thread.concurrent;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.moyan.example.j2se.base.ViolateTest;
import com.sun.org.apache.xml.internal.utils.ObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutorsTest {
	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

    static Operation operation = new Operation();
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
//		
		
		operation.setOperation("main intially....");
		Future<?> future = null;
//		executorService.execute(getRunnable());
//		future = executorService.submit(getRunnable(),operation);
		future = executorService.submit(getCallable());
		logger.info("wait1.....");
//		logger.info(future.get());
		logger.info("wait2.....");
		executorService.shutdown();
	}
	
	static Runnable getRunnable() {
		Runnable runnable = new Runnable() {
			
			int count = 1;
			@Override
			public void run() {
				logger.info(count + "test...");
				operation.setOperation("execute getRunnable....");
				count++;
				
			}
		};
		return runnable;
	}
	
	static Callable<Operation> getCallable() {
		Callable<Operation> callable = new Callable<Operation>() {

			int count = 0;
			@Override
			public Operation call() throws Exception {
				logger.info(count + "test.....");
				operation.setOperation("execute getCallable....");
				count++;
				Thread.sleep(2000);
				return operation;
			}
			
		};
		return callable;
	}
	static class Operation {
		
		@Override
		public String toString() {
			return "Operation [operation=" + operation + ", currentDate="
					+ currentDate + "]";
		}
		String operation = "";
		Date currentDate = null;
		public String getOperation() {
			return operation;
		}
		public void setOperation(String operation) {
			this.operation = operation;
		}
		public Date getCurrentDate() {
			return currentDate;
		}
		public void setCurrentDate(Date currentDate) {
			this.currentDate = currentDate;
		}
	}
}



package com.moyan.example.j2se.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {
    private Lock lock = new ReentrantLock();
	private Condition condition  = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	public void await1() {
		try {
			lock.lock();
			System.out.println("执行 await1 方法");
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("await1 锁释放了1");
			lock.unlock();
			System.out.println("await1 锁释放了2");
		}
	}
	
	public void await2() {
		try {
			lock.lock();
			System.out.println("执行 await2 方法");
			condition2.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("await2锁释放了1");
			lock.unlock();
			System.out.println("await2锁释放了2");
		}
	}
	
	public void signal1() {
		try {
			lock.lock();
			System.out.println("执行signal1方法");
			condition.signal();
			System.out.println("通知了await1 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("signal1方法结束1");
			lock.unlock();
			System.out.println("signal1方法结束2");
		}
	}
	
	public void signal2() {
		try {
			lock.lock();
			System.out.println("执行signal2方法");
//			Thread.sleep(100000);
			condition2.signal();
			System.out.println("通知了await2 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("signal2方法结束1");
			lock.unlock();
			System.out.println("signal2方法结束2");
		}
	}

	public static void main(String[] args) {
		final LockConditionTest lockConditionTest = new LockConditionTest();
		Thread await1Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				lockConditionTest.await1();
			}
		});
		Thread await2Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				lockConditionTest.await2();
			}
		});
		Thread signal1Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				lockConditionTest.signal1();
			}
		});
		Thread signal2Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				lockConditionTest.signal2();
			}
		});

		await1Thread.start();
		await2Thread.start();
		signal1Thread.start();
		signal2Thread.start();
	}

}

package com.moyan.example.j2se.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSleepWaitTest {

    final Lock lock = new ReentrantLock();
    public  void sleep1() throws InterruptedException  {
        try {
            lock.lock();
            System.out.println("sleep1-start");
            Thread.sleep(5);
            System.out.println("sleep1-end");
        }finally {
            lock.unlock();
        }

    }

    public  void sleep2() throws InterruptedException  {
        try {
            lock.lock();
            System.out.println("sleep2-start");
            Thread.sleep(5);
            System.out.println("sleep2-end");
        }finally {
            lock.unlock();
        }

    }

    public  void wait1() throws InterruptedException  {
        try {
            lock.lock();
            System.out.println("wait1-start");
            lock.wait();
            System.out.println("wait1-end");
        }finally {
            lock.unlock();
        }

    }

    public  void wait2() throws InterruptedException  {
        try {
            lock.lock();
            System.out.println("wait2-start");
            lock.notify();
            System.out.println("wait2-end");
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws Exception{
        wait1(args);
//        sleep(args);
    }
    public static void wait1(String[] args) throws Exception{
        final LockSleepWaitTest syschronizeTest = new LockSleepWaitTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syschronizeTest.wait1();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syschronizeTest.wait2();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void sleep(String[] args) throws Exception{
        final LockSleepWaitTest syschronizeTest = new LockSleepWaitTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syschronizeTest.sleep1();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syschronizeTest.sleep2();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

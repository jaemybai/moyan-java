package com.moyan.example.j2se.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyschronizeSleppWaitTest {

    private static Logger logger = LoggerFactory.getLogger(SyschronizeSleppWaitTest.class);

    public synchronized void sleep1() throws InterruptedException  {
        logger.info("sleep1-start");
        Thread.sleep(5);
        logger.info("sleep1-end");
    }

    public synchronized void sleep2() throws InterruptedException  {
        logger.info("sleep2-start");
        Thread.sleep(5);
        logger.info("sleep2-end");
    }

    public synchronized void wait1() throws InterruptedException  {
        logger.info("wait1-start");
        this.wait();
        logger.info("wait1-end");
    }

    public synchronized void wait2() throws InterruptedException  {
        logger.info("wait2-start");
        this.notifyAll();
        logger.info("wait2-end");
    }

    public static void main(String[] args) throws Exception{
        wait1(args);
//        sleep(args);
    }
    public static void wait1(String[] args) throws Exception{
        final SyschronizeSleppWaitTest syschronizeTest = new SyschronizeSleppWaitTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syschronizeTest.wait1();
                }catch (Exception e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }).start();
        Thread.sleep(200);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syschronizeTest.wait2();
                }catch (Exception e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }).start();
    }

    public static void sleep(String[] args) throws Exception{
        final SyschronizeSleppWaitTest syschronizeTest = new SyschronizeSleppWaitTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    syschronizeTest.sleep1();
                }catch (Exception e) {
                    logger.error(e.getMessage(),e);
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
                    logger.error(e.getMessage(),e);
                }
            }
        }).start();
    }

}

package com.moyan.example.j2se.thread;

import com.moyan.example.base.AbstractOperateLogTest;

public class DamenThreadTest extends AbstractOperateLogTest {

    public static void main(String[] args) throws Exception{
        new DamenThreadTest().damenThreadTest();
    }

    public void damenThreadTest() throws Exception{
        Thread mainThread = new Thread(() -> {
            try {
                logger.info("start a main thread....");
                Thread.sleep(4000);
                logger.info("end a main thread....");
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        });

        Thread mainThread2 = new Thread(() -> {
            try {
                logger.info("start a main2 thread....");
                Thread.sleep(12000);
                logger.info("end a main2 thread....");
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        });

        Thread damonThread = new Thread(() -> {
            try {
                logger.info("start a damon Thread ....");
                Thread.sleep(10000);
                logger.info("end a damon thread....");
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        });


        mainThread.setDaemon(false);
        mainThread2.setDaemon(false);
        damonThread.setDaemon(true);
        mainThread.start();
        mainThread2.start();
        damonThread.start();
        Thread.sleep(1000);
        mainThread.setName("mainThread");
        mainThread2.setName("mainThread2");
        damonThread.setName("damonThread");
    }
}

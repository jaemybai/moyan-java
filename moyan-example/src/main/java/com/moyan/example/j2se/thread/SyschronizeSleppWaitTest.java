package com.moyan.example.j2se.thread;

public class SyschronizeSleppWaitTest {

    public synchronized void sleep1() throws InterruptedException  {
        System.out.println("sleep1-start");
        Thread.sleep(5);
        System.out.println("sleep1-end");
    }

    public synchronized void sleep2() throws InterruptedException  {
        System.out.println("sleep2-start");
        Thread.sleep(5);
        System.out.println("sleep2-end");
    }

    public synchronized void wait1() throws InterruptedException  {
        System.out.println("wait1-start");
        this.wait();
        System.out.println("wait1-end");
    }

    public synchronized void wait2() throws InterruptedException  {
        System.out.println("wait2-start");
        this.notifyAll();
        System.out.println("wait2-end");
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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

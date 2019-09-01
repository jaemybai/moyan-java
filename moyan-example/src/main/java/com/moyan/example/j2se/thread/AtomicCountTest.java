package com.moyan.example.j2se.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCountTest {
    public  AtomicInteger inc = new AtomicInteger(11);
     
    public  void increase() {
        inc.getAndIncrement();
    }
    
    public static void main(String[] args) {
        final AtomicCountTest test = new AtomicCountTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
        
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
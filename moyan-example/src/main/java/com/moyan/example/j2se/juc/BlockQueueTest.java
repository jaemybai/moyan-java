package com.moyan.example.j2se.juc;

import com.moyan.example.base.AbstractOperateLogTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueueTest<T> extends AbstractOperateLogTest   {

    public static void main(String[] args) throws Exception{
        BlockQueueTest blockQueueTest = new BlockQueueTest();
        blockQueueTest.test();
    }

    public void test() throws Exception {

        BlockQueueTest blockQueueTest = new BlockQueueTest();

//        blockQueueTest.put("atomic1");
//        blockQueueTest.put("atomic2");

        Thread takeThread = new Thread(() -> {
            logger.info("start take a element...");
            Object object = blockQueueTest.take();
            logger.info("take a element, obj:{}", object);
        });

        Thread takeThread2 = new Thread(() -> {
            logger.info("start take2 a element...");
            Object object = blockQueueTest.take();
            logger.info("take2 a element, obj:{}", object);
        });

        Thread takeThread3 = new Thread(() -> {
            logger.info("start take3 a element...");
            Object object = blockQueueTest.take();
            logger.info("take3 a element, obj:{}", object);
        });

        Thread putThread = new Thread(() -> {
            logger.info("start put a element...");
            blockQueueTest.put("put1");
            blockQueueTest.put("put2");
            blockQueueTest.put("put3");
            logger.info("put a element, obj:{}", "put1");
        });

        takeThread.setName("takeThread");
        takeThread2.setName("takeThread2");
        takeThread3.setName("takeThread3");
        putThread.setName("putThread");
        takeThread.start();
        takeThread2.start();
        takeThread3.start();
        Thread.sleep(2000);
//        putThread.start();
    }

    static class Node<T> {

        T item;
        Node<T> next;

        Node(T item) {
            this.item = item;
        }
    }

    private int capacity = 2;
    private AtomicInteger count = new AtomicInteger(0);
    private Node<T> head;
    private Node<T> last;
    Lock putLock = new ReentrantLock();
    Condition putCon = putLock.newCondition();
    Lock takeLock = new ReentrantLock();
    Condition takeCon = takeLock.newCondition();

    {
        last = head = new Node(null);
    }
    public void put(T t) {
        int countTem = -1;
        try {
            putLock.lock();
            while (count.get() == capacity) {
                putCon.await();
            }
            last = last.next = new Node<T>(t);
            countTem = count.getAndIncrement();
            putCon.signal();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            putLock.unlock();
        }
        if( countTem == 0) {
            try {
                takeLock.lock();
                takeCon.signal();
            } finally {
                takeLock.unlock();
            }
        }
    }

    public T take() {
        T t = null;
        int countTem = -1;
        try {
            takeLock.lock();
            while (count.get() ==0) {
                takeCon.await();
            }
            Node<T> first = head.next;
            t = first.item;
            head.next = null;
            first.item = null;
            head = first;
            countTem = count.getAndDecrement();
            takeCon.signal();
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            takeLock.unlock();
        }

        if( countTem == capacity) {
            try {
                putLock.lock();
                putCon.signal();
            }finally {
                putLock.unlock();
            }
        }
        return t;
    }

}

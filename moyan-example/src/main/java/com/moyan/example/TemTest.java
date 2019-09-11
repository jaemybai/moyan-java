package com.moyan.example;

import com.moyan.example.base.AbstractOperateLogTest;

import java.util.concurrent.ThreadPoolExecutor;

public class TemTest extends AbstractOperateLogTest {


    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
        new TemTest().test(null);
    }
    public  void test(String[] args) {
        ThreadPoolExecutor threadPoolExecutor;
        logger.info("COUNT_BITS:{}", COUNT_BITS);
        logger.info("CAPACITY:{}", CAPACITY);
        logger.info("RUNNING:{}", RUNNING);
        logger.info("SHUTDOWN:{}", SHUTDOWN);
        logger.info("STOP:{}", STOP);
        logger.info("TIDYING:{}", TIDYING);
        logger.info("TERMINATED:{}", TERMINATED);

    }
}

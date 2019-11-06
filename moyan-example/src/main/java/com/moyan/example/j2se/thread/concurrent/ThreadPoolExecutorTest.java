package com.moyan.example.j2se.thread.concurrent;

import com.moyan.example.AbstractOperateLogTest;

public class ThreadPoolExecutorTest extends AbstractOperateLogTest {

    int COUNT_BITS = Integer.SIZE - 3;
    int CAPACITY   = (1 << COUNT_BITS) - 1;
    int RUNNING    = -1 << COUNT_BITS;
    int SHUTDOWN   =  0 << COUNT_BITS;
    int STOP       =  1 << COUNT_BITS;
    int TIDYING    =  2 << COUNT_BITS;
    int TERMINATED =  3 << COUNT_BITS;

    public void test(String[] args) {

        logger.info("COUNT_BITS : {} ", COUNT_BITS);
        logger.info("CAPACITY : {}, byte: {} ", CAPACITY, Integer.toBinaryString(CAPACITY));
        int tem = (~CAPACITY);
        logger.info("~CAPACITY : {}, byte:{} ", tem, Integer.toBinaryString(tem) );
        logger.info("536870912: {}", Integer.toBinaryString(536870912));
        logger.info("-536870912: {}", Integer.toBinaryString(-536870912));

        logger.info("RUNNING : {} ", RUNNING);
        logger.info("SHUTDOWN : {} ", SHUTDOWN);
        logger.info("STOP : {} ", STOP);
        logger.info("TIDYING : {} ", TIDYING);
        logger.info("TERMINATED : {} ", TERMINATED);
        int ctl = ctlOf(TERMINATED, 4);
        logger.info("ctlOf(RUNNING,0): {}", ctl );
        logger.info("runStateOf(c): {}", runStateOf(ctl) );
        logger.info("workerCountOf(c): {}", workerCountOf(ctl) );
        logger.info("c : {}" , 9 & -1);
        logger.info("c : {}" , 9 & 1);

    }

    private  int ctlOf(int rs, int wc) { return rs | wc; }

    private  int runStateOf(int c)     { return c & ~CAPACITY; }
    private  int workerCountOf(int c)  { return c & CAPACITY; }

    public static void main(String[] args) {
        new ThreadPoolExecutorTest().test(args);
    }
}

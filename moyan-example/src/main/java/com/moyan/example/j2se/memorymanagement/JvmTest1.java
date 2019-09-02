package com.moyan.example.j2se.memorymanagement;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static com.sun.deploy.util.BufferUtil.MB;

public class JvmTest1 {
    private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

    static final int mb = 1024 * 1024 * 1;
    /**
     *  -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5;
        allocation1 = new byte[2 * mb ];
        logger.info("" + 1);
        allocation2 = new byte[2 * mb ];
        logger.info("" + 2);
        allocation3 = new byte[3 * mb ];
        logger.info("" + 3);
        allocation4 = new byte[3 * mb ];
        logger.info("" + 4);
        allocation5 = new byte[2 * mb ];
        logger.info("" + 5);
        int i=0;
        while (true) {
            if(i>10) {
                break;
            }
            i++;
            logger.info(Integer.toString(i)  + "\t" + new Date().toString());
            Thread.sleep(1*1000*60);
        }

    }
}

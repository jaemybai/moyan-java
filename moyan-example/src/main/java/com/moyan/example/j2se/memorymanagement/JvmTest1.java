package com.moyan.example.j2se.memorymanagement;

import java.util.Date;

import static com.sun.deploy.util.BufferUtil.MB;

public class JvmTest1 {

    static final int mb = 1024 * 1024 * 1;
    /**
     *  -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5;
        allocation1 = new byte[2 * mb ];
        System.out.println(1);
        allocation2 = new byte[2 * mb ];
        System.out.println(2);
        allocation3 = new byte[3 * mb ];
        System.out.println(3);
        allocation4 = new byte[3 * mb ];
        System.out.println(4);
        allocation5 = new byte[2 * mb ];
        System.out.println(5);
        int i=0;
        while (true) {
            if(i>10) {
                break;
            }
            i++;
            System.out.println(Integer.toString(i)  + "\t" + new Date().toString());
            Thread.sleep(1*1000*60);
        }

    }
}

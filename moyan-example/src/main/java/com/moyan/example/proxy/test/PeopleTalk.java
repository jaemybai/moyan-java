package com.moyan.example.proxy.test;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务类
 * 
 * @author yanbin
 * 
 */
public class PeopleTalk {

    private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

    public void talk(String msg) {
        logger.info("people talk" + msg);
    }

}
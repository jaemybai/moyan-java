package com.moyan.example.j2se;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TemTest {

    private static Logger logger = LoggerFactory.getLogger(TemTest.class);

    public static void main(String[] args) {
        int HASH_INCREMENT = 0x61c88647;
        logger.info("" + HASH_INCREMENT);
        AtomicInteger nextHashCode =new AtomicInteger();
        logger.info("" + nextHashCode.getAndAdd(HASH_INCREMENT));
        logger.info("" + nextHashCode.getAndAdd(HASH_INCREMENT));

        LinkedHashMap<Object,Object> map = new LinkedHashMap();
//        for(int i=0;i<10;i++) {
//            map.put(i,i);
//        }
        map.put(1,1);
        map.put(5,5);
        map.put(3,3);
        map.put("d","d");
        map.put("t","t");
        map.put("a","a");
        logger.info(map.toString());
        for (Map.Entry entry : map.entrySet()) {
            logger.info(entry.getKey() + ":" + entry.getValue());
        }

        HashMap<Object,Object> map2 = new HashMap<>();
//        for(int i=0;i<10;i++) {
//            map.put(i,i);
//        }
        map2.put(1,1);
        map2.put(5,5);
        map2.put(3,3);
        map2.put("d","d");
        map2.put("t","t");
        map2.put("a","a");
        logger.info(map2.toString());
        for (Map.Entry entry : map2.entrySet()) {
            logger.info(entry.getKey() + ":" + entry.getValue());
        }
    }
}

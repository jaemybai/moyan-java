package com.moyan.example.j2se;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TemTest {

    public static void main(String[] args) {
        int HASH_INCREMENT = 0x61c88647;
        System.out.println(HASH_INCREMENT);
        AtomicInteger nextHashCode =new AtomicInteger();
        System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));
        System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));

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
        System.out.println(map.toString());
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
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
        System.out.println(map2.toString());
        for (Map.Entry entry : map2.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}

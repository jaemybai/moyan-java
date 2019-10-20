package com.moyan.example.jdk8.stream;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {

        test();
    }

    public static void test() {
        List<StreamObj> streamObjList = new ArrayList<>();
        streamObjList.add(new StreamObj(1));
        streamObjList.add(new StreamObj(2));
        streamObjList = streamObjList.stream().filter(obj -> {return obj.getValue() == 1;}).collect(Collectors.toList());
        System.out.println(JSON.toJSON(streamObjList));
        Integer integer = streamObjList.stream().map(StreamObj::getValue).reduce(Integer::sum).orElse(0);
        System.out.println(integer);
    }
    public static void test1() {
        ReentrantLock reentrantLock;
        List<StreamObj> streamObjList = new ArrayList<>();
        streamObjList.add(new StreamObj(1));
        streamObjList.add(new StreamObj(2));
        streamObjList.add(new StreamObj(null));
//        streamObjList = streamObjList.stream().filter(obj -> {return obj.getValue() != null;}).collect(Collectors.toList());
        System.out.println(JSON.toJSON(streamObjList));
        Integer integer = streamObjList.stream().map(StreamObj::getValue).reduce(Integer::sum).get();
        System.out.println(integer);

    }



    private static class StreamObj {
        Integer value;

        public StreamObj(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}

package com.moyan.example.j2se.proxy.cglib;
/**
 * 测试类
 * 
 * @author yanbin
 * 
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        PeopleTalk peopleTalk = (PeopleTalk) new CglibProxy().getInstance(new PeopleTalk());
        peopleTalk.talk("业务方法");
//        peopleTalk.spreak("业务方法");
    }

}
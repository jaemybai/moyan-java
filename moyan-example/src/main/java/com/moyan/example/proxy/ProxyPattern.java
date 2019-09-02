package com.moyan.example.proxy;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 代理测试类，使用代理
 *
 * @author yanbin
 * 
 */
public class ProxyPattern {

    private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);


    public static void main(String[] args) {
        // 不需要执行额外方法的。
        ITalk people = new PeopleTalk("AOP", "18");
        people.talk("No ProXY Test");
        logger.info("-----------------------------");

        // 需要执行额外方法的（切面）
        StaticTalkProxy talker = new StaticTalkProxy(people);
        talker.talk("ProXY Test", "代理");
    }

}
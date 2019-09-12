package com.moyan.example.j2se.proxy.staticproxy;

import com.moyan.example.j2se.base.ViolateTest;
import com.moyan.example.j2se.proxy.ITalk;
import com.moyan.example.j2se.proxy.PeopleTalk;
import com.moyan.example.j2se.proxy.staticproxy.StaticTalkProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 代理测试类，使用代理
 *
 * @author yanbin
 * 
 */
public class ProxyPatternTest {

    private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

    public static void main(String[] args) {
        // 不需要执行额外方法的。
        ITalk people = new PeopleTalk("AOP", "18");
        people.talk("No ProXY JdkProxyTest");
        logger.info("-----------------------------");

        // 需要执行额外方法的（切面）
        StaticTalkProxy talker = new StaticTalkProxy(people);
        talker.talk("ProXY JdkProxyTest", "代理");
    }

}
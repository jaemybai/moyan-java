package com.moyan.example.j2se.proxy.jdk;

import com.moyan.example.j2se.proxy.ITalk;
import com.moyan.example.j2se.proxy.PeopleTalk;

/**
 * 测试类
 * 
 * @author yanbin
 * 
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        // 绑定代理，这种方式会在所有的方法都加上切面方法
        ITalk iTalk = (ITalk) new DynamicProxy().bind(new PeopleTalk());
        iTalk.talk("业务说明");
    }
}
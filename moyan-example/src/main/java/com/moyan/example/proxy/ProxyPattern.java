package com.moyan.example.proxy;
/**
 * 代理测试类，使用代理
 *
 * @author yanbin
 * 
 */
public class ProxyPattern {

    public static void main(String[] args) {
        // 不需要执行额外方法的。
        ITalk people = new PeopleTalk("AOP", "18");
        people.talk("No ProXY Test");
        System.out.println("-----------------------------");

        // 需要执行额外方法的（切面）
        StaticTalkProxy talker = new StaticTalkProxy(people);
        talker.talk("ProXY Test", "代理");
    }

}
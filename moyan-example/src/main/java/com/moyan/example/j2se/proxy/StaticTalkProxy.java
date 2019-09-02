package com.moyan.example.j2se.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 静态代理
 * 代理主题角色：内部包含对真实主题的引用，并且提供和真实主题角色相同的接口。
 * 
 * @author yanbin
 * 
 */
public class StaticTalkProxy implements ITalk {

    private static Logger logger = LoggerFactory.getLogger(StaticTalkProxy.class);

    private ITalk talker;

    public StaticTalkProxy(ITalk talker) {
        // super();
        this.talker = talker;
    }

    public void talk(String msg) {
        talker.talk(msg);
    }

    public void talk(String msg, String singname) {
        talker.talk(msg);
        sing(singname);
    }

    private void sing(String singname) {
        logger.info("唱歌：" + singname);
    }

}
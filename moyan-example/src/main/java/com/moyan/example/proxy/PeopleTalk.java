package com.moyan.example.proxy;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 真实主题角色：定义真实的对象。
 * 
 * @author yanbin
 * 
 */
public class PeopleTalk implements ITalk {

    private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

    public String username;
    public String age;

    public PeopleTalk(){
    	
    }
    public PeopleTalk(String username, String age) {
        this.username = username;
        this.age = age;
    }

    public void talk(String msg) {
        logger.info(msg + "!你好,我是" + username + "，我年龄是" + age);
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
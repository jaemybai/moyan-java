package com.moyan.example.j2se.proxy.test;

import java.lang.reflect.Method;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用cglib动态代理
 * 
 * @author yanbin
 * 
 */
public class CglibProxy implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(CglibProxy.class);

    private Object target;

    /**
     * 创建代理对象
     * 
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        logger.info("事物开始");
        result = methodProxy.invokeSuper(proxy, args);
        logger.info("事物结束");
        return result;
    }

}
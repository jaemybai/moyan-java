package com.moyan.example.springmvc;

import com.moyan.example.springmvc.controller.UserController;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ClassPathXmlApplicationContextTest {

    public static void main(String[] args) throws Exception{
        String path  = "classpath:application.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserController userController = applicationContext.getBean(UserController.class);
        userController.test();
    }
}

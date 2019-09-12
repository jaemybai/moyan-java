package com.moyan.example.springmvc;

import com.moyan.example.springmvc.controller.UserController;
import com.moyan.example.springmvc.model.UserInfo;
import com.moyan.example.springmvc.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@Configuration
@Aspect
public class ClassPathXmlApplicationContextTest {

    public static void main(String[] args) throws Exception{
        String path  = "classpath*:application.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserController userController = applicationContext.getBean(UserController.class);
//        userController.showUserInfo(null,0);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insert(new UserInfo());
        userController.test();
    }

    public void test() {

    }
}

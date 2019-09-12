package com.moyan.com.moyan.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTestMain {

    private static Logger logger = LoggerFactory.getLogger(SpringBootTestMain.class);

    public static void main(String[] args) {
        logger.info("============start===========");
        new SpringApplication(SpringBootTestMain.class).run(args);
        logger.info("============end==============");
    }
}

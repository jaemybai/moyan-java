package com.moyan.com.moyan.example.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class SpringBootTestController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping("/put/{name}")
    public Object putJson(@PathVariable(value = "name") String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",System.currentTimeMillis());
        jsonObject.put("name",name);
        redisTemplate.opsForValue().setIfAbsent(name,Long.toString(System.currentTimeMillis()));
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping("/get/{name}")
    public Object getJson(@PathVariable(value = "name") String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",System.currentTimeMillis());
        String value = redisTemplate.opsForValue().get(name);
        jsonObject.put("value",value);
        return jsonObject;
    }
}

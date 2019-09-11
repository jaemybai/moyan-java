package com.moyan.example.springmvc.controller;


import com.alibaba.fastjson.JSONObject;
import com.moyan.example.springmvc.model.UserInfo;
import com.moyan.example.springmvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/showInfo/{userId}")
	@Transactional
	public String showUserInfo(ModelMap modelMap,@PathVariable int userId) {
		UserInfo userInfo = userService.getUserById(userId);
		modelMap.addAttribute("userInfo",userInfo);
		
		return "/user/showInfo";
		
	}

	@ResponseBody
	@RequestMapping("/test")
	public JSONObject test() throws Exception{
		logger.info("start test.....");
		final long startTime,endTime;
		startTime = System.currentTimeMillis();
		final JSONObject jsonObject = new JSONObject();
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		executorService.submit(new Runnable() {
			public void run() {
				logger.info("thread start.......");
				try {
					Thread.sleep(10000);
				}catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
				long endTime = System.currentTimeMillis() - startTime;
				logger.info("thread end........endTime:" + endTime);
				jsonObject.put("threadEnd",Long.toString(endTime));
			}
		});
		executorService.shutdown();
		executorService.awaitTermination(2, TimeUnit.SECONDS);
		 endTime = System.currentTimeMillis() -startTime;
		logger.info("end end........endTime:" + endTime);
		jsonObject.put("testEnd",Long.toString(endTime));

		return jsonObject;
	}

	public static void main(String[] args) {
		HashMap hashMap;
	}
	
}

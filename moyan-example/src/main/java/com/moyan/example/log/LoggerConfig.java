package com.moyan.example.log;

import org.apache.log4j.PropertyConfigurator;

public class LoggerConfig {

	static {
		PropertyConfigurator.configure("src/log4j.properties");
	}
}

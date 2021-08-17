package com.cg.osm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineSweetMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineSweetMartApplication.class, args);
		final Logger logger = LoggerFactory.getLogger(OnlineSweetMartApplication.class);
		logger.info("I am running.....at port:8092");
		logger.warn("Please follow the instructions");
		logger.error("this is a error message");
	}

}

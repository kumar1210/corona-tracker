package com.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronaTrackerApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(CoronaTrackerApplication.class);

	public static void main(String[] args) {
		
		logger.info("------------------------- Corona Tracker is getting started -------------------------");
		SpringApplication.run(CoronaTrackerApplication.class, args);
		logger.info("------------------------- Corona Tracker is Successfully Up -------------------------");
	}

}

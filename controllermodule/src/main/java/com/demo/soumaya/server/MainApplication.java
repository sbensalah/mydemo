package com.demo.soumaya.server;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("com.demo.soumaya")
@EnableJpaRepositories("com.demo.soumaya.common.core.repositories")
@EntityScan("com.demo.soumaya.common.core.entities")
@EnableWebMvc
public class MainApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		logger.info("--- Application Server is running ------");
	}
	

}

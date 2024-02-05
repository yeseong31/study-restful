package com.example.restfulservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class RestfulServiceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(RestfulServiceApplication.class, args);

		for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
			// log.info("bean name: {}", beanDefinitionName);
		}
	}
}

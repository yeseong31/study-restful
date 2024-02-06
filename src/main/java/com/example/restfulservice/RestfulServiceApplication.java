package com.example.restfulservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Slf4j
@SpringBootApplication
public class RestfulServiceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(RestfulServiceApplication.class, args);

		for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
			// log.info("bean name: {}", beanDefinitionName);
		}
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREA);  // 기본값
		return localeResolver;
	}
}

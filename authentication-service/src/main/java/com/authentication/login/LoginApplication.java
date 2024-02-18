package com.authentication.login;

import com.authentication.login.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean getBean(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new AccessFilter());
		bean.addUrlPatterns("");
		return bean;
	}


}

package com.stackroute.chatservice;
//import com.stackroute.blogproject.filter.BlogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;


@SpringBootApplication
@EnableDiscoveryClient
public class BlogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogServiceApplication.class, args);
	}


}



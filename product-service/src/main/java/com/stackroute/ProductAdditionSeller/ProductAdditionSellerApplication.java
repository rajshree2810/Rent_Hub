package com.stackroute.ProductAdditionSeller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@CrossOrigin("*")
@EnableFeignClients
@EnableDiscoveryClient
public class ProductAdditionSellerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ProductAdditionSellerApplication.class, args);
	}
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedMethods("*")
//				.allowedOrigins("*")
//				.allowedHeaders("*");
//	}
}

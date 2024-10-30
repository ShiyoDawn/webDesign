package com.sduonline.webdesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sduonline.webdesign.mapper")
public class WebDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebDesignApplication.class, args);
	}

}

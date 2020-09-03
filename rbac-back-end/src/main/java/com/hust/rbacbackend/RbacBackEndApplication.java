package com.hust.rbacbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hust.rbacbackend.mapper")
public class RbacBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacBackEndApplication.class, args);
	}

}

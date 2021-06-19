package com.pollsforall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//@ComponentScan(basePackages = "com.pollsforall.model")  // ??? OTHERWISE ERROR------> try consider declaring bean of type com.pollsforall.model
////@ComponentScan(basePackages = "com.pollsforall.config") 
@SpringBootApplication
public class PollingAppApplication {

	public static void main(String[] args) {
		System.out.println("APP Startted");
		SpringApplication.run(PollingAppApplication.class, args);
	}

}

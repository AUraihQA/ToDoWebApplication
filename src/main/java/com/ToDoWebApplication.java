package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ToDoWebApplication {

	public static void main(String[] args) {
		ApplicationContext beanbag = SpringApplication.run(ToDoWebApplication.class, args);

		System.out.println(beanbag.getBean("getTime", String.class));	
	}

}

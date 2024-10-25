package com.kirdmt.demo_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoRestApplication {

	//если будет ругаться на порт 8080, что он якобы занят, то нужно убить процесс 5760 - httpd.exe что-то апачевское. Возможно часть postgres.
	//netstat -ano команда показывает все подключения с портами.
	public static void main(String[] args) {
		SpringApplication.run(DemoRestApplication.class, args);
	}

}

package com.devalvesg.desafio_itau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DesafioItauApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioItauApplication.class, args);
	}

}

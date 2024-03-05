package com.ru.volga.SpringShop11;

import com.ru.volga.SpringShop11.dto.ProductDTO;
import com.ru.volga.SpringShop11.service.ProductService;
import com.ru.volga.SpringShop11.service.ProductServiceImpl;
import org.hibernate.id.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.module.Configuration;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
		System.out.println(encoder.encode("123"));
	}

}

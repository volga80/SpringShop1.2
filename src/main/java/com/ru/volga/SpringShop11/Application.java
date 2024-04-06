package com.ru.volga.SpringShop11;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@EnableCaching
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        System.out.println(encoder.encode("123"));
    }
    //todo пересоздать бд после создания всех сущностей и добавления всех полей, после этого дописать скрипт SQL
    //todo переписать страницы html. добавить поле с количеством товара в наличии, добавить кнопку покупки в корзину,
    //todo и механизм переноса данных о покупке в таблицу для статистики

}

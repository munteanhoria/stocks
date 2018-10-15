package com.devm8.stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("com.devm8.stocks")
@EnableJpaRepositories("com.devm8.stocks.repositories")
public class Main implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

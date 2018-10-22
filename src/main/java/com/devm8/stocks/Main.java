package com.devm8.stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories("com.devm8.stocks.repositories")
@EnableScheduling
public class Main implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

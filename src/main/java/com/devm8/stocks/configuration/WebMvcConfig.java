package com.devm8.stocks.configuration;


import com.devm8.stocks.drivers.StocksDriver;
import com.devm8.stocks.monitoring.StocksMonitor;
import com.devm8.stocks.services.MailService;
import com.devm8.stocks.services.StocksService;
import com.devm8.stocks.services.gmail.GmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public MailService mailService() {
        return new GmailService("sdemailtest100@gmail.com", "sdemailtest1994");
    }

    @Bean
    public StocksDriver stocksDriver(@Value("${alphavantage.access.key}") String accessKey) {
        return new StocksDriver(accessKey);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
    }
}
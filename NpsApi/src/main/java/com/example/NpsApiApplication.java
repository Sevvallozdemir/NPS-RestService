package com.example;

import org.springframework.boot.SpringApplication; //gerekli run metodu sağlıyor
import org.springframework.boot.autoconfigure.SpringBootApplication;   //ana sınıf old. belirtiyor.
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
@EnableFeignClients
public class NpsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NpsApiApplication.class, args);
    }

}

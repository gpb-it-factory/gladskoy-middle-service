package ru.gpbitfactory.minibank.middle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MiddleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddleServiceApplication.class, args);
    }
}

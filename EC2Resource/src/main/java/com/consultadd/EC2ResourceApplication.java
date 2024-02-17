package com.consultadd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching

public class EC2ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EC2ResourceApplication.class, args);
    }

}

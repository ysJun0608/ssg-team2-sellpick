package com.ssg.wsmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication

public class WsmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsmtApplication.class, args);
    }

}

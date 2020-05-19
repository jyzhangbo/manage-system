package com.github.managesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ManageSystemApplication.class);
        app.run(args);
    }

}

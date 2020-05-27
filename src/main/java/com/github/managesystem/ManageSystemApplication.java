package com.github.managesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class ManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ManageSystemApplication.class);
        app.run(args);
    }

}

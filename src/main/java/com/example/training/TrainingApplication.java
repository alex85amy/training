package com.example.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.company")
@ComponentScan("com.example")
public class TrainingApplication {

    public static void main(String[] args) {

        SpringApplication.run(TrainingApplication.class, args);

    }

}

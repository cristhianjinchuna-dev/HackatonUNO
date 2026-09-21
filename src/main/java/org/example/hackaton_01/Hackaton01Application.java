package org.example.hackaton_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example.hackaton_01", "Config"})
public class Hackaton01Application {

    public static void main(String[] args) {
        SpringApplication.run(Hackaton01Application.class, args);
    }

}

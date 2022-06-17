package com.example.holdprojectsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableWebMvc
@EnableScheduling
@SpringBootApplication
public class HoldProjectSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoldProjectSystemApplication.class, args);
    }

}

package com.ch.ch20;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.ch.ch20.mapper")
public class Ch20Application {

    public static void main(String[] args) {

        SpringApplication.run(Ch20Application.class, args);
    }

}

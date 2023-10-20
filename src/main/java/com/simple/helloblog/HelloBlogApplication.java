package com.simple.helloblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.simple.helloblog.mapper")
public class HelloBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloBlogApplication.class, args);
    }

}

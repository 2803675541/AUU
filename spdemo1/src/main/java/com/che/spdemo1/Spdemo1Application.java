package com.che.spdemo1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.che.spdemo1.mapper")
@SpringBootApplication
public class Spdemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Spdemo1Application.class, args);
    }

}

package com.smoothstack.lms.librarianservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.smoothstack.lms.librarianservice.swagger.SwaggerConfig;

@SpringBootApplication
@Import({ SwaggerConfig.class })
public class LibrarianServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarianServiceApplication.class, args);
    }

}

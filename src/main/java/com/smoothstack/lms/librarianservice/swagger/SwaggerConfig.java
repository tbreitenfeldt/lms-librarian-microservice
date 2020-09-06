package com.smoothstack.lms.librarianservice.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("librarian").select()
                .apis(RequestHandlerSelectors.basePackage("com.smoothstack.lms.librarianservice"))
                .paths(PathSelectors.ant("/lms/librarian/**")).build().apiInfo(apiInfo())
                .useDefaultResponseMessages(true);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/v2/api-docs", config);
        return new CorsFilter(source);
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("GCIT-LMS", "Library Management System API.", "API TOS", "Terms of service",
                new Contact("John Doe", "www.example.com", "myeaddress@company.com"), "License of API",
                "API license URL", Collections.emptyList());
        return apiInfo;
    }
}

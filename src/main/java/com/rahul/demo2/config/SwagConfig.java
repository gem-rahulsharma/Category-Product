package com.rahul.demo2.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwagConfig {
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Rahul APIs").select().apis(RequestHandlerSelectors.basePackage("com.rahul.demo2")).paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Library Management System").description("Sample Documentation Generated Using SWAGGER2 for our Library Management System Rest API").license("Dzone Swagger Article").contact("Rahul Sharma").licenseUrl("https://dzone.com/articles/spring-boot-restful-api-documentation" + "-with-swagger").version("1.0").build();
    }
}



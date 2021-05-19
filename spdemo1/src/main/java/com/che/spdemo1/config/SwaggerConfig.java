package com.che.spdemo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Api")
                .apiInfo(apiInfo());//配置Swagger信息

    }

    //配置Swagger信息
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "张松的Swagger API文档",
                "登录注册接口  api",
                "1.0",
                "https://bareth.blog.csdn.net/",
                new Contact("登录注册接口的API", "https://bareth.blog.csdn.net/", "1412578784@qq.com"),//作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }




}

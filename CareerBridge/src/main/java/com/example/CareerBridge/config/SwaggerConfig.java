package com.example.CareerBridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI jobPortalAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CareerBridge API")
                        .description("CareerBridge Platform")
                        .version("1.0.0"));
    }
}


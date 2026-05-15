package com.abbtech.notification.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI notificationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Notification Service API").version("v1"))
                .servers(List.of(new Server()
                        .url("http://localhost:8081/api/e-market")
                        .description("API Gateway")));
    }
}

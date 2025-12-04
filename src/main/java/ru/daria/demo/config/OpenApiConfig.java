package ru.daria.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//тут создается описание для swaggerа
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI myOpenApi() {
        return new OpenAPI().info(new Info().title("SpringBoot Api").version("1").description("Описание тут"));    //SwaggerConfig - старое название
    }
}

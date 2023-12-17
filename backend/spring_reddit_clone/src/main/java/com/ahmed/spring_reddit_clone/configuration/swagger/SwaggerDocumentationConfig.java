package com.ahmed.spring_reddit_clone.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Springboot Reddit Clone")
                        .description("This is a simple `Reddit Clone` app using [spring boot](https://spring.io/) and is documented using OpenAPI 3.0 specification You can find out more about Swagger at [https://swagger.io](https://swagger.io).")
                        .termsOfService("")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://localhost:8080/api/licenses/LICENSE-2.0.html"))
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("ahmedabdelwhed68@gmail.com")));
    }
}

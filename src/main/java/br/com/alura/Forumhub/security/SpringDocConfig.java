package br.com.alura.Forumhub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



    @Configuration
    public class SpringDocConfig {
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .components(new Components()
                            .addSecuritySchemes("bearer-key",
                                    new SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")))
                    .info(new Info()
                            .title("Desafio Alura - FórumHub")
                            .description("API-rest de uma aplicação de Fórum,CRUD de usuários, cursos, e tópicos onde usuários podem responder cada tópico")
                            .contact(new Contact()
                                    .name("Samuel Rodriues")
                                    .email("samuel_souza87@hotmail.com"))
                            .license(new License()
                                    .name("Apache 2.0")
                                    .url("");
}

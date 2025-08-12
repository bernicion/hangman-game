package com.bernic.hangman.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "Hangman API", version = "1.0.0",
        description = "Play Hangman over REST. Start games, guess letters"
))
@Configuration
public class OpenApiConfig {
}

package com.bernic.hangman.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Data
@ConfigurationProperties(prefix = "hangman")
public class HangmanProperties {

    @Min(1) private int maxAttempts;
    private final Words words = new Words();

    @Data
    public static class Words {
        @Size(min = 10) private List<String> list;
    }
}

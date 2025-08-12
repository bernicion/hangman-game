package com.bernic.hangman.config;

import com.bernic.hangman.domain.GameConfig;
import com.bernic.hangman.service.WordProvider;
import com.bernic.hangman.service.impl.RandomWordProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HangmanProperties.class)
public class DefaultConfig {

    @Bean
    GameConfig gameConfig(HangmanProperties props) {
        return new GameConfig(props.getMaxAttempts());
    }

    @Bean
    WordProvider wordProvider(HangmanProperties props) {
        return new RandomWordProvider(props.getWords().getList());
    }

}

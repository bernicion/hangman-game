package com.bernic.hangman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HangmanApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HangmanApiApplication.class, args);
        System.out.println("Hangman API is running...");
    }
}

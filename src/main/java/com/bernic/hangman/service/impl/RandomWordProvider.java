package com.bernic.hangman.service.impl;

import com.bernic.hangman.service.WordProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class RandomWordProvider implements WordProvider {

    private final List<String> words;
    private final Random rand = new Random();

    @Override
    public String nextWord() {
        log.info("Selecting a random word from the list of {} words", words.size());
        return words.get(rand.nextInt(words.size()));
    }
}

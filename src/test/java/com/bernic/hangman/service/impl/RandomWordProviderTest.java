package com.bernic.hangman.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RandomWordProviderTest {

    @Test
    void nextWord_WithNonEmptyList_ShouldReturnWordFromList() {
        List<String> words = List.of("HELLO", "WORLD", "TEST");
        RandomWordProvider wordProvider = new RandomWordProvider(words);

        String word = wordProvider.nextWord();

        assertThat(word).isIn(words);
    }

    @Test
    void nextWord_WithSingleWord_ShouldReturnThatWord() {
        List<String> words = List.of("HELLO");
        RandomWordProvider wordProvider = new RandomWordProvider(words);

        String word = wordProvider.nextWord();

        assertThat(word).isEqualTo("HELLO");
    }
}
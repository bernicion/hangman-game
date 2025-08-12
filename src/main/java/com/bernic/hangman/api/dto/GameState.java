package com.bernic.hangman.api.dto;

import com.bernic.hangman.domain.enums.Status;

import java.util.Set;

public record GameState(String gameId, String maskedWord, Set<Character> wrongLetters,
                        int attemptsLeft, Status status) {
}

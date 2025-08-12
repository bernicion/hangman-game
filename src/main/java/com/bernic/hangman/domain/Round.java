package com.bernic.hangman.domain;


import com.bernic.hangman.domain.enums.GuessResult;
import com.bernic.hangman.domain.enums.Status;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Round {
    private final String target;
    private final char[] revealed;
    private final Set<Character> correctGuesses = new HashSet<>();
    private final Set<Character> wrongGuesses = new HashSet<>();
    private int remaining;

    public Round(String target, int maxAttempts) {
        this.target = target.toLowerCase();
        this.revealed = "-".repeat(target.length()).toCharArray();
        this.remaining = maxAttempts;
    }

    public GuessResult guess(char ch) {
        char c = Character.toLowerCase(ch);
        if (correctGuesses.contains(c) || wrongGuesses.contains(c)) {
            return GuessResult.ALREADY_GUESSED;
        }

        boolean isCorrect = false;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == c) {
                revealed[i] = c;
                isCorrect = true;
            }
        }

        if (isCorrect) {
            correctGuesses.add(c);
            return GuessResult.CORRECT;
        } else {
            wrongGuesses.add(c);
            remaining--;
            return GuessResult.INCORRECT;
        }
    }

    public Status getStatus() {
        if (new String(revealed).equals(target)) {
            return Status.WON;
        }
        return remaining <= 0 ? Status.LOST : Status.IN_PROGRESS;
    }

    public String getMasked() { return new String(revealed); }
}

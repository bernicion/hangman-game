package com.bernic.hangman.service;

import com.bernic.hangman.domain.enums.GuessResult;
import com.bernic.hangman.domain.Game;

import java.util.Optional;

public interface GamePlay {

    String startNewGame();

    Optional<Game> getGame(String gameId);

    GuessResult guess(String gameId, char c);
}

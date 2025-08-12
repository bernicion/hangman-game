package com.bernic.hangman.service.impl;

import com.bernic.hangman.config.HangmanProperties;
import com.bernic.hangman.service.WordProvider;
import com.bernic.hangman.domain.GameConfig;
import com.bernic.hangman.domain.enums.GuessResult;
import com.bernic.hangman.domain.Game;
import com.bernic.hangman.domain.Round;
import com.bernic.hangman.service.GamePlay;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService implements GamePlay {

    private final GameConfig gameConfig;
    private final WordProvider wordProvider;
    private final HangmanProperties hangmanProperties;
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    @Override
    public String startNewGame() {
        log.info("Starting a new game with max attempts: {}", gameConfig.maxAttempts());
        var round = new Round(wordProvider.nextWord(), gameConfig.maxAttempts());
        var game = new Game(round);
        var id = UUID.randomUUID().toString();
        games.put(id, game);
        return id;
    }

    @Override
    public Optional<Game> getGame(String gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    @Override
    public GuessResult guess(String id, char c) {
        log.info("Processing guess '{}' for game '{}'", c, id);
        var game = requireGame(id);
        var round = game.round();
        return round.guess(c);
    }

    private Game requireGame(String id) {
        return getGame(id)
                .orElseThrow(() -> new IllegalArgumentException("Game not found: " + id));
    }

}

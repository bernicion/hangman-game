package com.bernic.hangman.service.impl;

import com.bernic.hangman.domain.GameConfig;
import com.bernic.hangman.domain.enums.GuessResult;
import com.bernic.hangman.service.WordProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameConfig gameConfig;

    @Mock
    private WordProvider wordProvider;

    @InjectMocks
    private GameService gameService;


    @Test
    void startNewGame_ShouldCreateNewGameWithId() {
        when(gameConfig.maxAttempts()).thenReturn(6);
        when(wordProvider.nextWord()).thenReturn("HANGMAN");
        String gameId = gameService.startNewGame();

        assertThat(gameId).isNotNull();
        assertThat(gameService.getGame(gameId)).isPresent();
    }

    @Test
    void getGame_WithNonExistentId_ShouldReturnEmpty() {
        assertThat(gameService.getGame("non-existent")).isEmpty();
    }

    @Test
    void guess_WithValidGameAndCorrectLetter_ShouldReturnCorrectResult() {
        when(gameConfig.maxAttempts()).thenReturn(6);
        when(wordProvider.nextWord()).thenReturn("HANGMAN");
        String gameId = gameService.startNewGame();

        GuessResult result = gameService.guess(gameId, 'H');

        assertThat(result).isEqualTo(GuessResult.CORRECT);
    }

    @Test
    void guess_WithValidGameAndIncorrectLetter_ShouldReturnIncorrectResult() {
        when(gameConfig.maxAttempts()).thenReturn(6);
        when(wordProvider.nextWord()).thenReturn("HANGMAN");
        String gameId = gameService.startNewGame();

        GuessResult result = gameService.guess(gameId, 'Z');

        assertThat(result).isEqualTo(GuessResult.INCORRECT);
    }

    @Test
    void guess_WithInvalidGameId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> gameService.guess("invalid-id", 'A'));
    }
}
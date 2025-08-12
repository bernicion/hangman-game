package com.bernic.hangman.api;

import com.bernic.hangman.api.dto.GameState;
import com.bernic.hangman.api.dto.GuessRequest;
import com.bernic.hangman.api.dto.GuessResponse;
import com.bernic.hangman.domain.Game;
import com.bernic.hangman.domain.Round;
import com.bernic.hangman.domain.enums.Status;
import com.bernic.hangman.domain.enums.GuessResult;
import com.bernic.hangman.service.GamePlay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HangManControllerTest {

    @Mock
    private GamePlay gamePlay;

    @InjectMocks
    private HangManController controller;

    private static final String GAME_ID = "test-game-id";
    private Round mockRound;
    private Game mockGame;

    @BeforeEach
    void setUp() {
        mockRound = new Round("HELLO", 6);
        mockGame = new Game(mockRound);
    }

    @Test
    void startGame_ShouldReturnNewGameState() {
        when(gamePlay.startNewGame()).thenReturn(GAME_ID);
        when(gamePlay.getGame(GAME_ID)).thenReturn(Optional.of(mockGame));

        ResponseEntity<GameState> response = controller.startGame();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(gamePlay).startNewGame();
    }

    @Test
    void getGame_WithValidId_ShouldReturnGameState() {
        when(gamePlay.getGame(GAME_ID)).thenReturn(Optional.of(mockGame));

        ResponseEntity<GameState> response = controller.getGame(GAME_ID);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("-----", response.getBody().maskedWord());
        assertEquals(6, response.getBody().attemptsLeft());
        verify(gamePlay).getGame(GAME_ID);
    }

    @Test
    void getGame_WithInvalidId_ShouldThrowException() {
        when(gamePlay.getGame(GAME_ID)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> controller.getGame(GAME_ID));
        verify(gamePlay).getGame(GAME_ID);
    }

    @Test
    void guess_ShouldReturnGuessResponse() {
        GuessRequest request = new GuessRequest('E');
        when(gamePlay.guess(GAME_ID, 'E')).thenReturn(GuessResult.CORRECT);
        when(gamePlay.getGame(GAME_ID)).thenReturn(Optional.of(mockGame));

        ResponseEntity<GuessResponse> response = controller.guess(GAME_ID, request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(GuessResult.CORRECT, response.getBody().result());
        assertNotNull(response.getBody().state());
        verify(gamePlay).guess(GAME_ID, 'E');
    }
}
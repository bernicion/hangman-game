package com.bernic.hangman.api;

import com.bernic.hangman.api.dto.GameState;
import com.bernic.hangman.api.dto.GuessRequest;
import com.bernic.hangman.api.dto.GuessResponse;
import com.bernic.hangman.domain.enums.GuessResult;
import com.bernic.hangman.service.GamePlay;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/games")
@AllArgsConstructor
public class HangManController {

    private final GamePlay gamePlay;

    @Operation(summary = "Start a new game of Hangman")
    @PostMapping
    public ResponseEntity<GameState> startGame() {
        var gameId = gamePlay.startNewGame();
        return getGame(gameId);
    }

    @Operation(summary = "Get game state")
    @GetMapping("/{id}")
    public ResponseEntity<GameState> getGame(
            @Parameter(
                    name = "id",
                    description = "Game ID returned from /api/games",
                    required = true,
                    example = "e0d1234b-9f54-4bfa-8b11-c8f64fa3b912",
                    in = ParameterIn.PATH
            )
            @PathVariable("id") String id) {
        var game = gamePlay.getGame(id)
                .orElseThrow(() -> new NoSuchElementException("Game not found"));
        var round = game.round();
        var gameState = new GameState(
                id, round.getMasked(), round.getWrongGuesses(), round.getRemaining(), round.getStatus());
        return ResponseEntity.ok(gameState);
    }

    @Operation(summary = "Guess a letter")
    @PostMapping("/{id}/guess")
    public ResponseEntity<GuessResponse> guess(
            @Parameter(
                    name = "id",
                    description = "Game ID returned from /api/games",
                    required = true,
                    example = "e0d1234b-9f54-4bfa-8b11-c8f64fa3b912",
                    in = ParameterIn.PATH
            )
            @PathVariable("id") String id, @Valid @RequestBody GuessRequest request) {
        GuessResult res = gamePlay.guess(id, request.letter());
        var state = getGame(id).getBody();
        return ResponseEntity.ok(new GuessResponse(res, state));
    }
}

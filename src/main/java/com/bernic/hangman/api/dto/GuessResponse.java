package com.bernic.hangman.api.dto;

import com.bernic.hangman.domain.enums.GuessResult;

public record GuessResponse(GuessResult result, GameState state) {
}

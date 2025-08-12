package com.bernic.hangman.api.dto;

import jakarta.validation.constraints.NotNull;

public record GuessRequest(@NotNull char letter) {
}

package com.vimacodes.poker.card;

import java.util.Arrays;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Suit {
    CLUBS('C', '♣'), HEARTS('H', '♥'), SPADES('S', '♠'), DIAMONDS('D', '♦');

    char initial;
    char symbol;

    public static Suit valueOfInitial(Character initial) {
        return Arrays.stream(values())
                .filter(s -> s.initial == initial)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        "There is no suit with initial: " + initial));
    }

    public static Suit valueOfSymbol(Character symbol) {
        return Arrays.stream(values())
                .filter(s -> s.symbol == symbol)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        "There is no suit with symbol: " + symbol));
    }
}

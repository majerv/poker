package com.vimacodes.poker.card;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Rank {
        ACE('A', 14), KING('K', 13), QUEEN('Q', 12), JACK('J', 11), TEN('T', 10), NINE('9',
                        9), EIGHT('8', 8), SEVEN('7', 7), SIX('6',
                                        6), FIVE('5', 5), FOUR('4', 4), THREE('3', 3), TWO('2', 2);

        char symbol;
        int value;

    public static Rank valueOfSymbol(char symbol) {
        return Arrays.stream(values())
                .filter(s -> s.symbol == symbol)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        "There is no rank with symbol: " + symbol));
    }
}

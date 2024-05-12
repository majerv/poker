package com.vimacodes.poker.card;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @ParameterizedTest
    @MethodSource
    void valueOf(String input, Card expectedCard) {
        Card card = Card.valueOf(input);
        Assertions.assertEquals(expectedCard, card);
        Assertions.assertEquals(expectedCard.toShortString(), input);
    }

    private static Stream<Arguments> valueOf() {
        return Stream.of(
                Arguments.of("7H", new Card(Rank.SEVEN, Suit.HEARTS)),
                Arguments.of("5S", new Card(Rank.FIVE, Suit.SPADES)),
                Arguments.of("AC", new Card(Rank.ACE, Suit.CLUBS)),
                Arguments.of("KD", new Card(Rank.KING, Suit.DIAMONDS)));
    }

}

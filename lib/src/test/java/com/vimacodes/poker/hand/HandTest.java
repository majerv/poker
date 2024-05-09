package com.vimacodes.poker.hand;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.vimacodes.poker.card.Card;
import com.vimacodes.poker.card.Rank;
import com.vimacodes.poker.card.Suit;

class HandTest {

    @Test
    void valueOfValidCards() {
        // given
        var input = "7H 5S 7C AS TD";

        // when
        var hand = Hand.valueOf(input);

        // then
        var expectedHand = new Hand(List.of(
                new Card(Rank.SEVEN, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES),
                new Card(Rank.TEN, Suit.DIAMONDS)));

        Assertions.assertEquals(expectedHand, hand);
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @MethodSource
    void checkStraigthProperty(String cards, boolean expected) {
        Assertions.assertEquals(expected, Hand.valueOf(cards).isStraight());
    }

    private static Stream<Arguments> checkStraigthProperty() {
        return Stream.of(
                Arguments.of("7H 5S 7C AS TD", false),
                Arguments.of("7H 5S 4C 6S 8D", true),
                Arguments.of("2S 3S 4S 5S 6S", true),
                Arguments.of("2S 3S 4S 5S AS", true),
                Arguments.of("2C 3H 4D 5S AD", true),
                Arguments.of("2C 3H 6D 5S AD", false),
                Arguments.of("TC JH QD KS AD", true),
                Arguments.of("TC JH QD 5S AD", false));
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @MethodSource
    void checkSameSuitProperty(String cards, boolean expected) {
        Assertions.assertEquals(expected, Hand.valueOf(cards).isSameSuit());
    }

    private static Stream<Arguments> checkSameSuitProperty() {
        return Stream.of(
                Arguments.of("7H 5S 7C AS TD", false),
                Arguments.of("7S 5S 4S 6S 8S", true),
                Arguments.of("7D KD 2D QD TD", true),
                Arguments.of("7D KD 2D QD TS", false));
    }
}

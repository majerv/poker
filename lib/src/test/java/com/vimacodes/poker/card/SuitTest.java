package com.vimacodes.poker.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.vimacodes.poker.card.Suit;

class SuitTest {

    @ParameterizedTest
    @ValueSource(chars = {'♠', '♥', '♦', '♣'})
    void parsingFromValidSymbol(char validSymbol) {
        Suit suit = Suit.valueOfSymbol(validSymbol);
        Assertions.assertEquals(suit.symbol, validSymbol);
    }

    @ParameterizedTest
    @ValueSource(chars = {'♤', '♡', '♢', '♧', 'a'})
    void parsingFromInvalidSymbol(char invalidSymbol) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Suit.valueOfSymbol(invalidSymbol));
    }
}

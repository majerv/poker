package com.vimacodes.poker.hand;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
}

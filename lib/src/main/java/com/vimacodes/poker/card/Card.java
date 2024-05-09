package com.vimacodes.poker.card;

import com.google.common.base.Preconditions;
import lombok.Value;

@Value
public class Card {
    Rank rank;
    Suit suit;

    public static Card valueOf(String input) {
        Preconditions.checkArgument(input.length() == 2, "Card string representation must be 2 characters long, got: %s", input);
        return new Card(Rank.valueOfSymbol(input.charAt(0)), Suit.valueOfInitial(input.charAt(1)));
    }
}

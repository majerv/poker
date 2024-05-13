package com.vimacodes.poker.hand;

import lombok.Value;

@Value
public class TestInput {

    Hand hand;
    String expectedCategory;

    public static TestInput valueOf(String line) {
        var parts = line.split("\\|");
        var hand = Hand.valueOf(parts[0].trim());
        var expectedCategory = parts[1].trim();
        return new TestInput(hand, expectedCategory);

    }

}

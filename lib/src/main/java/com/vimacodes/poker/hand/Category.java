package com.vimacodes.poker.hand;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Category {
    STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIRS, ONE_PAIR, HIGH_CARD;
    
    String toPrettyPrint() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace("_", " ");
    }

    static Category valueOfPrettyPrint(String prettyPrint) {
        return Category.valueOf(prettyPrint.toUpperCase().replaceAll("\\s+", "_"));
    }
}

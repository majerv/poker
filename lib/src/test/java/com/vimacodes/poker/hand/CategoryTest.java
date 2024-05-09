package com.vimacodes.poker.hand;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CategoryTest {

    @ParameterizedTest(name = "[{index}] {arguments}")
    @MethodSource
    void prettyPrint(Category category, String prettyPrint) {
        Assertions.assertEquals(prettyPrint, category.toPrettyPrint());
        Assertions.assertEquals(category, Category.valueOfPrettyPrint(prettyPrint));
    }

    private static Stream<Arguments> prettyPrint() {
        return Stream.of(
                Arguments.of(Category.STRAIGHT_FLUSH, "Straight flush"),
                Arguments.of(Category.FOUR_OF_A_KIND, "Four of a kind"),
                Arguments.of(Category.FULL_HOUSE, "Full house"),
                Arguments.of(Category.FLUSH, "Flush"),
                Arguments.of(Category.STRAIGHT, "Straight"),
                Arguments.of(Category.THREE_OF_A_KIND, "Three of a kind"),
                Arguments.of(Category.TWO_PAIRS, "Two pairs"),
                Arguments.of(Category.ONE_PAIR, "One pair"),
                Arguments.of(Category.HIGH_CARD, "High card"));
    }


}

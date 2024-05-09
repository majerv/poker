package com.vimacodes.poker.hand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestWithResources
class HandEvaluatorTest {

    @GivenTextResource("input.txt")
    String input;

    @Test
    void evaluateHands() {
        input.lines()
                .peek(line -> log.info("line: {}", line))
                .map(TestInput::valueOf)
                .forEach(HandEvaluatorTest::verify);
    }

    private static void verify(TestInput input) {
        HandRank handRank = input.getHand().evaluate();
        Category category = handRank.getCategory();
        log.debug("Hand {} -> rank: {}", input.getHand(), handRank);

        Assertions.assertEquals(input.getExpectedCategory(), category);
    }

}

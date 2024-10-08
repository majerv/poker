package com.vimacodes.poker.evaluation;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.vimacodes.poker.hand.HandRank;
import com.vimacodes.poker.hand.TestInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
@TestWithResources
class CompositeHandEvaluatorTest {

    @GivenTextResource("input.txt")
    String input;

    private static void verify(TestInput input) {
        HandRank handRank = input.getHand().evaluate();
        log.debug("Hand {} -> rank: {}", input.getHand(), handRank);

        Assertions.assertEquals(input.getExpectedCategory(), handRank.toPrettyPrint());
    }

    @Test
    void evaluateHands() {
        input.lines()
                .peek(line -> log.info("line: {}", line))
                .map(TestInput::valueOf)
                .forEach(CompositeHandEvaluatorTest::verify);
    }

}

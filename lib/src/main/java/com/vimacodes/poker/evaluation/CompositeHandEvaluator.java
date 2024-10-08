package com.vimacodes.poker.evaluation;

import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandRank;
import java.util.List;
import java.util.Optional;

public class CompositeHandEvaluator implements HandEvaluator {

    // TODO inject
    private static final List<HandEvaluator> EVALUATORS = List.of(
            new StraightFlushEvaluator(),
            new FourOfAKindEvaluator(),
            new FullHouseEvaluator(),
            new FlushEvaluator(),
            new StraightEvaluator(),
            new ThreeOfAKindEvaluator(),
            new TwoPairsEvaluator(),
            new OnePairEvaluator(),
            new HighCardEvaluator());

    @Override
    public Optional<HandRank> evaluate(Hand hand) {
        return EVALUATORS.stream()
                .map(e -> e.evaluate(hand))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

}

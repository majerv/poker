package com.vimacodes.poker.hand;

import java.util.List;
import java.util.Optional;
import com.vimacodes.poker.evaluation.FourOfAKindEvaluator;
import com.vimacodes.poker.evaluation.FullHouseEvaluator;
import com.vimacodes.poker.evaluation.HighCardEvaluator;
import com.vimacodes.poker.evaluation.OnePairEvaluator;
import com.vimacodes.poker.evaluation.StraightFlushEvaluator;
import com.vimacodes.poker.evaluation.TwoPairsEvaluator;

public class CompositeHandEvaluator implements HandEvaluator {

    // TODO inject
    private static final List<HandEvaluator> EVALUATORS = List.of(
            new StraightFlushEvaluator(),
            new FourOfAKindEvaluator(),
            new FullHouseEvaluator(),
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

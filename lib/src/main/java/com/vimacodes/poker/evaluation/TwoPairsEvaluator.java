package com.vimacodes.poker.evaluation;

import java.util.Optional;
import com.vimacodes.poker.hand.Category;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandEvaluator;
import com.vimacodes.poker.hand.HandRank;

public class TwoPairsEvaluator implements HandEvaluator {

    @Override
    public Optional<HandRank> evaluate(Hand hand) {

        if (hand.getGroupsByRank().keySet().size() == 3) {
            return Optional.of(new HandRank(Category.TWO_PAIRS));
        }

        return Optional.empty();
    }

}

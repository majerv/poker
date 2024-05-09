package com.vimacodes.poker.evaluation;

import java.util.Optional;
import com.vimacodes.poker.hand.Category;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandEvaluator;
import com.vimacodes.poker.hand.HandRank;

public class HighCardEvaluator implements HandEvaluator {

    @Override
    public Optional<HandRank> evaluate(Hand hand) {

        if (hand.getGroupsByRank().keySet().size() == 5) {
            return Optional.of(new HandRank(Category.HIGH_CARD));
        }

        return Optional.empty();
    }

}

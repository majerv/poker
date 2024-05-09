package com.vimacodes.poker.evaluation;

import java.util.Optional;
import com.vimacodes.poker.hand.Category;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandEvaluator;
import com.vimacodes.poker.hand.HandRank;

public class OnePairEvaluator implements HandEvaluator {

    @Override
    public Optional<HandRank> evaluate(Hand hand) {

        if (hand.getGroupsByRank().keySet().size() == 4) {
            return Optional.of(new HandRank(Category.ONE_PAIR));
        }

        return Optional.empty();
    }

}

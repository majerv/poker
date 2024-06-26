package com.vimacodes.poker.evaluation;

import java.util.Optional;
import com.vimacodes.poker.hand.Category;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandRank;

public class FlushEvaluator implements HandEvaluator {

    @Override
    public Optional<HandRank> evaluate(Hand hand) {
        if (hand.isSameSuit() && !hand.isStraight()) {
            return Optional.of(new HandRank(Category.FLUSH));
        }

        return Optional.empty();
    }

}

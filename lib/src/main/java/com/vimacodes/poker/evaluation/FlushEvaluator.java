package com.vimacodes.poker.evaluation;

import com.vimacodes.poker.hand.Category;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandRank;
import java.util.Optional;

public class FlushEvaluator implements HandEvaluator {

    @Override
    public Optional<HandRank> evaluate(Hand hand) {
        if (hand.isSameSuit() && !hand.isStraight()) {
            return Optional.of(new HandRank(Category.FLUSH));
        }

        return Optional.empty();
    }

}

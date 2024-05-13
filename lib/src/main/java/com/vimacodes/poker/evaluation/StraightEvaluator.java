package com.vimacodes.poker.evaluation;

import java.util.Optional;
import com.vimacodes.poker.card.Rank;
import com.vimacodes.poker.hand.Category;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandRank;

public class StraightEvaluator implements HandEvaluator {

    @Override
    public Optional<HandRank> evaluate(Hand hand) {
        if (!hand.isSameSuit() && hand.isStraight()) {
            String name = null;

            if (hand.hasCardWithRank(Rank.ACE)) {
                name = hand.hasCardWithRank(Rank.KING) ? "Broadway straight" : "wheel";
            }

            return Optional.of(new HandRank(Category.STRAIGHT, name));
        }

        return Optional.empty();
    }

}

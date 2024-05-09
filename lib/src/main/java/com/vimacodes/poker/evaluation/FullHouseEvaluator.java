package com.vimacodes.poker.evaluation;

import java.util.Optional;
import com.vimacodes.poker.hand.Category;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandEvaluator;
import com.vimacodes.poker.hand.HandRank;

public class FullHouseEvaluator implements HandEvaluator {

    @Override
    public Optional<HandRank> evaluate(Hand hand) {
        var groupsByRank = hand.getGroupsByRank();

        if (groupsByRank.size() == 2 && groupsByRank.containsValue(3L)) {
            return Optional.of(new HandRank(Category.FULL_HOUSE));
        }

        return Optional.empty();
    }
}

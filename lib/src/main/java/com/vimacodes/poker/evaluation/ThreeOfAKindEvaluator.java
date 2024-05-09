package com.vimacodes.poker.evaluation;

import java.util.Map;
import java.util.Optional;
import com.vimacodes.poker.card.Rank;
import com.vimacodes.poker.hand.Category;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandEvaluator;
import com.vimacodes.poker.hand.HandRank;

public class ThreeOfAKindEvaluator implements HandEvaluator {

    @Override
    public Optional<HandRank> evaluate(Hand hand) {
        Map<Rank, Long> groupsByRank = hand.getGroupsByRank();

        if (groupsByRank.size() == 3 && groupsByRank.containsValue(3L)) {
            return Optional.of(new HandRank(Category.THREE_OF_A_KIND));
        }

        return Optional.empty();
    }

}

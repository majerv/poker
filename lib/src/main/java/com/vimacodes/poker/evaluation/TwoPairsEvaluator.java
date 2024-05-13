package com.vimacodes.poker.evaluation;

import java.util.Map;
import java.util.Optional;
import com.vimacodes.poker.card.Rank;
import com.vimacodes.poker.hand.Category;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandRank;

public class TwoPairsEvaluator implements HandEvaluator {

    @Override
    public Optional<HandRank> evaluate(Hand hand) {
        Map<Rank, Long> groupsByRank = hand.getGroupsByRank();
        
        if (hand.getGroupsByRank().size() == 3 && groupsByRank.containsValue(2L)) {
            return Optional.of(new HandRank(Category.TWO_PAIRS));
        }

        return Optional.empty();
    }

}

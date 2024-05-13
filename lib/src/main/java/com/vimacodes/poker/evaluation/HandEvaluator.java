package com.vimacodes.poker.evaluation;

import java.util.Optional;
import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandRank;

public interface HandEvaluator {
    Optional<HandRank> evaluate(Hand hand);
}

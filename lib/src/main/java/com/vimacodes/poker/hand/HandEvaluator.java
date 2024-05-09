package com.vimacodes.poker.hand;

import java.util.Optional;

public interface HandEvaluator {
    Optional<HandRank> evaluate(Hand hand);
}

package com.vimacodes.poker.evaluation;

import com.vimacodes.poker.hand.Hand;
import com.vimacodes.poker.hand.HandRank;
import java.util.Optional;

public interface HandEvaluator {
  Optional<HandRank> evaluate(Hand hand);
}

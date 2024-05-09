package com.vimacodes.poker.hand;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import com.vimacodes.poker.card.Card;
import lombok.Value;

@Value
public class Hand {

    private static final HandEvaluator DEFAULT_EVALUATOR = new ConditionalHandEvaluator();

    Collection<Card> cards;

    static Hand valueOf(String input) {
        String[] parts = input.split("\\s+");

        List<Card> cards = Arrays.stream(parts)
                .map(String::trim)
                .map(Card::valueOf)
                .collect(Collectors.toList());

        return new Hand(cards);
    }

    public HandRank evaluate() {
        return DEFAULT_EVALUATOR.evaluate(this);
    }

    public HandRank evaluate(HandEvaluator evaluator) {
        return evaluator.evaluate(this);
    }
}

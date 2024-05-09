package com.vimacodes.poker.hand;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.vimacodes.poker.card.Card;
import com.vimacodes.poker.card.Rank;
import lombok.Value;

@Value
public class Hand {

    private static final HandEvaluator DEFAULT_EVALUATOR = new ContextualHandEvaluator();

    Collection<Card> cards;
    boolean sameSuit;
    boolean straight;
    Map<Rank, Long> groupsByRank;

    public Hand(Collection<Card> cards) {
        this.cards = List.copyOf(cards);

        this.sameSuit = 1 == cards.stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet())
                .size();

        this.straight = calculateStraightProperty(cards);

        this.groupsByRank = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

    }

    public static Hand valueOf(String input) {
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

    private static boolean calculateStraightProperty(Collection<Card> cards) {
        List<Card> sortedCards =
                cards.stream()
                        .sorted(Comparator.comparing(Card::getRank))
                        .collect(Collectors.toList());

        return isStraight(sortedCards);
    }

    private static boolean isStraight(List<Card> sortedCards) {
        int numCards = sortedCards.size();

        var rankHigh = sortedCards.getFirst().getRank();
        var rankLow = sortedCards.getLast().getRank();

        boolean straight = (numCards - 1) == rankHigh.getValue() - rankLow.getValue();
        
        if (straight) {
            return true;
        }
        
        // handle 2,3,4,5,A, aka the "wheel"
        if (rankHigh == Rank.ACE) {
            var reducedCards = sortedCards.subList(1, numCards);
            return reducedCards.getFirst().getRank() == Rank.FIVE && isStraight(reducedCards);
        }
        
        return false;
    }
}

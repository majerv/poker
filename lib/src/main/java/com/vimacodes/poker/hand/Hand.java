package com.vimacodes.poker.hand;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.base.Preconditions;
import com.vimacodes.poker.card.Card;
import com.vimacodes.poker.card.Rank;
import com.vimacodes.poker.evaluation.CompositeHandEvaluator;
import com.vimacodes.poker.evaluation.HandEvaluator;
import lombok.NonNull;
import lombok.Value;

@Value
public class Hand {

    private static final HandEvaluator DEFAULT_EVALUATOR = new CompositeHandEvaluator();

    Collection<Card> cards;

    // TODO factor out to a properties object
    boolean sameSuit;
    boolean straight;
    Map<Rank, Long> groupsByRank;

    public Hand(@NonNull Collection<Card> inputCards) {
        checkHandSize(inputCards);
        
        this.cards = Set.copyOf(inputCards);
        checkHandSize(cards);

        this.sameSuit = 1 == cards.stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet())
                .size();

        this.straight = calculateStraightProperty(cards);

        this.groupsByRank = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

    }

    private static void checkHandSize(Collection<Card> cards) {
        Preconditions.checkArgument(cards.size() == 5, "Hands are formed out of 5 cards");
    }

    public static Hand valueOf(@NonNull String input) {
        String[] parts = input.split("\\s+");

        List<Card> cards = Arrays.stream(parts)
                .map(String::trim)
                .map(Card::valueOf)
                .collect(Collectors.toList());

        return new Hand(cards);
    }

    public HandRank evaluate() {
        return evaluateBy(DEFAULT_EVALUATOR);
    }

    public HandRank evaluateBy(HandEvaluator evaluator) {
        return evaluator.evaluate(this)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Could not evaluate the rank of the following hand: " + this.cards));
    }

    public boolean hasCardWithRank(final Rank rank) {
        return cards.stream().anyMatch(c -> c.getRank() == rank);
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

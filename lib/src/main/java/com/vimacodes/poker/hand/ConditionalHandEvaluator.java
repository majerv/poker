package com.vimacodes.poker.hand;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.vimacodes.poker.card.Card;
import com.vimacodes.poker.card.Rank;

public class ConditionalHandEvaluator implements HandEvaluator {

    @Override
    public HandRank evaluate(Hand hand) {
        var cards = hand.getCards();
        
        Map<Rank, Long> rankCounts = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        boolean sameSuits = cards.stream().map(Card::getSuit).collect(Collectors.toSet()).size() == 1;

        List<Rank> sortedCards =
                cards.stream().map(Card::getRank).sorted().collect(Collectors.toList());
        
        // FIXME handle 2,3,4,5,A
        boolean straight = Math.abs(sortedCards.getLast().getRank() - sortedCards.getFirst().getRank()) == 4;
        
        Category category = Category.HIGH_CARD;

        if (sameSuits && straight) {
            category = Category.STRAIGHT_FLUSH;
        }

        if (rankCounts.size() == 2) {
            if (rankCounts.values().contains(4L)) {
                category = Category.FOUR_OF_A_KIND;
            } else {
                category = Category.FULL_HOUSE;
            }
        }

        if (rankCounts.keySet().size() == 3) {
            category = Category.TWO_PAIRS;
        }

        if (rankCounts.keySet().size() == 4) {
            category = Category.ONE_PAIR;
        }

        // FIXME calculate rank 1-7462
        return new HandRank(category, 0);
    }

}

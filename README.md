# Poker evaluator

This project is an implementation of [this programming assignment](https://patakino.web.elte.hu/pny2/beadando.pdf).

The exercise was created by one of my friends, Norbert Pataki, and was originally given to his university students to implement it in `C++`.

## Modules

The project contains the following modules:

- `lib`: a java library to evaluate poker hands according to the `classic` or so called [straight poker](https://en.wikipedia.org/wiki/Poker) variant
- `web-app`: a single-page application to visualize the evaluated poker hand (coming soon)

## Usage

Poker hands can be evaluated by
- forming a [Hand](lib/src/main/java/com/vimacodes/poker/hand/Hand.java) out of 5 [Cards](lib/src/main/java/com/vimacodes/poker/card/Card.java)
- passing it to a [HandEvaluator](lib/src/main/java/com/vimacodes/poker/hand/HandEvaluator.java) implementation

The resulting [HandRank](lib/src/main/java/com/vimacodes/poker/hand/HandRank.java) object reveals the hand's:
- category ([see categories here](https://en.wikipedia.org/wiki/List_of_poker_hands)) 
- special name (e.g. `royal flush` or `wheel`)
- distinct rank: [1-7462] (coming soon)

### Card

Represents a card in the [standard 52-card deck](https://en.wikipedia.org/wiki/Standard_52-card_deck) with its rank and suit:

```java
// e.g. seven of hearts
Card card = new Card(Rank.SEVEN, Suit.HEARTS);
```

or just

```java
Card card = Card.valueOf("7H");
```

### Hand

A hand is formed out of 5 cards:

```java
Hand hand = new Hand(List.of(
                new Card(Rank.SEVEN, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.CLUBS),
                new Card(Rank.ACE, Suit.SPADES),
                new Card(Rank.TEN, Suit.DIAMONDS)));
```

or simply

```java
Hand hand = Hand.valueOf("7H 5S 7C AS TD");
```

### Hand-ranking

The evaluation is driven by classes implementing the [HandEvaluator](lib/src/main/java/com/vimacodes/poker/hand/HandEvaluator.java) interface.

A default evaluator is provided on the `Hand` class, which is based on [these classic ranking rules](https://en.wikipedia.org/wiki/List_of_poker_hands) without jokers:

```java
Hand hand = Hand.valueOf("3H 7H 5H 6H 4H");
HandRank handRank = hand.evaluate();

System.out.println(handRank.getCategory());     --> prints "Straight flush"
```

Different implementations can be provided to:
- alter the rules (e.g. the [ace-to-five low](https://en.wikipedia.org/wiki/Lowball_(poker)#Ace-to-five))
- optimize the evaluation algorithm

```java
class MyHandEvaluator implements HandEvaluator { ... }

MyHandEvaluator myEvaluator = ...
Hand hand = ...

// then
HandRank handRank = hand.evaluateBy(myEvaluator);

// or
Optional<HandRank> handRank = myEvaluator.evaluate(hand);
```
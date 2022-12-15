package gamelogic;

import java.util.*;


public class Deck {

    private static HashMap<Integer,Card> deckKeyToCard = new HashMap<>(); // Hashmap of Key -> Value, card rank -> Card(suit,value)
    private static HashMap<Card, Integer> deckCardToKey = new HashMap<>(); // Hashmap of Value -> Key, Card(suit,value) -> card rank

    private ArrayList<Integer> ranksOfCards = new ArrayList<>();

    public Deck()
    {
        List<String> suits = List.of("Spade","Club","Diamond","Heart");
        List<String> values = List.of("3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2");

        int rank = 0;

        for (String value:values)
        {
            for (String suit:suits)
            {
                deckKeyToCard.put(rank,new Card(suit,value));
                deckCardToKey.put(new Card(suit,value),rank);
                ranksOfCards.add(rank);
                rank++;
            }
        }

        deckKeyToCard.put(rank,new Card("Joker","Black"));
        deckCardToKey.put(new Card("Joker","Black"),rank);
        ranksOfCards.add(rank);
        rank++;

        deckKeyToCard.put(rank,new Card("Joker","Red"));
        deckCardToKey.put(new Card("Joker","Red"),rank);
        ranksOfCards.add(rank);

        //backOfCardImage = new Image("file:images/cards/backofcards.png");
    }

    // shuffle the deck
    public void shuffleDeck()
    {
        Collections.shuffle(ranksOfCards);
    }

    // for debugging: print out entire deck
    public void printDeck()
    {
        for(Integer key : ranksOfCards)
        {
            // Due to the index to find the card in mapList
            Card value = deckKeyToCard.get(key);
            System.out.print(value.toString() + " ");
        }
    }

    public HashMap<Card, Integer> getDeckCardToKey()
    {
        return deckCardToKey;
    }

    public HashMap<Integer, Card> getDeckKeyToValue()
    {
        return deckKeyToCard;
    }

    public Card dealTopCard()
    {
        if (ranksOfCards.size() > 0)
        {
            Integer topCardkey = ranksOfCards.remove(0);
            return deckKeyToCard.get(topCardkey); // return Card object with key
        }
        else
            {
            return null;
        }
    }
}

package gamelogic;

import java.util.ArrayList;
import java.util.Collections;

public class Player
{

    private String name; //the name for players
    public Boolean isLandlord = false; //always set human player as true.

    public ArrayList<Card> myHandInCards = new ArrayList<Card>();
    public ArrayList<Integer> myHandInKeys = new ArrayList<Integer>();

    private Deck tempDeck = new Deck(); // create a new temporary deck in order to get the HashMap

    // player class constructor
    public Player(String name)
    {
        this.name = name;
    }

    public void setHumanPlayerAsLandlord() {
        this.isLandlord = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void putMyCards(Card card) {
        this.myHandInCards.add(card);
    }

    public ArrayList<Card> getPlayerCurrentHand() {
        return myHandInCards;
    }

    public void addToLandlordHandAtIndex(int index, Card card)
    {
        this.myHandInCards.set(index, card);
    }

    public void removePlayerCardOnHand(Integer index)
    {
        this.myHandInCards.remove(index);
    }

/*    public ArrayList<Card> getPlayerCurrentHandTopCard() {
        if (myHand.size() > 0) {
            Integer topCardkey = ranksOfCards.remove(0);
            return deck.get(topCardkey);
        }
        else {
            return null;
        }
    }*/

    public boolean getLandlordStatus() { //not sure if needed
        return this.isLandlord;
    }

    public void emptyHand() {
        this.myHandInCards.clear();
    }

    public void addCards(Card card) {
        myHandInCards.add(card);
    }

    public void convertCardToKey(ArrayList<Card> list){
        //Traveling players'cards and landlord cards and index
        for(Card card : list){
            // Due to the index to find the card in mapList
            myHandInKeys.add(tempDeck.getDeckCardToKey().get(card)); // get each card's Integer value
        }
    }

    public void convertKeyToCard(ArrayList<Integer> list)
    {
        //Traveling players'cards and landlord cards and index
        for(Integer key : list){
            // Due to the index to find the card in mapList
            myHandInCards.add(tempDeck.getDeckKeyToValue().get(key));
        }
    }

    public void setCardAsPlayed(int index) {
        myHandInCards.set(index, null);
    }

    public void sortCards()
    {
        convertCardToKey(myHandInCards); // convert myHand into ArrayList of int ranks
        Collections.sort(myHandInKeys); // sort the hand in keys in order
        myHandInCards.clear(); // clear
        convertKeyToCard(myHandInKeys);
    }
}

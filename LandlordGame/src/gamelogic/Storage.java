package gamelogic;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.BooleanSupplier;

public class Storage {

    private static ArrayList<Card> currentCards = new ArrayList<>();
    private static ArrayList<Integer> currentCardsInKeys = new ArrayList<>();

    public static ArrayList<String> targetCardIDList = new ArrayList<>();
    private static ArrayList<Card> tempCurrentCards = new ArrayList<>();

    private static ArrayList<Card> previousCards = new ArrayList<>();
    public static String previousCombination = new String(); // Used to record the card combination the previous player played.

    private static Boolean playButtonStatus = false;

    private Deck tempDeck = new Deck();

    public void sortCurrentCards()
    {
        convertCurrentCardToKey(currentCards);
        Collections.sort(currentCardsInKeys);
        currentCards.clear();
        convertCurrentKeyToCard(currentCardsInKeys);
    }

    public void convertCurrentCardToKey(ArrayList<Card> list)
    {
        currentCardsInKeys.clear();
        for (Card card : list)
        {
            currentCardsInKeys.add(tempDeck.getDeckCardToKey().get(card));
        }
    }

    public void convertCurrentKeyToCard(ArrayList<Integer> list)
    {
        for (Integer key : list)
        {
            currentCards.add(tempDeck.getDeckKeyToValue().get(key));
        }
    }

    public static void addToTargetCardIDList(String id)
    {
        targetCardIDList.add(id);
    }

    public static String getTargetCardIDLIst(int index)
    {
        return targetCardIDList.get(index);
    }

    public static void addToTempCurrentCards(Card card)
    {
        tempCurrentCards.add(card);
    }

    public static void clearTempCurrentCards()
    {
        tempCurrentCards.clear();
    }

    public static void addCardToCurrentCards(Card card)
    {
        currentCards.add(card);
    }

    public void addCardToPreviousCards(Card card)
    {
        previousCards.add(card);
    }

    public void removeCardAtIndexCurrentCards(int index)
    {
        currentCards.remove(index);
    }

    public static void clearCurrentCards()
    {
        currentCards.clear();
    }

    public static void clearPreviousCards()
    {
        previousCards.clear();
    }

    public static ArrayList<Card> getCurrentCards()
    {
        return currentCards;
    }

    public static ArrayList<Card> getTempCurrentCards()
    {
        return tempCurrentCards;
    }

    public static Card getCurrentCardsAtIndex(int index)
    {
        return currentCards.get(index);
    }

    public void removeTempCardAtTempCardIndex(int index)
    {
        tempCurrentCards.remove(index);
    }

    public static ArrayList<Card> getPreviousCards()
    {
        return previousCards;
    }

    public void setPlayButtonTrue()
    {
        playButtonStatus = true;
        System.out.println(playButtonStatus);
    }

    public static Boolean getPlayButtonStatus()
    {
        return playButtonStatus;
    }

    public static void clearTargetCardIDList()
    {
        targetCardIDList.clear();
    }

    public void copyToPreviousCard(ArrayList<Card> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            previousCards.add(list.get(i));
        }
    }
}

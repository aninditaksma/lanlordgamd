package AI;
import gamelogic.Card;
import gamelogic.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class FarmerAI2 extends Player
{
    // constructor
    public FarmerAI2(String name)
    {
        super(name);
    }

    public ArrayList<Card> findLargest(ArrayList<Card> farmerAIHand, String combination, ArrayList<Card> previousCards)
    {
        // find the largest values in the farmer hand one after the other
        // See if there are multiple occurrences of these largest values.
        // the max number of cards would be 6( 2 triplets)
        // check the size of the previous cards the human player played
        // only one largest card ex. Ace
        Card largestCard = farmerAIHand.get(farmerAIHand.size() - 1);
        ArrayList<Card> firstCards = new ArrayList<>();
        // if the previous player plays a single card
//        if (combination.equals("Single"))
//        {
//            if (getNumericalValue(previousCards.get(0).getValue()) < getNumericalValue(largestCard.getValue()))
//            {
//                firstCards.add(largestCard);
//                setCardAsPlayed(farmerAIHand.size() - 1);
//                return firstCards;
//            }
//            else
//            {
//                return new ArrayList<>();
//            }
//        }

        if(combination.equals("Single"))
        {
            firstCards = findMulti(farmerAIHand, farmerAIHand.size(), "Single");
        }
        // if the previous player plays a pair of cards
        if (combination.equals("Pair")) //we want to find 2 largest possible cards from the hand
        {
            System.out.println(farmerAIHand);
            System.out.println("INNNNN PAIRRR");
            firstCards = findMulti(farmerAIHand, farmerAIHand.size(), "Pair");
        }

        if(combination.equals("Triplet"))
        {
            firstCards = findMulti(farmerAIHand, farmerAIHand.size(), "Triplet");
        }

        if(combination.equals("Bomb"))
        {
            firstCards = findMulti(farmerAIHand, farmerAIHand.size(), "Bomb");
        }

        if(combination.equals("Triplet with an attached card"))
        {
            firstCards = findMulti(farmerAIHand, farmerAIHand.size(), "Triplet with an attached card");
        }

        if(combination.equals("Triplet with an attached pair"))
        {
            firstCards = findMulti(farmerAIHand, farmerAIHand.size(), "Triplet with an attached pair");
        }

        if(combination.equals("Sequence of Triplets"))
        {
            firstCards = findMulti(farmerAIHand, farmerAIHand.size(), "Sequence of Triplets");
        }
        return firstCards;
    }

    public ArrayList<Card> findMulti(ArrayList<Card> farmerAIHand, Integer farmerAIHandSize, String combination)
    {
        // if AI hand does not have any
        if(farmerAIHandSize == 0)
        {
            // returns empty arraylist of pass/no cards
            System.out.println("Pass");
            return new ArrayList<>();
        }


        Card largestCard = farmerAIHand.get(farmerAIHandSize - 1);
        ArrayList<Card> tempList = new ArrayList<>();
        ArrayList<Card> finalList;

        if (combination.equals("Single"))
        {
            for (int i = farmerAIHand.size() - 2; i > 0; i--)
            {
                if (!(farmerAIHand.get(i) == null))
                {
                    tempList.add(largestCard);
                    setCardAsPlayed(farmerAIHand.size() - 1);
                    return tempList;
                }
                else
                {
                    return new ArrayList<>();
                }
            }
        }

        if (combination.equals("Pair"))
        {
            for (int i = farmerAIHand.size() - 2; i > 0; i--)
            {
//                if (!(farmerAIHand.get(i) == null) && getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(largestCard.getValue())) && tempList.size() < 2)
//                {
//                    if (getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 1).getValue())))
//                    {
//                        tempList.add(farmerAIHand.get(i));
//                        tempList.add(farmerAIHand.get(i + 1));
//                        setCardAsPlayed(i);
//                        setCardAsPlayed(i + 1);
//                    }
//                }
                if (!(farmerAIHand.get(i) == null) && !(farmerAIHand.get(i - 1) == null) && /*getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(largestCard.getValue())) &&*/ tempList.size() < 2)
                {
                    if (getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(farmerAIHand.get(i - 1).getValue())))
                    {
                        tempList.add(farmerAIHand.get(i));
                        tempList.add(farmerAIHand.get(i - 1));
                        setCardAsPlayed(i);
                        setCardAsPlayed(i - 1);
                    }
                    else {
                        continue;
                    }
                }
                else{
                    continue;
                }
            }
            System.out.println(farmerAIHand);
            System.out.println(tempList);
//            finalList = removeDuplicates(tempList);

            if(tempList.size() == 2)
            {
                System.out.println("in the if:" + tempList);
                return tempList;
            }
            else
            {
                System.out.println("recursive: Did not have 2 cards!");
                //findMulti(farmerAIHand, farmerAIHandSize - 1, combination);
            }

        }

        // test later with red and black joker

        if(combination.equals("Rocket"))
        {
            System.out.println("Pass");
            return null;
        }

        if(combination.equals("Triplet"))
        {
            for (int i = 0; i < farmerAIHand.size() - 3; i++)
            {
                if (!(farmerAIHand.get(i) == null) && getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(largestCard.getValue())) && tempList.size() < 3)
                {
                    // checking if the values for each card is the same
                    if (getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 1).getValue())) && getNumericalValue(farmerAIHand.get(i + 1).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 2).getValue())))
                    {
                        tempList.add(farmerAIHand.get(i));
                        tempList.add(farmerAIHand.get(i + 1));
                        tempList.add(farmerAIHand.get(i + 2));
                        setCardAsPlayed(i);
                        setCardAsPlayed(i + 1);
                        setCardAsPlayed(i + 2);
                    }
                }
            }

            if(tempList.size() == 3)
            {
                System.out.println(farmerAIHand);
                System.out.println("final templist: " + tempList);
                return tempList;
            }
            else
            {
                System.out.println("recursive: Did not have 3 cards!");
                //findMulti(farmerAIHand, farmerAIHandSize - 1, combination);
            }

        }

        if(combination.equals("Bomb"))
        {
            for (int i = 0; i < farmerAIHand.size() - 1; i++)
            {
                if (!(farmerAIHand.get(i) == null) &&getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(largestCard.getValue())) && tempList.size() < 4)
                {
                    if (getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 1).getValue())) && getNumericalValue(farmerAIHand.get(i + 1).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 2).getValue())) && getNumericalValue(farmerAIHand.get(i + 2).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 3).getValue())))
                    {
                        tempList.add(farmerAIHand.get(i));
                        tempList.add(farmerAIHand.get(i + 1));
                        tempList.add(farmerAIHand.get(i + 2));
                        tempList.add(farmerAIHand.get(i + 3));
                        setCardAsPlayed(i);
                        setCardAsPlayed(i + 1);
                        setCardAsPlayed(i + 2);
                        setCardAsPlayed(i + 3);
                    }
                }
            }

            if(tempList.size() == 4)
            {
                System.out.println(farmerAIHand);
                System.out.println("final templist: " + tempList);
                return tempList;
            }
            else
            {
                System.out.println("recursive: Did not have 4 cards!");
                //findMulti(farmerAIHand, farmerAIHandSize - 1, combination);
            }
        }

        if(combination.equals("Triplet with an attached card"))
        {
            for (int i = 0; i < farmerAIHand.size() - 3; i++) //this
            {
                if (!(farmerAIHand.get(i) == null) && getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(largestCard.getValue())) && tempList.size() < 3)
                {
                    if (getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 1).getValue())) && getNumericalValue(farmerAIHand.get(i + 1).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 2).getValue())))
                    {
                        tempList.add(farmerAIHand.get(i));
                        tempList.add(farmerAIHand.get(i + 1));
                        tempList.add(farmerAIHand.get(i + 2));
                        setCardAsPlayed(i);
                        setCardAsPlayed(i + 1);
                        setCardAsPlayed(i + 2);
                    }
                }
            }

            if(tempList.size() == 3)
            {
                for (int i = 0; i < farmerAIHand.size(); i++)
                {
                    if (!(farmerAIHand.get(i).getValue() == null))
                    {
                        tempList.add(farmerAIHand.get(i));
                        System.out.println("Additional Card added is: " + farmerAIHand.get(0));
                        setCardAsPlayed(i);
                        System.out.println(farmerAIHand);
                        System.out.println("final tempList is: " + tempList);
                        return tempList;
                    }
                }
            }
            else
            {
                System.out.println("recursive: Did not have 4 cards!");
                //findMulti(farmerAIHand, farmerAIHandSize - 1, combination);
            }
        }

        if(combination.equals("Triplet with an attached pair"))
        {
            for (int i = 0; i < farmerAIHand.size() - 3; i++) //this
            {
                if (!(farmerAIHand.get(i) == null) && getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(largestCard.getValue())) && tempList.size() < 3)
                {
                    if (getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 1).getValue())) && getNumericalValue(farmerAIHand.get(i + 1).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 2).getValue())))
                    {
                        tempList.add(farmerAIHand.get(i));
                        tempList.add(farmerAIHand.get(i + 1));
                        tempList.add(farmerAIHand.get(i + 2));
                        setCardAsPlayed(i);
                        setCardAsPlayed(i + 1);
                        setCardAsPlayed(i + 2);
                    }
                }
            }

            if(tempList.size() == 3)
            {
                getPair(farmerAIHand, tempList);
                return tempList;
            }
            else
            {
                System.out.println("Did not have 3 cards!");
                //findMulti(farmerAIHand, farmerAIHandSize - 1, combination);
            }

        }

        if(combination.equals("Sequence of Triplets"))
        {
            System.out.println("In SOT");
            for (int i = 0; i < farmerAIHand.size() - 3; i++) //this
            {
                if (!(farmerAIHand.get(i) == null) && getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(largestCard.getValue())) && tempList.size() < 3)
                {
                    if (getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 1).getValue())) && getNumericalValue(farmerAIHand.get(i + 1).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 2).getValue())))
                    {
                        // adding the triplet
                        tempList.add(farmerAIHand.get(i));
                        tempList.add(farmerAIHand.get(i + 1));
                        tempList.add(farmerAIHand.get(i + 2));
                        setCardAsPlayed(i);
                        setCardAsPlayed(i + 1);
                        setCardAsPlayed(i + 2);
                    }
                }
            }

            if(tempList.size() == 3)
            {
                getTriplet(farmerAIHand, tempList);
                return tempList;
            }
            else
            {
                System.out.println("Did not have 3 cards!");
                //findMulti(farmerAIHand, farmerAIHandSize - 1, combination);
            }

        }
        return findMulti(farmerAIHand, farmerAIHandSize - 1, combination);
    }

    public ArrayList<Card> getTriplet(ArrayList<Card> farmerAIHand, ArrayList<Card> tripletCards)
    {
        for (int i = 0; i < farmerAIHand.size() - 3; i++) //this
        {
            if (!(farmerAIHand.get(i) == null) && tripletCards.size() < 6 && !tripletCards.contains(farmerAIHand.get(i)))
            {
                if (getNumericalValue(farmerAIHand.get(i + 1).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 2).getValue())))
                {
                    if (getNumericalValue(tripletCards.get(0).getValue()) == (getNumericalValue(farmerAIHand.get(i).getValue()) - 1) || getNumericalValue(tripletCards.get(0).getValue()) == (getNumericalValue(farmerAIHand.get(i).getValue()) + 1))
                    {
                        if(getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 1).getValue())))
                        {
                            tripletCards.add(farmerAIHand.get(i));
                            tripletCards.add(farmerAIHand.get(i + 1));
                            tripletCards.add(farmerAIHand.get(i + 2));
                            setCardAsPlayed(i);
                            setCardAsPlayed(i + 1);
                            setCardAsPlayed(i + 2);
                        }
                    }
                }
            }
        }

        if(tripletCards.size() == 6)
        {
            System.out.println(farmerAIHand);
            System.out.println("final list returned: " + tripletCards);
            return tripletCards;
        }
        else
        {
            System.out.println("You don't have enough triplets");
            return null;
        }
        //return null;
    }

    public ArrayList<Card> getPair(ArrayList<Card> farmerAIHand, ArrayList<Card> tripletCards)
    {
        for (int i = 0; i < farmerAIHand.size() - 1; i++)
        {
            if (!(farmerAIHand.get(i) == null) && tripletCards.size() < 5)
            {
                if (getNumericalValue(farmerAIHand.get(i).getValue()).equals(getNumericalValue(farmerAIHand.get(i + 1).getValue())))
                {
                    if(!tripletCards.contains(farmerAIHand.get(i)))
                    {
                        tripletCards.add(farmerAIHand.get(i));
                        tripletCards.add(farmerAIHand.get(i + 1));
                        setCardAsPlayed(i);
                        setCardAsPlayed(i + 1);
                    }
                }
            }
        }

        if(tripletCards.size() == 5)
        {
            System.out.println(farmerAIHand);
            System.out.println("final list returned: " + tripletCards);
            return tripletCards;
        }
        else
        {
            System.out.println("You have no pair for triplet and an attached pair");
            return null;
        }
        //return null;
    }

    public static Integer getNumericalValue(String cardValue)
    {
        switch (cardValue)
        {
            case "3":
                return 1;
            case "4":
                return 2;
            case "5":
                return 3;
            case "6":
                return 4;
            case "7":
                return 5;
            case "8":
                return 6;
            case "9":
                return 7;
            case "10":
                return 8;
            case "J":
                return 9;
            case "Q":
                return 10;
            case "K":
                return 11;
            case "A":
                return 12;
            case "2":
                return 13;
            case "Black":
                return 14;
            case "Red":
                return 15;
            default:
                return 0;
        }
    }

    public ArrayList<Card> removeDuplicates(ArrayList<Card> movesWithDup)
    {
        ArrayList<Card> newList = new ArrayList<Card>();

        for(Card c : movesWithDup)
        {
            if(!newList.contains(c))
            {
                newList.add(c);
            }
        }
        return newList;
    }
}
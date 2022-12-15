package gamelogic;

import java.util.ArrayList;

public class MainForDebug {

    private static ArrayList<Card> testList;

    public static void main(String args[])
    {

        testList = new ArrayList<>();
        testList.add(new Card("Spade", "3"));
        testList.add(new Card("Heart", "3"));
        testList.add(new Card("Club", "3"));
        testList.add(new Card("Spade", "4"));
        testList.add(new Card("Heart", "4"));
        testList.add(new Card("Diamond", "4"));



//        Player landlord = new Player("landlord");
//
//        // will this to change this later to AI player class
//        Player farmerAI1 = new Player("farmerAI1");
//        Player farmerAI2 = new Player("farmerAI2");
//
//        Game newGame = new Game(landlord, farmerAI1, farmerAI2);
//        newGame.initGame();

        //Game.checkCombination(testList);






    }
}

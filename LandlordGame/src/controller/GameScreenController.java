package controller;

import AI.FarmerAI;
import AI.FarmerAI2;
import gamelogic.*;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameScreenController extends Storage implements Initializable {

    // individual card object as images in the game screen
    @FXML private ImageView card1;
    @FXML private ImageView card2;
    @FXML private ImageView card3;
    @FXML private ImageView card4;
    @FXML private ImageView card5;
    @FXML private ImageView card6;
    @FXML private ImageView card7;
    @FXML private ImageView card8;
    @FXML private ImageView card9;
    @FXML private ImageView card10;
    @FXML private ImageView card11;
    @FXML private ImageView card12;
    @FXML private ImageView card13;
    @FXML private ImageView card14;
    @FXML private ImageView card15;
    @FXML private ImageView card16;
    @FXML private ImageView card17;
    @FXML private ImageView card18;
    @FXML private ImageView card19;
    @FXML private ImageView card20;
    @FXML private ImageView chosenCard1;
    @FXML private ImageView chosenCard2;
    @FXML private ImageView chosenCard3;
    @FXML private ImageView chosenCard4;
    @FXML private ImageView chosenCard5;
    @FXML private ImageView chosenCard6;
    @FXML private HBox chosenCardHbox;
    @FXML private HBox farmerAI1Hbox;
    @FXML private HBox farmerAI2Hbox;
    @FXML private ImageView farmerAI1Card1;
    @FXML private ImageView farmerAI1Card2;
    @FXML private ImageView farmerAI1Card3;
    @FXML private ImageView farmerAI1Card4;
    @FXML private ImageView farmerAI1Card5;
    @FXML private ImageView farmerAI1Card6;
    @FXML private ImageView farmerAI2Card1;
    @FXML private ImageView farmerAI2Card2;
    @FXML private ImageView farmerAI2Card3;
    @FXML private ImageView farmerAI2Card4;
    @FXML private ImageView farmerAI2Card5;
    @FXML private ImageView farmerAI2Card6;
    @FXML private Button clearAndResetButton;
    @FXML private Button playButton;
    @FXML private Button newGameButton;
    @FXML private Button exitButton;
    @FXML private HBox playerHandHBox;
    @FXML private ImageView gameScreenBackground;

    public ArrayList<String> currentCardsValueInString;
    ArrayList<Player> playerOrder;
    Player landlord;
    FarmerAI farmerAI1;
    FarmerAI2 farmerAI2;
    private Deck deck;
    ArrayList<Card> farmerAI1played;
    ArrayList<Card> farmerAI2played;
    private static String combo;

    int counter = 0; // record the number of times a player have played.
    int numOfPasses = 0; // record the number of passes, if passed twice (ie. numOfPasses == 2), then clear the preCards.

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        gameScreenBackground.setImage(new Image("file:images/ui/main.jpg"));

        landlord = new Player("landlord");
        // will this to change this later to AI player class
        farmerAI1 = new FarmerAI("farmerAI1");
        farmerAI2 = new FarmerAI2("farmerAI2");

        landlord.setHumanPlayerAsLandlord(); // set player1 as the human player (landlord)

        playerOrder = new ArrayList<Player>();
        playerOrder.add(landlord); // landlord goes first
        playerOrder.add(farmerAI1);
        playerOrder.add(farmerAI2);

        deck = new Deck();
        deck.shuffleDeck();
        dealCardToPlayer();
    }

    @FXML public void newGameButtonPushed() throws IOException
    {
        System.out.println("GAME STARTS!");
        initPlayerHandCardImages();
        playGame();
        newGameButton.setVisible(false);
    }

    public Player playGame()
    {

        System.out.println();

        if (landlord.getPlayerCurrentHand().size() == 0) // Player 1 wins, as no more cards in hand.
        {
            System.out.println("in p1 win condition");
            return landlord;
        } else if (farmerAI1.getPlayerCurrentHand().size() == 0) // Player 2 wins
        {
            System.out.println("in p2 win condition");
            return farmerAI1;
        } else if (farmerAI2.getPlayerCurrentHand().size() == 0) // Player 3 wins
        {
            System.out.println("in p3 win condition");
            return farmerAI2;
        }
        else {
            if (counter % 3 == 0) // player 1's turn
            {
//                chosenCard1.setImage(null);
//                chosenCard2.setImage(null);
//                chosenCard3.setImage(null);
//                chosenCard4.setImage(null);
//                chosenCard5.setImage(null);
//                chosenCard6.setImage(null);


                clearCurrentCards(); //before every player's turn clear the currentCard arraylist for the new sets of cards
                clearPreviousCards();
                playerHandHBox.setOnMouseClicked(e ->
                {
                    // add target card to currentCards ArrayList
                    currentCardsValueInString = new ArrayList<String>();
                    String targetCardID = e.getPickResult().getIntersectedNode().getId();

                    switch (targetCardID)
                    {
                        case "card1":
                            card1.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(0).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(0));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(0));
                            getChosenCardsImage(getTempCurrentCards()); //display chosen card onto chosenCardHBox
                            removeTempCardAtTempCardIndex(0); //remove chosen card from tempCardIndex to avoid duplicate cards displayed onto the chosenCardHBox
                            landlord.setCardAsPlayed(0);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card2":
                            card2.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(1).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(1));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(1));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(1);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card3":
                            card3.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(2).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(2));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(2));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(2);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card4":
                            card4.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(3).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(3));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(3));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(3);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card5":
                            card5.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(4).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(4));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(4));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(4);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card6":
                            card6.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(5).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(5));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(5));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(5);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card7":
                            card7.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(6).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(6));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(6));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(6);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card8":
                            card8.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(7).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(7));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(7));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(7);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card9":
                            card9.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(8).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(8));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(8));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(8);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card10":
                            card10.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(9).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(9));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(9));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(9);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card11":
                            card11.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(10).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(10));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(10));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(10);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card12":

                            card12.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(11).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(11));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(11));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(11);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card13":
                            card13.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(12).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(12));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(12));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(12);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card14":
                            card14.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(13).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(13));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(13));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(13);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card15":
                            card15.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(14).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(14));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(14));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(14);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card16":
                            card16.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(15).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(15));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(15));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(15);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card17":
                            card17.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(16).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(16));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(16));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(16);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card18":
                            card18.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(17).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(17));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(17));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(17);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card19":
                            card19.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(18).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(18));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(18));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(18);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                        case "card20":
                            card20.setImage(null);
                            currentCardsValueInString.add(landlord.getPlayerCurrentHand().get(19).getValue());
                            System.out.println("You've chosen this card: " + currentCardsValueInString);
                            addToTargetCardIDList(targetCardID);
                            addCardToCurrentCards(landlord.getPlayerCurrentHand().get(19));
                            addToTempCurrentCards(landlord.getPlayerCurrentHand().get(19));
                            getChosenCardsImage(getTempCurrentCards());
                            removeTempCardAtTempCardIndex(0);
                            landlord.setCardAsPlayed(19);
                            System.out.println("Your Updated Hand: " + landlord.getPlayerCurrentHand());
                            break;
                    }
                    System.out.println("Target Card ID: " + targetCardID);
                    System.out.println();
                });

                //For Testing atm REMOVE LATER --------------------------------
                //addCardToPreviousCards(new Card("Spade", "6"));
                //addCardToPreviousCards(new Card("Club", "6"));
                //previousCombination = "Pair";
                //addCardToPreviousCards(new Card("Diamond", "6"));
                //addCardToPreviousCards(new Card("Heart", "6"));
                //For Testing atm REMOVE LATER --------------------------------

                playButton.setOnAction((e) ->
                {
                    doTurn(landlord);
                });

                clearAndResetButton.setOnAction((e) ->
                {
                    System.out.println("Clicked On Clear And Reset");
                    clearAndResetFunction();
                });

            } else if (counter % 3 == 1) // player 2's turn
            {
//                System.out.println("Current cards : " + currentCardsValueInString);
//                System.out.println("Previous cards: " + getPreviousCards());
                System.out.println("Player 2's turn");
                System.out.println("Farmer AI1 Hand :" + farmerAI1.getPlayerCurrentHand());
                farmerAI1played = farmerAI1.findLargest(farmerAI1.getPlayerCurrentHand(), previousCombination, getPreviousCards());
                System.out.println("Card(s) played by FarmerAI1: " + farmerAI1played);


                //System.out.println(farmerAI1.getPlayerCurrentHand(), farmerAI1played);

                if(farmerAI1played.isEmpty())
                {
                    // need to add pop up
                    //getPreviousCards().clear();
                    //doTurn(farmerAI1);
                    System.out.println("Pass");
                }
                else {
                    clearCurrentCards();
                    clearPreviousCards();
                }


                for(int i = 0 ; i <= farmerAI1played.size() - 1; i++)
                {
                    if(i == 0)
                    {
                        farmerAI1Card1.setImage(farmerAI1played.get(0).getCardImage());
                        currentCardsValueInString.add(farmerAI1played.get(0).getValue());
                        addCardToCurrentCards(farmerAI1played.get(0));
                        addToTempCurrentCards(farmerAI1played.get(0));
                        removeTempCardAtTempCardIndex(0);
                        getPreviousCards().clear();
                        System.out.println("\n");
                        System.out.println("Previous Cards: " + getPreviousCards());
                        System.out.println("Current cards single : " + getCurrentCards());
                        System.out.println("FarmerAI1 Updated hand: " + farmerAI1.getPlayerCurrentHand());
                    }
                    if(i == 1)
                    {
                        System.out.println("FarmerAI1 played :" + farmerAI1played);
                        farmerAI1Card1.setImage(farmerAI1played.get(0).getCardImage());
                        addCardToCurrentCards(farmerAI1played.get(1));
                        addToTempCurrentCards(farmerAI1played.get(1));
                        removeTempCardAtTempCardIndex(0);
                        farmerAI1Card2.setImage(farmerAI1played.get(1).getCardImage());
                        currentCardsValueInString.add(farmerAI1played.get(1).getValue());
                        getPreviousCards().clear();
                        System.out.println("\n");
                        System.out.println("Previous Cards: " + getPreviousCards());
                        System.out.println("Current cards pair: " + getCurrentCards());
                        System.out.println("FarmerAI1 Updated hand: " + farmerAI1.getPlayerCurrentHand());

                    }
                    if(i == 2)
                    {
                        System.out.println("FarmerAI1 played :" + farmerAI1played);
                        farmerAI1Card1.setImage(farmerAI1played.get(0).getCardImage());
                        farmerAI1Card2.setImage(farmerAI1played.get(1).getCardImage());
                        addCardToCurrentCards(farmerAI1played.get(2));
                        addToTempCurrentCards(farmerAI1played.get(2));
                        System.out.println("\n");
                        System.out.println("Current cards triplet: " + getCurrentCards());
                        removeTempCardAtTempCardIndex(0);
                        farmerAI1Card3.setImage(farmerAI1played.get(2).getCardImage());
                        getPreviousCards().clear();
                        System.out.println("FarmerAI1 Updated hand: " + farmerAI1.getPlayerCurrentHand());
                    }
                    if(i == 3)
                    {
                        farmerAI1Card1.setImage(farmerAI1played.get(0).getCardImage());
                        farmerAI1Card2.setImage(farmerAI1played.get(1).getCardImage());
                        addCardToCurrentCards(farmerAI1played.get(3));
                        addToTempCurrentCards(farmerAI1played.get(3));
                        System.out.println("\n");
                        System.out.println("Current cards triplet with attached card: " + getCurrentCards());
                        removeTempCardAtTempCardIndex(0);
                        farmerAI1Card4.setImage(farmerAI1played.get(3).getCardImage());
                        getPreviousCards().clear();
                        System.out.println("FarmerAI1 Updated hand: " + farmerAI1.getPlayerCurrentHand());
                    }
                    if(i == 4)
                    {
                        farmerAI1Card1.setImage(farmerAI1played.get(0).getCardImage());
                        farmerAI1Card2.setImage(farmerAI1played.get(1).getCardImage());
                        addCardToCurrentCards(farmerAI1played.get(4));
                        addToTempCurrentCards(farmerAI1played.get(4));
                        System.out.println("\n");
                        System.out.println("Current cards triplet with attached pair: " + getCurrentCards());
                        removeTempCardAtTempCardIndex(0);
                        farmerAI1Card4.setImage(farmerAI1played.get(3).getCardImage());
                        farmerAI1Card5.setImage(farmerAI1played.get(4).getCardImage());
                        getPreviousCards().clear();
                        System.out.println("FarmerAI1 Updated hand: " + farmerAI1.getPlayerCurrentHand());
                    }
                    if(i == 5)
                    {
                        farmerAI1Card1.setImage(farmerAI1played.get(0).getCardImage());
                        farmerAI1Card2.setImage(farmerAI1played.get(1).getCardImage());
                        farmerAI1Card2.setImage(farmerAI1played.get(2).getCardImage());
                        addCardToCurrentCards(farmerAI1played.get(5));
                        addToTempCurrentCards(farmerAI1played.get(5));
                        System.out.println("\n");
                        System.out.println("Current cards sequence of triplets: " + getCurrentCards());
                        removeTempCardAtTempCardIndex(0);
                        farmerAI1Card4.setImage(farmerAI1played.get(3).getCardImage());
                        farmerAI1Card5.setImage(farmerAI1played.get(4).getCardImage());
                        farmerAI1Card6.setImage(farmerAI1played.get(5).getCardImage());
                        getPreviousCards().clear();
                        System.out.println("FarmerAI1 Updated hand: " + farmerAI1.getPlayerCurrentHand());
                    }
                }

                doTurn(farmerAI1);

            }
            else if (counter % 3 == 2) // player 3's turn
            {
                System.out.println("Player 3's turn");

                System.out.println("FarmerAI2 hand : " + farmerAI2.getPlayerCurrentHand());
                farmerAI2played = farmerAI2.findLargest(farmerAI2.getPlayerCurrentHand(), previousCombination, getPreviousCards());

                if(farmerAI2played.isEmpty())
                {
                    System.out.println("Pass");
                }
                else {
                    clearCurrentCards();
                    clearPreviousCards();
                }


                for(int i = 0 ; i <= farmerAI2played.size() - 1; i++)
                {
                    if(i == 0)
                    {
                        farmerAI2Card1.setImage(farmerAI2played.get(0).getCardImage());
                        currentCardsValueInString.add(farmerAI2played.get(0).getValue());
                        addCardToCurrentCards(farmerAI2played.get(0));
                        addToTempCurrentCards(farmerAI2played.get(0));
                        removeTempCardAtTempCardIndex(0);
                        getPreviousCards().clear();
                        System.out.println("\n");
                        System.out.println("Previous Cards: " + getPreviousCards());
                        System.out.println("Current cards single : " + getCurrentCards());
                        System.out.println("FarmerAI2 Updated hand: " + farmerAI2.getPlayerCurrentHand());

                    }
                    if(i == 1)
                    {
                        System.out.println("FarmerAI1 played :" + farmerAI2played);
                        farmerAI2Card1.setImage(farmerAI2played.get(0).getCardImage());
                        addCardToCurrentCards(farmerAI2played.get(1));
                        addToTempCurrentCards(farmerAI2played.get(1));
                        removeTempCardAtTempCardIndex(0);
                        farmerAI2Card2.setImage(farmerAI2played.get(1).getCardImage());
                        currentCardsValueInString.add(farmerAI2played.get(1).getValue());
                        getPreviousCards().clear();
                        System.out.println("\n");
                        System.out.println("Previous Cards: " + getPreviousCards());
                        System.out.println("Current cards pair: " + getCurrentCards());
                        System.out.println("FarmerAI2 Updated hand: " + farmerAI2.getPlayerCurrentHand());
                    }
                    if(i == 2)
                    {
                        System.out.println("FarmerAI1 played :" + farmerAI2played);
                        farmerAI2Card1.setImage(farmerAI2played.get(0).getCardImage());
                        farmerAI2Card2.setImage(farmerAI2played.get(1).getCardImage());
                        addCardToCurrentCards(farmerAI2played.get(2));
                        addToTempCurrentCards(farmerAI2played.get(2));
                        System.out.println("\n");
                        System.out.println("Current cards triplet: " + getCurrentCards());
                        removeTempCardAtTempCardIndex(0);
                        farmerAI2Card3.setImage(farmerAI2played.get(2).getCardImage());
                        getPreviousCards().clear();
                        System.out.println("FarmerAI2 Updated hand: " + farmerAI2.getPlayerCurrentHand());
                    }
                    if(i == 3)
                    {
                        farmerAI2Card1.setImage(farmerAI2played.get(0).getCardImage());
                        farmerAI2Card2.setImage(farmerAI2played.get(1).getCardImage());
                        addCardToCurrentCards(farmerAI2played.get(3));
                        addToTempCurrentCards(farmerAI2played.get(3));
                        System.out.println("\n");
                        System.out.println("Current cards triplet with attached card: " + getCurrentCards());
                        removeTempCardAtTempCardIndex(0);
                        farmerAI2Card4.setImage(farmerAI2played.get(3).getCardImage());
                        getPreviousCards().clear();
                        System.out.println("FarmerAI1 Updated hand: " + farmerAI2.getPlayerCurrentHand());
                    }
                    if(i == 4)
                    {
                        farmerAI2Card1.setImage(farmerAI2played.get(0).getCardImage());
                        farmerAI2Card2.setImage(farmerAI2played.get(1).getCardImage());
                        addCardToCurrentCards(farmerAI2played.get(4));
                        addToTempCurrentCards(farmerAI2played.get(4));
                        System.out.println("\n");
                        System.out.println("Current cards triplet with attached pair: " + getCurrentCards());
                        removeTempCardAtTempCardIndex(0);
                        farmerAI2Card4.setImage(farmerAI2played.get(3).getCardImage());
                        farmerAI2Card5.setImage(farmerAI2played.get(4).getCardImage());
                        getPreviousCards().clear();
                        System.out.println("FarmerAI1 Updated hand: " + farmerAI2.getPlayerCurrentHand());
                    }
                    if(i == 5)
                    {
                        farmerAI2Card1.setImage(farmerAI2played.get(0).getCardImage());
                        farmerAI2Card2.setImage(farmerAI2played.get(1).getCardImage());
                        farmerAI2Card2.setImage(farmerAI2played.get(2).getCardImage());
                        addCardToCurrentCards(farmerAI2played.get(5));
                        addToTempCurrentCards(farmerAI2played.get(5));
                        System.out.println("\n");
                        System.out.println("Current cards sequence of triplets: " + getCurrentCards());
                        removeTempCardAtTempCardIndex(0);
                        farmerAI2Card4.setImage(farmerAI2played.get(3).getCardImage());
                        farmerAI2Card5.setImage(farmerAI2played.get(4).getCardImage());
                        farmerAI2Card6.setImage(farmerAI2played.get(5).getCardImage());
                        getPreviousCards().clear();
                        System.out.println("FarmerAI2 Updated hand: " + farmerAI2.getPlayerCurrentHand());
                    }
                }

                doTurn(farmerAI2);
            }
        }

        return null;
    }

    public int getCorrespondingCardIndex(ArrayList<Card> playerHand, ArrayList<Card> cardsPlayed)
    {
        for(int i = 0; i < playerHand.size() - 1; i++)
        {
            for(int j = 0; j < cardsPlayed.size() -1 ; j++)
            {
                if(playerHand.get(i).equals(cardsPlayed.get(j)))
                {
                    return i;
                }
            }
        }
        return 0;
    }

    public void doTurn(Player p)
    {
        System.out.println("CurrentCard Unsorted: " + getCurrentCards());
        sortCurrentCards();
        System.out.println("CurrentCard Sorted: " + getCurrentCards());

        previousCombination = checkCombination(getCurrentCards());
        System.out.println("Combination Played: " + previousCombination);
        System.out.println("First Round: Next Player's Turn");
        copyToPreviousCard(getCurrentCards());
        System.out.println("Counter in do turn :" + counter);
        if(counter < 2) // should be less than 2 for rounds
        {
            counter++;
        }
        else
        {
            counter = 0;
        }
        System.out.println("Counter in do turn after :" + counter);
        playGame();

        System.out.println("Previous Combination: " + previousCombination);
        String combinationPlayed = checkCombination(getCurrentCards());
        System.out.println(combinationPlayed);
        if(combinationPlayed.equals(previousCombination))
        {
            Boolean validPlay = compareCardRanks(combinationPlayed, getCurrentCards(), getPreviousCards());
            if (validPlay)
            {
                System.out.println("Valid Play");
                System.out.println("You Played: " + combinationPlayed);
                copyToPreviousCard(getCurrentCards());
            }
            else
            {
            // show error message "invalid play"
            System.out.println("Invalid Play, Try Again (Same Combination)");
            clearAndResetFunction();
            playGame();
            }
        }
        else if(combinationPlayed.equals("Pass"))
        {
            playGame();
        }
        else
        {
            System.out.println("Invalid Play, Try Again (Wrong Combination)");
            clearAndResetFunction();
            playGame();
        }
    }

    public static String checkCombination(ArrayList<Card> playedCard)
    {
        // pass
        if(playedCard.size() == 0)
        {
            combo = "Pass";
        }
        // one card of the same number
        if (playedCard.size() == 1)
        {
            combo = "Single";
        }

        if(playedCard.size() == 2)
        {
            // gets the value from the two cards and compares them
            // two cards of the same rank
            if(playedCard.get(0).getValue().equals(playedCard.get(1).getValue()))
            {
                combo = "Pair";
            }
            // two jokers
            if(playedCard.get(0).getValue().equals("Red") && playedCard.get(1).getValue().equals("Black"))
            {
                combo = "Rocket";
            }
            // two jokers
            if(playedCard.get(0).getValue().equals("Black") && playedCard.get(1).getValue().equals("Red"))
            {
                combo = "Rocket";
            }
        }

        if (playedCard.size() == 3)
        {
            // three cards of the same rank
            if(playedCard.get(0).getValue().equals(playedCard.get(1).getValue()) && playedCard.get(0).getValue().equals(playedCard.get(2).getValue()))
            {
                combo = "Triplet";
            }
        }

        if(playedCard.size() == 4)
        {
            // four cards of the same rank
            if((playedCard.get(0).getValue().equals(playedCard.get(1).getValue())) && (playedCard.get(2).getValue().equals(playedCard.get(3).getValue()))
                    && (playedCard.get(0).getValue().equals(playedCard.get(2).getValue()))                                                                                                                                  )
            {
                combo = "Bomb";
            }

            // triplet with attached card at the back
            if((playedCard.get(0).getValue().equals(playedCard.get(1).getValue())) && (playedCard.get(1).getValue().equals(playedCard.get(2).getValue())
                    && (!(playedCard.get(3).getValue().equals(playedCard.get(1).getValue())))))
            {
                System.out.println("Triplet with attached card");
                combo = "Triplet with an attached card";
            }
            // triplet with attached card at the front
            else if((playedCard.get(1).getValue().equals(playedCard.get(2).getValue())) && (playedCard.get(2).getValue().equals(playedCard.get(3).getValue())
                    && (!(playedCard.get(0).getValue().equals(playedCard.get(1).getValue())))))
            {
                System.out.println("Triplet with an attached card");
                combo = "Triplet with an attached card";

            }
        }

        if(playedCard.size() == 5)
        {
            // triplet with attached pair in the front
            if(playedCard.get(2).getValue().equals(playedCard.get(3).getValue()) && playedCard.get(2).getValue().equals(playedCard.get(4).getValue())
                    && playedCard.get(0).getValue().equals(playedCard.get(1).getValue()))
            {
                combo = "Triplet with an attached pair";
            }
            // triplet with attached pair in the back
            if(playedCard.get(0).getValue().equals(playedCard.get(1).getValue()) && playedCard.get(0).getValue().equals(playedCard.get(2).getValue())
                    && playedCard.get(3).getValue().equals(playedCard.get(4).getValue()))
            {
                combo = "Triplet with an attached pair";
            }
        }

        if(playedCard.size() == 6)
        {
            // Sequence of triplets
            // P1 : 7-7-7-8-8-8 || P2: 3-3-3-4-4-4
            if(playedCard.get(0).getValue().equals(playedCard.get(1).getValue()) && playedCard.get(0).getValue().equals(playedCard.get(2).getValue())
                    && playedCard.get(3).getValue().equals(playedCard.get(4).getValue())
                    && playedCard.get(3).getValue().equals(playedCard.get(5).getValue())
                    && (!(playedCard.get(2).getValue().equals(playedCard.get(3).getValue()))))
            {
                if((getNumericalValue(playedCard.get(3).getValue()) - ((getNumericalValue(playedCard.get(2).getValue()))) == 1))
                {
                    System.out.println("Sequence of Triplets");
                    combo = "Sequence of Triplets";
                }
            }

        }
        // too complicated to implement for now
//        if(playedCard.size() == 8)
//        {
//            // Sequence of triplets with attached cards
//
//            if(playedCard.get(0).getValue().equals(playedCard.get(1).getValue()) && playedCard.get(0).getValue().equals(playedCard.get(2).getValue())
//                    && playedCard.get(3).getValue().equals(playedCard.get(4).getValue())
//                    && playedCard.get(3).getValue().equals(playedCard.get(5).getValue())
//                    && (!(playedCard.get(2).getValue().equals(playedCard.get(3).getValue()))))
//            {
//                return "Sequence of triplets";
//            }
//        }
        return combo;
    }

    public static boolean compareCardRanks(String combination, ArrayList<Card> playedCard, ArrayList<Card> previousCard)
    {
        if (combination.equals("Single"))
        {
            if (getNumericalValue(playedCard.get(0).getValue()) < getNumericalValue(previousCard.get(0).getValue()))
            {
                return false;
            }
            else
            {
                return true;
            }

        }

        if(combination.equals("Pair"))
        {
            if(getNumericalValue(playedCard.get(1).getValue()) < getNumericalValue(previousCard.get(1).getValue()))
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        if(combination.equals("Rocket"))
        {
            // returns true since Jokers have the largest values in the game
            return true;
        }

        if (combination.equals("Triplet"))
        {
            if(getNumericalValue(playedCard.get(2).getValue()) < getNumericalValue(previousCard.get(2).getValue()))
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        if (combination.equals("Bomb"))
        {
            if(getNumericalValue(playedCard.get(3).getValue()) < getNumericalValue(previousCard.get(3).getValue()))
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        if(combination.equals("Triplet with an attached card"))
        {
            if(getNumericalValue(playedCard.get(1).getValue()) < getNumericalValue(previousCard.get(1).getValue()))
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        if(combination.equals("Triplet with an attached pair"))
        {
            if(getNumericalValue(playedCard.get(2).getValue()) < getNumericalValue(previousCard.get(2).getValue()))
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        if(combination.equals("Sequence of Triplets"))
        {
            if(getNumericalValue(playedCard.get(5).getValue()) < getNumericalValue(previousCard.get(5).getValue()))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        return false;
    }

    // return integer value of the card based on its Card Value string
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

    public ArrayList<String> getCurrentCardsValueInString()
    {
        return currentCardsValueInString;
    }


    public void initPlayerHandCardImages()
    {
        card1.setImage(landlord.getPlayerCurrentHand().get(0).getCardImage());
        card2.setImage(landlord.getPlayerCurrentHand().get(1).getCardImage());
        card3.setImage(landlord.getPlayerCurrentHand().get(2).getCardImage());
        card4.setImage(landlord.getPlayerCurrentHand().get(3).getCardImage());
        card5.setImage(landlord.getPlayerCurrentHand().get(4).getCardImage());
        card6.setImage(landlord.getPlayerCurrentHand().get(5).getCardImage());
        card7.setImage(landlord.getPlayerCurrentHand().get(6).getCardImage());
        card8.setImage(landlord.getPlayerCurrentHand().get(7).getCardImage());
        card9.setImage(landlord.getPlayerCurrentHand().get(8).getCardImage());
        card10.setImage(landlord.getPlayerCurrentHand().get(9).getCardImage());
        card11.setImage(landlord.getPlayerCurrentHand().get(10).getCardImage());
        card12.setImage(landlord.getPlayerCurrentHand().get(11).getCardImage());
        card13.setImage(landlord.getPlayerCurrentHand().get(12).getCardImage());
        card14.setImage(landlord.getPlayerCurrentHand().get(13).getCardImage());
        card15.setImage(landlord.getPlayerCurrentHand().get(14).getCardImage());
        card16.setImage(landlord.getPlayerCurrentHand().get(15).getCardImage());
        card17.setImage(landlord.getPlayerCurrentHand().get(16).getCardImage());
        card18.setImage(landlord.getPlayerCurrentHand().get(17).getCardImage());
        card19.setImage(landlord.getPlayerCurrentHand().get(18).getCardImage());
        card20.setImage(landlord.getPlayerCurrentHand().get(19).getCardImage());
    }

    // this function takes the cards from the tempCurrentCard arraylist and display the corresponding card image onto the chosenCardHBox
    public void getChosenCardsImage(ArrayList<Card> listOfCards)
    {
        for(Card p : listOfCards)
        {
            if(chosenCard1.getImage() == null)
            {
                chosenCard1.setImage(listOfCards.get(listOfCards.indexOf(p)).getCardImage());
            }
            else if(chosenCard2.getImage() == null)
            {
                chosenCard2.setImage(listOfCards.get(listOfCards.indexOf(p)).getCardImage());
            }
            else if(chosenCard3.getImage() == null)
            {
                chosenCard3.setImage(listOfCards.get(listOfCards.indexOf(p)).getCardImage());
            }
            else if(chosenCard4.getImage() == null)
            {
                chosenCard4.setImage(listOfCards.get(listOfCards.indexOf(p)).getCardImage());
            }
            else if(chosenCard5.getImage() == null)
            {
                chosenCard5.setImage(listOfCards.get(listOfCards.indexOf(p)).getCardImage());
            }
            else if(chosenCard6.getImage() == null)
            {
                chosenCard6.setImage(listOfCards.get(listOfCards.indexOf(p)).getCardImage());
            }
        }
    }


    // This function deals the cards to each of the players (landlord, farmerAI1, farmerAI2)
    public void dealCardToPlayer()
    {
        //to make sure all players have empty hands
        for (Player p : playerOrder)
        {
            p.emptyHand();
        }

        for (Player p : playerOrder)
        {
            if (p.isLandlord == true)
            { // deal 20 cards to landlord (i.e. human player)
                for (int i = 0; i < 20; i++)
                {
                    p.addCards(deck.dealTopCard());
                }
            }
            else
            {
                for (int i = 0; i < 17; i++)
                {
                    p.addCards(deck.dealTopCard());
                }
            }
            p.sortCards(); // sort player's cards in hand after it's been dealt
        }
    }

    public void clearAndResetFunction()
    {
        clearTempCurrentCards();
        chosenCard1.setImage(null);
        chosenCard2.setImage(null);
        chosenCard3.setImage(null);
        chosenCard4.setImage(null);
        chosenCard5.setImage(null);
        chosenCard6.setImage(null);

        for (int i = 0; i < targetCardIDList.size(); i++)
        {
            switch (getTargetCardIDLIst(i)) {
                case "card1":
                    landlord.addToLandlordHandAtIndex(0, getCurrentCardsAtIndex(i));
                    break;
                case "card2":
                    landlord.addToLandlordHandAtIndex(1, getCurrentCardsAtIndex(i));
                    break;
                case "card3":
                    landlord.addToLandlordHandAtIndex(2, getCurrentCardsAtIndex(i));
                    break;
                case "card4":
                    landlord.addToLandlordHandAtIndex(3, getCurrentCardsAtIndex(i));
                    break;
                case "card5":
                    landlord.addToLandlordHandAtIndex(4, getCurrentCardsAtIndex(i));
                    break;
                case "card6":
                    landlord.addToLandlordHandAtIndex(5, getCurrentCardsAtIndex(i));
                    break;
                case "card7":
                    landlord.addToLandlordHandAtIndex(6, getCurrentCardsAtIndex(i));
                    break;
                case "card8":
                    landlord.addToLandlordHandAtIndex(7, getCurrentCardsAtIndex(i));
                    break;
                case "card9":
                    landlord.addToLandlordHandAtIndex(8, getCurrentCardsAtIndex(i));
                    break;
                case "card10":
                    landlord.addToLandlordHandAtIndex(9, getCurrentCardsAtIndex(i));
                    break;
                case "card11":
                    landlord.addToLandlordHandAtIndex(10, getCurrentCardsAtIndex(i));
                    break;
                case "card12":
                    landlord.addToLandlordHandAtIndex(11, getCurrentCardsAtIndex(i));
                    break;
                case "card13":
                    landlord.addToLandlordHandAtIndex(12, getCurrentCardsAtIndex(i));
                    break;
                case "card14":
                    landlord.addToLandlordHandAtIndex(13, getCurrentCardsAtIndex(i));
                    break;
                case "card15":
                    landlord.addToLandlordHandAtIndex(14, getCurrentCardsAtIndex(i));
                    break;
                case "card16":
                    landlord.addToLandlordHandAtIndex(15, getCurrentCardsAtIndex(i));
                    break;
                case "card17":
                    landlord.addToLandlordHandAtIndex(16, getCurrentCardsAtIndex(i));
                    break;
                case "card18":
                    landlord.addToLandlordHandAtIndex(17, getCurrentCardsAtIndex(i));
                    break;
                case "card19":
                    landlord.addToLandlordHandAtIndex(18, getCurrentCardsAtIndex(i));
                    break;
                case "card20":
                    landlord.addToLandlordHandAtIndex(19, getCurrentCardsAtIndex(i));
                    break;
            }
            System.out.println("Within loop: " + landlord.getPlayerCurrentHand());
        }
        System.out.println("This should go back to the original hand: " + landlord.getPlayerCurrentHand());
        clearCurrentCards();
        clearTargetCardIDList();
        initPlayerHandCardImages();
    }


    @FXML public void exitGameScreen(ActionEvent event) throws IOException
    {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            Storage.clearCurrentCards();
            Storage.clearPreviousCards();
            Storage.clearTempCurrentCards();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainMenu.fxml"));

            Stage deckViewWindow = (Stage) exitButton.getScene().getWindow();
            deckViewWindow.setScene(new Scene(root));

        }
    }
}



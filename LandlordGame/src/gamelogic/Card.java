package gamelogic;

import javafx.scene.image.Image;

import java.util.Objects;

public class Card { //Individual Cards //add extends Objects if needed
    private String suit;
    private String value;

    private Image cardImage;

    public Card(String suit, String value)
    {
        this.suit = suit;
        this.value = value;

        String cardFileName = value + "_" + suit + ".png";
        cardImage = new Image("file:images/cards/" + cardFileName);
    }

    public Image getCardImage() {
        return cardImage;
    }

    public void setCardImage(Image cardImage) {
        this.cardImage = cardImage;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

/*    // Overridden method for comparing cards by suit (for sorting hands)
    public int compareTo (Card other) {
        if (suit.compareTo(other.getSuit()) == 0)
            return value.compareTo(other.getValue());
        return suit.compareTo(other.getSuit());
    }

    // Overriden method to check if cards are the same
    public boolean equals(Card other) {
        return suit.equals(other.getSuit()) && value.equals(other.getValue());
    }*/

    public String printCard() {
        return getValue() + " of " + getSuit();
    }

    @Override
    public String toString(){
        return String.format("(%s,%s)", value, suit);
    }

    //comparing if two card objects have the same value and suit
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(suit, card.suit) && Objects.equals(value, card.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }

    //add copy() function if needed
}

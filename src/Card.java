public class Card {
    public String suit;
    public String value;
    public String cardName;
    public int cardPoint;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.cardName = suit + " " + value;
    }

    public String getSuit() {
        return suit;
    }

    public String getCardName(){
        return cardName;
    }

    public String getValue() {
        return value;
    }
}

// file reading
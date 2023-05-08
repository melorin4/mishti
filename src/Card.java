import java.util.List;

public class Card {
    public String suit;
    public String value;
    public String cardName;
    public int cardPoint;

    public Card(String suit, String value, int cardPoint) {
        this.suit = suit;
        this.value = value;
        this.cardName = suit + " " + value;
        this.cardPoint = cardPoint;
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

    public int getCardPoint() {
        return cardPoint;
    }

    public int getPointValue(List<String> pointValues) {
        for (String line : pointValues) {
            String[] parts = line.split(" ");
            if (parts[0].equals(suit) && (parts[1].equals(value) || parts[1].equals("*"))) {
                return Integer.parseInt(parts[2]);
            }
        }
        return 1; //default value for cards
    }
}
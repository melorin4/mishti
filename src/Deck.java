import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    private ArrayList<String> deck;

    public Deck() {
        deck = new ArrayList<>();
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        //create the cards
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + suit);
            }
        }

        Collections.shuffle(deck);
    }

    //testing if the deck got shuffled correctly
    public void printDeck() {
        for (String card : deck) {
            System.out.println(card);
        }
    }

    //cut the card deck at the given index
    public void cutDeck(int index) {
        if (index < 0 || index >= deck.size()) {
            System.out.println("Invalid number!");
            return;
        }

        ArrayList<String> topHalf = new ArrayList<>(deck.subList(0, index));
        ArrayList<String> bottomHalf = new ArrayList<>(deck.subList(index, deck.size()));

        //replace the first deck and change the cards with the cut version
        deck.clear();
        deck.addAll(bottomHalf);
        deck.addAll(topHalf);
    }
}

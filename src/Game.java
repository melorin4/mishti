import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Game {
    private ArrayList<Card> deck;
    public ArrayList<Card> playedCards;
    public Random ran = new Random();
    public Card card;
    public boolean isFirstRound;
    public void createDeck()
    {
        deck = new ArrayList<>();
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        //create the cards
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public void cutDeck() {
        int index = ran.nextInt(1,51);
        ArrayList<Card> topHalf = new ArrayList<>(deck.subList(0, index));
        ArrayList<Card> bottomHalf = new ArrayList<>(deck.subList(index, deck.size()-1));

        //replace the first deck and change the cards with the cut version
        deck.clear();
        deck.addAll(bottomHalf);
        deck.addAll(topHalf);
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }


    public void printDeck(){
        for (Card card1: deck) {
            System.out.println(card1.getCardName());
        }
    }

    public Card dealCard(){
        Card card = deck.remove(0);
        return card;
    }


}

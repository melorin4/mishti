import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Game {
    public ArrayList<Card> deck;
    public ArrayList<Card> playedCards;
    public Random ran = new Random();
    public Card card;
    public boolean isFirstRound = true;
    public int playerCount;
    public ArrayList<Card> table;

    public boolean isMishti = false; // pişti puanı hesaplanırken kullan!

    Player player1 = null; //Human
    Player player2 = null;
    Player player3 = null;
    Player player4 = null;


    public void InitGame(int playerCount){
        createDeck();





   }

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
        System.out.println(getDeckSize());
        ArrayList<Card> topHalf = new ArrayList<>(deck.subList(0, index-1));
        ArrayList<Card> bottomHalf = new ArrayList<>(deck.subList( index,  deck.size()-1));

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

    public Card pullCardFromDeck(){
        Card card = deck.remove(0);
        return card;
    }

    public void deal4Card(Player player){

        for (int i=0; i<4; i++) {
            Card temp = pullCardFromDeck();
            player.getHand().add(temp);}
    }
    public void dealCard(){

        if (isFirstRound){
            for (int i=0; i<4; i++){
               Card temp = pullCardFromDeck();
               table.add(temp);
               playedCards.add(temp);
            }
            isFirstRound=false;
        }
       if (playerCount==2){
           deal4Card(player1);
           deal4Card(player2);
       }
        if (playerCount==3){
            deal4Card(player1);
            deal4Card(player2);
            deal4Card(player3);
        }
        if (playerCount==4){
            deal4Card(player1);
            deal4Card(player2);
            deal4Card(player3);
            deal4Card(player4);
        }
    }
    public void addGround(Card thrownCard){ // Human and Bots
        playedCards.add(thrownCard);
        table.add(thrownCard);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public int getDeckSize() {
        return deck.size();
    }

    public void cardCompare(){

        int size = table.size();
        if (table.size()>1){
            if (table.get(size-1).getValue() == table.get(size-2).getValue() || table.get(size-1).getValue() == "J"){
                table.clear();
            }
            // Puanlar eklenecek!!

        }
        if (table.size() == 1){
            if (table.get(size-1).getValue() == table.get(size-2).getValue()){
                isMishti = true;
                table.clear();
            }
            if (isMishti = false){
                if (table.get(size-1).getValue() == "J"){
                    table.clear();

                }
            }
        }
        isMishti = false;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Game {
    private ArrayList<Card> deck;
    private String pointFilePath;
    public ArrayList<Card> playedCards;
    public Random ran = new Random();
    public boolean isFirstRound = true;
    public int playerCount;
    public ArrayList<Card> table;

    public boolean isMishti = false; // pişti puanı hesaplanırken kullan!

    Player player1 = null; //Human
    Player player2 = null;
    Player player3 = null;
    Player player4 = null;

    public Game(String pointFilePath){
        this.pointFilePath = pointFilePath;
    }

    public void InitGame(int playerCount){ //*************************
        createDeck();
        cutDeck();
        shuffleDeck();





   }

    public void  getDeckWithPoints() {
        for (Card card: this.deck) {
            System.out.println(card.cardName+" "+card.cardPoint);
        }

    }

    public void createDeck()
    {
        ArrayList<Card> localDeck = new ArrayList<Card>();
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        //create the cards
        for (String suit : suits) {
            for (String rank : ranks) {
                localDeck.add(new Card(suit, rank, this.pointFilePath));
            }
        }
        this.deck = localDeck;
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


    public void cardCompare(Player player){

        int size = table.size();
        if (table.size()>1){
            if (table.get(size-1).getRank() == table.get(size-2).getRank() || table.get(size-1).getRank() == "J"){
                calculateScore(isMishti,player);
                table.clear();
            }
        }
        if (table.size() == 1){
            if (table.get(size-1).getRank() == table.get(size-2).getRank()){
                isMishti = true;
                calculateScore(isMishti,player);
                table.clear();
            }
            if (isMishti = false){
                if (table.get(size-1).getRank() == "J"){
                    calculateScore(isMishti,player);
                    table.clear();
                }
            }
        }
        isMishti = false;
    }
    public void calculateScore(boolean isMishti,Player player){
        int score = 0;
        for(Card i : table){
            score += i.cardPoint;
        }
        if(isMishti) {
            score = 5*score;
        }
        player.addPoints(score);
    }
    public void playRound(Player player){
        Card thrownCard = player.throwCard(table,playedCards);
        addGround(thrownCard);
        cardCompare(player);
    }
    public void GameLoop

















}

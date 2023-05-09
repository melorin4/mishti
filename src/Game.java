import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Game {
    private ArrayList<Card> deck;
    private String pointFilePath;
    public ArrayList<Card> playedCards;
    public Random ran = new Random();
    public boolean isFirstRound = true;
    public int playerCount;
    public ArrayList<Card> table;
    public ArrayList<String> playerNames;
    public ArrayList<String> playerExpertise;
    public String verbosenessLevel;
    private String playerScores[][];

    public boolean isMishti = false; // pişti puanı hesaplanırken kullan!

    Player player1 = null; //Human
    Player player2 = null;
    Player player3 = null;
    Player player4 = null;

    public Game(int playerCount,String pointFilePath,ArrayList<String> playerNames,ArrayList<String> playerExpertise,String verbosenessLevel){
        this.pointFilePath = pointFilePath;
        this.playerCount = playerCount;
        this.playerNames = playerNames;
        this.playerExpertise = playerExpertise;
        this.verbosenessLevel = verbosenessLevel;
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
    public void GameLoop(int round,){}

    public void CreateLeaderboard(Player player) throws IOException {
        ReadLeaderboard(true);
        int position = -1;
        for (int i = 0; i < playerScores.length; i++) {
            if (playerScores[i][1] == null || player.getPoints() > Integer.parseInt(playerScores[i][1])) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            System.out.println("CONGRATULATIONS\n---high score---");
            for (int i = playerScores.length - 1; i > position; i--) {
                playerScores[i][0] = playerScores[i - 1][0];
                playerScores[i][1] = playerScores[i - 1][1];
            }
            playerScores[position][0] = player.getName();
            playerScores[position][1] = Integer.toString(player.getPoints());
        }
        UpdateLeaderboard();
    }

    public void UpdateLeaderboard() {
        Formatter f = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("leaderboard.txt", false);
            f = new Formatter(fw);
            for (int i = 0; i < playerScores.length; i++) {
                f.format("%s,%s\n", playerScores[i][0], playerScores[i][1]);
            }
            fw.close();
        } catch (Exception e) {
            System.err.println("Something went wrong.");
        } finally {
            if (f != null) {
                f.close();
            }
        }
    }

    public void PrintLeaderboard() {
        ReadLeaderboard(false);
        System.out.println("Leaderboard:");
        for (int i = 0; i < playerScores.length; i++) {
            if (playerScores[i][1] != null) {
                System.out.println(playerScores[i][0] + ": " + playerScores[i][1]);
            }
        }
    }

    public void ReadLeaderboard(boolean isNewHighScore) {
        FindPlayerCount(isNewHighScore);
        Scanner reader = null;
        String line = "";
        int playerCount = 0;
        try {
            reader = new Scanner(Paths.get("C:\\Users\\Okan Özyürekli\\IdeaProjects\\Card Game Project\\leaderboard.txt"));
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] info = line.split(",");
                playerScores[playerCount][0] = info[0];
                playerScores[playerCount][1] = info[1];
                playerCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public void FindPlayerCount(boolean isNewHighScore) {
        Scanner reader = null;
        String line = "";
        int playerCount = 0;
        try {
            reader = new Scanner(Paths.get("C:\\Users\\kaan\\Documents\\GitHub\\mishti\\src\\leaderboard.txt"));
            while (reader.hasNextLine()) {
                playerCount++;
                reader.nextLine();
            }
            if (playerCount >= 10) {
                playerCount = 10;
            } else if (isNewHighScore) {
                playerCount++;
            }
            playerScores = new String[playerCount][2];
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

















}

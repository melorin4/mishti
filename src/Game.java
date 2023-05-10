import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Game {
    public String verbose = "";
    public String succint = "";
    private int playerCount;
    private Player[] players = null;
    private ArrayList<Card> deck;
    private String pointFilePath;
    public ArrayList<Card> playedCards = new ArrayList<>();
    public Random ran = new Random();
    public boolean isFirstRound;
    public boolean isFirstGame = true;
    public ArrayList<Card> table = new ArrayList<>();
    public ArrayList<String> playerNames;
    public ArrayList<String> playerExpertise;
    public boolean verbosenessLevel;
    private String playerScores[][];
    public boolean isMishti = false; // pişti puanı hesaplanırken kullan!
    public Game(String pointFilePath,int playerCount,ArrayList<String> playerNames,ArrayList<String> playerExpertise,boolean verbosenessLevel){
        this.pointFilePath = pointFilePath;
        this.playerCount = playerCount;
        this.playerNames = playerNames;
        this.playerExpertise = playerExpertise;
        this.verbosenessLevel = verbosenessLevel;
    }
    public void InitPlayers(int playerCount){
        for(int i=0;i<playerCount;i++){
            if(playerExpertise.get(i).equals("H") || playerExpertise.get(i).equals("h")){
                players[i] = new HumanPlayer(playerNames.get(i),playerExpertise.get(i));
            }
            if(playerExpertise.get(i).equals("N") || playerExpertise.get(i).equals("n")){
                players[i] = new NovicePlayer(playerNames.get(i),playerExpertise.get(i));
            }
            if(playerExpertise.get(i).equals("R") || playerExpertise.get(i).equals("r")){
                players[i] = new RegularPlayer(playerNames.get(i),playerExpertise.get(i));
            }
            if(playerExpertise.get(i).equals("E") || playerExpertise.get(i).equals("e")){
                players[i] = new ExpertPlayer(playerNames.get(i),playerExpertise.get(i));
            }
        }
    }

    public void InitGame(int playerCount,boolean isFirstGame){
        createDeck();
        cutDeck();
        shuffleDeck();
        players = new Player[playerCount];
        InitPlayers(playerCount);
        isFirstRound = true;
        if(!isFirstGame){
        playedCards.clear();
        table.clear();
        }
    //log temizleme
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
        ArrayList<Card> bottomHalf = new ArrayList<>(deck.subList(index, deck.size()));

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
           deal4Card(players[0]);
           deal4Card(players[1]);
       }
        if (playerCount==3){
            deal4Card(players[0]);
            deal4Card(players[1]);
            deal4Card(players[2]);
        }
        if (playerCount==4){
            deal4Card(players[0]);
            deal4Card(players[1]);
            deal4Card(players[2]);
            deal4Card(players[3]);
        }
    }
    public void addGround(Card thrownCard){ // Human and Bots
        playedCards.add(thrownCard);
        table.add(thrownCard);
    }


    public void cardCompare(Player player){

        int size = table.size();
        if (table.size()>2){
            if (table.get(size-1).getRank() == table.get(size-2).getRank() || table.get(size-1).getRank() == "J"){
                calculateScore(isMishti,player);
                table.clear();
            }
        }
        if (table.size() == 2){
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
            for (String[] playerScore : playerScores) {
                f.format("%s,%s\n", playerScore[0], playerScore[1]);
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
        for (String[] playerScore : playerScores) {
            if (playerScore[1] != null) {
                System.out.println(playerScore[0] + ": " + playerScore[1]);
            }
        }
    }

    public void ReadLeaderboard(boolean isNewHighScore) {
        FindPlayerCount(isNewHighScore);
        Scanner reader = null;
        String line = "";
        int playerCount = 0;
        try {
            Path path = Paths.get("leaderboard.txt");
            reader = new Scanner(path);
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
            Path path = Paths.get("leaderboard.txt");
            reader = new Scanner(path);

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
    public void GameLoop(int round) throws IOException {
        int roundCounter = 0;
        while(roundCounter<round) {
                InitGame(playerCount,isFirstGame);
            while(!deck.isEmpty()) {
                dealCard();
                for (int i = 0; i < 4; i++) {
                    for (Player p : players) {
                        playRound(p); // human parameter
                    }
                }
            }
            Player winner = null;
            for(Player p: players){
                if(winner == null || p.getPoints() > winner.getPoints()){
                    winner = p;
                }
            }
            System.out.println(roundCounter+1 + ". ROUND OVER");
            System.out.println("------------------------");
            CreateLeaderboard(winner);
            roundCounter++;
            isFirstGame = false;
            if(verbosenessLevel){
                System.out.println(verbose);
            }
            else {
                System.out.println(succint);
            }
        }
    }
}
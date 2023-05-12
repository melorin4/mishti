import java.io.*;
import java.util.*;

public class Game {
    public Player lastWinCards;
    public String cardsOnGround = "";
    public String verbose = "";
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
    public boolean isMishti = false;
    public Game(String pointFilePath,int playerCount,ArrayList<String> playerNames,ArrayList<String> playerExpertise,boolean verbosenessLevel){
        this.pointFilePath = pointFilePath;
        this.playerCount = playerCount;
        this.playerNames = playerNames;
        this.playerExpertise = playerExpertise;
        this.verbosenessLevel = verbosenessLevel;
    }
    public void InitPlayers(int playerCount){
        int humanCounter = 0;
        for (int i = 0; i < playerCount; i++) {
            if (playerExpertise.get(i).equals("H") || playerExpertise.get(i).equals("h")) {
                players[i] = new HumanPlayer(playerNames.get(i), playerExpertise.get(i));
                humanCounter++;
            }
            if (playerExpertise.get(i).equals("N") || playerExpertise.get(i).equals("n")) {
                players[i] = new NovicePlayer(playerNames.get(i), playerExpertise.get(i));
            }
            if (playerExpertise.get(i).equals("R") || playerExpertise.get(i).equals("r")) {
                players[i] = new RegularPlayer(playerNames.get(i), playerExpertise.get(i));
            }
            if (playerExpertise.get(i).equals("E") || playerExpertise.get(i).equals("e")) {
                players[i] = new ExpertPlayer(playerNames.get(i), playerExpertise.get(i));
            }
        }
        if(humanCounter>1){
            System.out.println("There can be only one human player.");
            throw new RuntimeException();
        }
        for(Player p:players){
            if(p == null) {
                System.out.println("Please check player name and expertise.");
                throw new RuntimeException();
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
   }

    public void  getDeckWithPoints() {
        for (Card card: this.deck) {
            System.out.println(card.cardName+" "+card.cardPoint);
        }

    }

    public void createDeck()
    {
        ArrayList<Card> localDeck = new ArrayList<Card>();
        String[] suits = {"♠", "♥", " ♦", "♣"};
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
        verbose += "{ ";
        for (int i=0; i<4; i++) {
            Card temp = pullCardFromDeck();
            verbose +=  temp.cardName + " ";
            player.getHand().add(temp);
        }
        verbose += "} Score: " + player.getPoints() + " ||| ";
    }
    public void dealCard(){
        if (isFirstRound){
            cardsOnGround += "Cards on table: ";
            for (int i=0; i<4; i++){
               Card temp = pullCardFromDeck();
               cardsOnGround += temp.getCardName() + " ";
               table.add(temp);
               playedCards.add(temp);
            }
            isFirstRound=false;
        }
       if (playerCount==2){
           verbose += "Player1: ";
           deal4Card(players[0]);
           verbose += "Player2: ";
           deal4Card(players[1]);
       }
        if (playerCount==3){
            verbose += "Player1: ";
            deal4Card(players[0]);
            verbose += "Player2: ";
            deal4Card(players[1]);
            verbose += "Player3: ";
            deal4Card(players[2]);
        }
        if (playerCount==4){
            verbose += "Player1: ";
            deal4Card(players[0]);
            verbose += "Player2: ";
            deal4Card(players[1]);
            verbose += "Player3: ";
            deal4Card(players[2]);
            verbose += "Player4: ";
            deal4Card(players[3]);
        }
    }
    public void addGround(Card thrownCard){
        playedCards.add(thrownCard);
        table.add(thrownCard);
    }


    public void cardCompare(Player player){

        int size = table.size();
        if (table.size()>2){
            if (table.get(size-1).getRank() == table.get(size-2).getRank() || table.get(size-1).getRank() == "J"){
                calculateScore(isMishti,player);
                table.clear();
                lastWinCards = player;
            }
        }
        if (table.size() == 2){
            if (table.get(size-1).getRank() == table.get(size-2).getRank()){
                isMishti = true;
                calculateScore(isMishti,player);
                table.clear();
                lastWinCards = player;
            }
            if (isMishti = false){
                if (table.get(size-1).getRank() == "J"){
                    calculateScore(isMishti,player);
                    table.clear();
                    lastWinCards = player;
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
        verbose += thrownCard.cardName + " ";
        addGround(thrownCard);
        cardCompare(player);
    }

    public void CreateLeaderboard(Player player) throws IOException {
        File leaderboardFile = new File("leaderboard.txt");
        if (!leaderboardFile.exists()) {
            leaderboardFile.createNewFile();
        }
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
            System.out.println("------------------------");
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
        int playerCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"))){
            String line;
            while ((line = reader.readLine())!= null) {
                String[] info = line.split(",");
                playerScores[playerCount][0] = info[0];
                playerScores[playerCount][1] = info[1];
                playerCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FindPlayerCount(boolean isNewHighScore) {
        int playerCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"))){
            String line;
            while ((line = reader.readLine())!= null) {
                playerCount++;
            }
            if (playerCount >= 10) {
                playerCount = 10;
            } else if (isNewHighScore) {
                playerCount++;
            }
            playerScores = new String[playerCount][2];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GameLoop(int round) throws IOException {

        int roundCounter = 0;
        int handCounter = 1;
        while(roundCounter<round) {
                InitGame(playerCount,isFirstGame);
            System.out.println(roundCounter+1 + ". ROUND BEGINS");
            while(!deck.isEmpty()) {
                verbose += "\nHand " + handCounter + ": ";
                dealCard();
                for (int i = 0; i < 4; i++) {
                    verbose += "\n  " + (i+1) + ". " ;
                    for (Player p : players) {
                        playRound(p); // human parameter
                    }
                }
                handCounter++;
            }
            if(table.size() > 0){
                calculateScore(false,lastWinCards);
            }
            Player winner = null;
            for(Player p: players){
                if(winner == null || p.getPoints() > winner.getPoints()){
                    winner = p;
                }
            }

            handCounter = 1;
            System.out.println(roundCounter+1 + ". ROUND OVER");

            System.out.println("------------------------");
            System.out.println(winner.getName() + " WINS!!");
            System.out.println("------------------------");
            CreateLeaderboard(winner);
            roundCounter++;
            isFirstGame = false;
            if(verbosenessLevel){
                verbose = cardsOnGround + verbose;
                System.out.println(verbose);
                verbose = "";
                cardsOnGround = "";
            }
            System.out.print("Score List: ");
            for(Player i: players) {
                System.out.print(i.getName() + ": " + i.getPoints() + " | ");
            }
            System.out.println("\n------------------------");
        }
    }
}
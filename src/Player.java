import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Player  {
    public Scanner sc = new Scanner(System.in);
    public Random r = new Random();
    private ArrayList<Card> hand;
    private String name;
    private int points = 0;
    private String expertLevel;
    public String[][] seenCards = null;

    public Player(String name,String expertLevel){
        this.name = name;
        this.expertLevel = expertLevel;
    }



    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public String getExpertLevel() {
        return expertLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpertLevel(String expertLevel) {

    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Card throwCard(ArrayList<Card> table,ArrayList<Card> playedCards){
        return null;
    }

    public int calculateTableScore(ArrayList<Card> table){
        int totalScore = 0;
        for(Card i:table){
            totalScore += i.cardPoint;
        }
        return totalScore;
    }
    public void trackCards(ArrayList<Card> playedCards){
        seenCards = new String[][] {{"A","0"},{"2", "0"}, {"3", "0"},{"4", "0"},{"5", "0"},{"6", "0"},{"7", "0"},{"8", "0"},{"9", "0"},{"10", "0"},{"J", "0"},{"Q", "0"},{"K", "0"},};
        for(Card i:hand){
            for(int j=0;j<13;j++){
                if(i.rank == seenCards[j][0]){
                    seenCards[j][1] = Integer.toString(Integer.parseInt(seenCards[j][1]) + 1);
                }
            }
        }
        for(Card i:playedCards){
            for(int j=0;j<13;j++){
                if(i.rank == seenCards[j][0]){
                    seenCards[j][1] = Integer.toString(Integer.parseInt(seenCards[j][1]) + 1);
                }
            }
        }
    }

}

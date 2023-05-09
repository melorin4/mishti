import java.util.ArrayList;
import java.util.Random;

public abstract class Player  {
    public Random r = new Random();
    private ArrayList<Card> hand;
    private String name;
    private int points = 0;
    private String expertLevel;

    public Player(){
    }

    public Player(ArrayList<Card> hand, String name, int points, String expertLevel){
        this.hand = hand;
        this.name = name;
        this.points = points;
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


}

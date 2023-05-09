import java.util.ArrayList;

public abstract class Player  {
    //Game game = new Game(); // why
    private ArrayList<Card> hand;
    private String name;
    private int points;
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

    public void setPoints(int points) {
        this.points = points;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void throwCard(){

    }


}

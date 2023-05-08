import java.util.ArrayList;

public class HumanPlayer extends Player {

    public HumanPlayer(ArrayList<Card> hand, String name, int points, String expertLevel){
        super(hand, name, points, expertLevel);

    }

    public void throwCard(int cardNum){
        addGround(player1.getHand().get(cardNum-1));
        cardCompare();
        player1.getHand().remove(cardNum-1);
    }


}

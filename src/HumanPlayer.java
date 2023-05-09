import java.util.ArrayList;

public class HumanPlayer extends Player {

    public HumanPlayer(ArrayList<Card> hand, String name, int points, String expertLevel){
        super(hand, name, points, expertLevel);

    }


    public Card throwCard(int cardNum){
        Card thrownCard = getHand().get(cardNum-1);
        getHand().remove(cardNum-1);
        return thrownCard;
    }




}

import java.util.ArrayList;

public class NovicePlayer extends Player  {

    public NovicePlayer(ArrayList<Card> hand, String name, int points, String expertLevel){
        super(hand, name, points, expertLevel);

    }


    @Override
    public Card throwCard(ArrayList<Card> table,ArrayList<Card> playedCards) {
        int random = r.nextInt(0,getHand().size());
            Card thrownCard = getHand().get(random);
            getHand().remove(random);
            return thrownCard;
        }
    }



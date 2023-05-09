import java.util.ArrayList;

public class NovicePlayer extends Player  {

    public NovicePlayer(String name,String expertLevel){
        super(name,expertLevel);

    }


    @Override
    public Card throwCard(ArrayList<Card> table,ArrayList<Card> playedCards) {
        int random = r.nextInt(0,getHand().size());
            Card thrownCard = getHand().get(random);
            getHand().remove(random);
            return thrownCard;
        }
    }



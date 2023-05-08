import java.util.ArrayList;

public class NovicePlayer extends Player  {

    public NovicePlayer(ArrayList<Card> hand, String name, int points, String expertLevel){
        super(hand, name, points, expertLevel);

    }

    @Override
    public void throwCard() {
        int random = ran.nextInt(0,getHand().size());
            addGround(getHand().get(random));
            cardCompare();
            getHand().remove(random);
        }
    }



import java.util.ArrayList;

public class RegularPlayer extends Player{

    public RegularPlayer(ArrayList<Card> hand, String name, int points, String expertLevel){
        super(hand, name, points, expertLevel);

    }

    @Override
    public Card throwCard(ArrayList<Card> table,ArrayList<Card> playedCards){
        int cardsOnTable = table.size();
        Card bestCard = null;
        for (Card i : getHand()) {
            if(table.size()>0) {
                if (table.get(cardsOnTable - 1).rank == i.rank) {
                    if (bestCard == null || i.cardPoint > bestCard.cardPoint) {
                        bestCard = i;
                    }
                }
            }
        }
        for(Card i : getHand()){
            if(table.size()>0){
                if(table.get(cardsOnTable-1).rank == i.rank){
                    if(calculateTableScore(table) > 0){
                        getHand().remove(bestCard);
                        return bestCard;
                    }
                }
            }
        }
        Card worstCard = getHand().get(0);
        for(Card i : getHand()) {
            if(i.cardPoint < worstCard.cardPoint){
                worstCard = i;
            }
        }
        getHand().remove(worstCard);
        return worstCard;
    }
}

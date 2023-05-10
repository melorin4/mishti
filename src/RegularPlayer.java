import java.util.ArrayList;

public class RegularPlayer extends Player{

    public RegularPlayer(String name,String expertLevel){
        super(name,expertLevel);

    }

    @Override
    public Card throwCard(ArrayList<Card> table,ArrayList<Card> playedCards){
        int cardsOnTable = table.size();
        Card bestCard = null;
        for (Card i : getHand()) {
            if(table.size()>0) {
                if (table.get(cardsOnTable - 1).rank.equals(i.rank) || i.rank.equals("J")){
                    if (bestCard == null || i.cardPoint > bestCard.cardPoint) {
                        bestCard = i;
                    }
                }
            }
        }
        for(Card i : getHand()){
            if(table.size()>0){
                if(table.get(cardsOnTable-1).rank.equals(i.rank) || i.rank.equals("J")){
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

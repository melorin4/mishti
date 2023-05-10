import java.util.ArrayList;

public class ExpertPlayer extends Player {

    public ExpertPlayer(String name,String expertLevel){
            super(name,expertLevel);
    }

        @Override
        public Card throwCard(ArrayList<Card> table,ArrayList<Card> playedCards){
            int cardsOnTable = table.size();
            Card bestCard = null;
            for (Card i : getHand()) {
                if(table.size()>0) {
                    if (i.rank.equals("J")) {
                        if (bestCard == null || i.cardPoint > bestCard.cardPoint) {
                            bestCard = i;
                        }
                    }
                }
            }
            for (Card i : getHand()) {
                if(table.size()>0) {
                    if (table.get(cardsOnTable - 1).rank.equals(i.rank)) {
                        if (bestCard == null || i.cardPoint > bestCard.cardPoint) {
                            bestCard = i;
                        }
                    }
                }
            }
            for(Card i:getHand()){
                if(table.size()>0){
                    if(table.get(cardsOnTable-1).rank.equals(i.rank) || i.rank.equals("J")){
                        if(calculateTableScore(table) > 0){
                            getHand().remove(bestCard);
                            return bestCard;
                        }
                    }
                }
            }
            Card worstCard = null;
            trackCards(playedCards);
            for(Card i : getHand()) {
                for(int j=0;j<13;j++){
                    if(i.rank.equals(seenCards[j][0]) && seenCards[j][1].equals("4")){
                        if(worstCard == null || i.cardPoint < worstCard.cardPoint) {
                            worstCard = i;
                        }
                    }
                }
            }
            if(worstCard != null) {
                getHand().remove(worstCard);
                return worstCard;
            }
            for(Card i : getHand()) {
                if(worstCard == null || i.cardPoint < worstCard.cardPoint){
                    worstCard = i;
                }
            }
            getHand().remove(worstCard);
            return worstCard;
        }
    
}



import java.util.ArrayList;

public class ExpertPlayer extends Player {

    public ExpertPlayer(ArrayList<Card> hand, String name, int points, String expertLevel){
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
            for(Card i:getHand()){
                if(table.size()>0){
                    if(table.get(cardsOnTable-1).rank == i.rank){
                        if(calculateTableScore(table) > 0){
                            return bestCard;
                        }
                    }
                }
            }
            Card worstCard = getHand().get(0);
            int[] seenCards = new int[getHand().size()];
            for (int i=0;i<seenCards.length;i++){
                seenCards[i] = 0;
            }
            for (int i = 0;i<getHand().size();i++) {
                for (Card j:table) {
                    if(getHand().get(i).rank==j.rank){
                        seenCards[i]++;
                    }
                }
            }
            for (int i = 0;i<getHand().size();i++) {
                for (Card j:playedCards) {
                    if(getHand().get(i).rank==j.rank){
                        seenCards[i]++;
                    }
                }
            }
            for(Card i : getHand()) {
                if(i.cardPoint < worstCard.cardPoint){
                    worstCard = i;
                }
            }
            return worstCard;
        }
    
}



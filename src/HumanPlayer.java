import java.util.ArrayList;

public class HumanPlayer extends Player {

    public HumanPlayer(String name,String expertLevel){
        super(name,expertLevel);

    }


    public Card throwCard(ArrayList<Card> table,ArrayList<Card> playedCards){
        System.out.println("------------------------------");
        String groundInfo = "Last thrown card: ";
        if (table.size() > 0) {
            groundInfo += table.get(table.size()-1).getCardName();
        } else {
            groundInfo += "no cards left";
        }
        System.out.println(groundInfo + " | Amount of cards on the ground: " + table.size());
        String playerHand = "Player: ";
        for (int i = 0; i < getHand().size(); i++) {
            playerHand += (i + 1) + ".card:" + getHand().get(i).getCardName() + " | ";
        }
        playerHand += "score:  " + getPoints();
        System.out.println(playerHand);
        System.out.println("Please enter the number of the card you want to throw: ");
        int cardNum = sc.nextInt();
        Card thrownCard = getHand().get(cardNum-1);
        getHand().remove(thrownCard);
        return thrownCard;
    }




}

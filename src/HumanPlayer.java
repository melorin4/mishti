import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, String expertLevel) {
        super(name, expertLevel);

    }

    public Card throwCard(ArrayList<Card> table, ArrayList<Card> playedCards) {
        while (true) {
            System.out.println("--------------------- (Enter 9 to exit) ---------------------");
            String groundInfo = "Last thrown card: ";
            if (table.size() > 0) {
                groundInfo += table.get(table.size() - 1).getCardName();
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
            try {
                int cardNum = sc.nextInt();
                if(cardNum == 9){
                    System.out.println("Shutting down...");
                    System.exit(0);
                }
                Card thrownCard = getHand().get(cardNum - 1);
                getHand().remove(thrownCard);
                return thrownCard;
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please enter a valid card number.");
                sc.nextLine();
            }
        }
    }
}


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Card {
    public String suit;
    public String rank;
    public String cardName;
    public int cardPoint;

    public Card(String suit, String rank, String pointFilePath) {
        this.suit = suit;
        this.rank = rank;
        this.cardName = suit + " " + rank;
        this.cardPoint = this.getCardPoint(pointFilePath);
    }

    public String getSuit() {
        return suit;
    }

    public String getCardName(){
        return cardName;
    }

    public String getRank() {
        return rank;
    }

    // file reading
    public int getCardPoint(String filename) {
        int defaultPoint = 10; // default point
        try {
            File file = new File(filename);
            // "C:\\Users\\selin\\OneDrive\\Desktop\\MySE116Project\\mishti\\src\\PointFile"
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                 String line = scanner.nextLine();
                 if (line.startsWith(suit) && line.substring(1,3).contains(rank)) {
                     String[] parts = line.split("\\s+");
                     return Integer.parseInt(parts[1]);

                 } else if (line.startsWith(suit) && line.substring(1).contains("*")){
                     String[] parts = line.split("\\s+");
                     return Integer.parseInt(parts[1]);

                } else if (line.startsWith("*") && line.substring(1,3).contains(rank)){
                    String[] parts = line.split("\\s+");
                     return Integer.parseInt(parts[1]);

                } else if (line.startsWith("**")){
                    String[] parts = line.split("\\s+");
                     return Integer.parseInt(parts[1]);

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return defaultPoint;
    }
}

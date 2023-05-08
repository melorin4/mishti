import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Card {
    public String suit;
    public String value;
    public String cardName;
    public int cardPoint;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.cardName = suit + " " + value;
        this.cardPoint = getCardPoint("PointFile.txt");
    }

    public String getSuit() {
        return suit;
    }

    public String getCardName(){
        return cardName;
    }

    public String getValue() {
        return value;
    }

    // file reading
    public int getCardPoint(String filename) {
        int defaultPoint = 1; // default point value
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(new File("C:\\Users\\melorin\\Documents\\GitHub\\mishti\\src\\PointFile"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(suit) && (line.substring(1).contains(value) || line.endsWith("*"))) {
                    // found a matching line
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

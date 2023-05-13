import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Card {
    public String suit;
    public String rank;
    public String cardName;
    public int cardPoint;

    public Card(String suit, String rank, String pointFilePath) {
        this.suit = suit;
        this.rank = rank;
        this.cardName = suit + rank;
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
    public int getCardPoint(String pointFileName) {
        Path path = Paths.get(pointFileName);
        File file = path.toFile();
        int defaultPoint = 1; // default point
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null) {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return defaultPoint;
    }
}

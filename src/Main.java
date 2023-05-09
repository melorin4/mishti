import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("").toAbsolutePath();
        String pointFilePath = path.normalize().toString()+ "\\src\\PointFile";

        if (args.length >=1 ){
            pointFilePath = args[0];
        }

        Game game = new Game(pointFilePath);
        game.createDeck();

        //game.printDeck();
        System.out.println("------------------------------------");
        //game.cutDeck();
        //game.printDeck();

        //game.InitGame(3);

        System.out.println();

        /*
        Card card1 = new Card("♥", "3"); // -10
        System.out.println(card1.getCardPoint("PointFile.txt"));
        Card card2 = new Card("♥", "4"); // 2
        System.out.println(card2.getCardPoint("PointFile.txt"));
        Card card3 = new Card("♥", "5"); // 2
        System.out.println(card3.getCardPoint("PointFile.txt"));
        Card card4 = new Card("♦", "5"); // -1
        System.out.println(card4.getCardPoint("PointFile.txt"));
        Card card5 = new Card("♣", "7"); // 0
        System.out.println(card5.getCardPoint("PointFile.txt"));

*/
        game.getDeckWithPoints();



    }
}
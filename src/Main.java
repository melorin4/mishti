import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = null;
        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<String> playerExpertise = new ArrayList<>();
        Path path = Path.of("").toAbsolutePath();
        String pointFilePath = path.normalize().toString()+ "\\src\\PointFile";
        if (args.length >=1 ){
            pointFilePath = args[0];
        }
        try {
            switch (args[1]) {
                case "2":
                    playerNames.add(args[3]);
                    playerNames.add(args[5]);
                    playerExpertise.add(args[4]);
                    playerExpertise.add(args[6]);
                    game = new Game(pointFilePath, Integer.parseInt(args[1]), playerNames, playerExpertise, Boolean.parseBoolean(args[7]));
                    break;
                case "3":
                    playerNames.add(args[3]);
                    playerNames.add(args[5]);
                    playerNames.add(args[7]);
                    playerExpertise.add(args[4]);
                    playerExpertise.add(args[6]);
                    playerExpertise.add(args[8]);
                    game = new Game(pointFilePath, Integer.parseInt(args[1]), playerNames, playerExpertise, Boolean.parseBoolean(args[9]));
                case "4":
                    playerNames.add(args[3]);
                    playerNames.add(args[5]);
                    playerNames.add(args[7]);
                    playerNames.add(args[9]);
                    playerExpertise.add(args[4]);
                    playerExpertise.add(args[6]);
                    playerExpertise.add(args[8]);
                    playerExpertise.add(args[10]);
                    game = new Game(pointFilePath, Integer.parseInt(args[1]), playerNames, playerExpertise, Boolean.parseBoolean(args[11]));
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            System.err.println("Please enter inputs in the correct order: *point file name* *number of players* *round* *name + expertise level for each player* *verbose mode(true/false)*");
        }
        game.GameLoop(Integer.parseInt(args[2]));


    }
}
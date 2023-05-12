import java.io.*;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws IOException {
        Game game = null;
        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<String> playerExpertise = new ArrayList<>();
        try {
            String pointFilePath = args[0];
            File pointFile = new File(pointFilePath);
            if (!pointFile.exists()) {
                System.out.println("Point file does not exist.");
                throw new Exception();
            }
            int round = Integer.parseInt(args[2]);
            if(round<1){
                System.out.println("Round cannot be smaller than 1");
                throw new Exception();
            }
            switch (args[1]) {
                case "2":
                    if(args.length < 8){
                        System.out.println("Missing input. Please make sure you enter all of the arguments required.");
                        throw new Exception();
                    }
                    if(!args[7].equals("true")) {
                        if(!args[7].equals("false")) {
                            System.out.println("Please enter 'true' or 'false' for verbose mode.");
                            throw new Exception();
                        }
                    }
                    playerNames.add(args[3]);
                    playerNames.add(args[5]);
                    playerExpertise.add(args[4]);
                    playerExpertise.add(args[6]);
                    game = new Game(pointFilePath, Integer.parseInt(args[1]), playerNames, playerExpertise, Boolean.parseBoolean(args[7]));
                    game.GameLoop(Integer.parseInt(args[2]));
                    break;
                case "3":
                    if(args.length < 10){
                        System.out.println("Missing input. Please make sure you enter all of the arguments required.");
                        throw new Exception();
                    }
                    if(!args[9].equals("true")) {
                        if(!args[9].equals("false")) {
                            System.out.println("Please enter 'true' or 'false' for verbose mode.");
                            throw new Exception();
                        }
                    }
                    playerNames.add(args[3]);
                    playerNames.add(args[5]);
                    playerNames.add(args[7]);
                    playerExpertise.add(args[4]);
                    playerExpertise.add(args[6]);
                    playerExpertise.add(args[8]);
                    game = new Game(pointFilePath, Integer.parseInt(args[1]), playerNames, playerExpertise, Boolean.parseBoolean(args[9]));
                    game.GameLoop(Integer.parseInt(args[2]));
                    break;
                case "4":
                    if(args.length < 12){
                        System.out.println("Missing input. Please make sure you enter all of the arguments required.");
                        throw new Exception();
                    }
                    if(!args[11].equals("true")) {
                        if(!args[11].equals("false")) {
                            System.out.println("Please enter 'true' or 'false' for verbose mode.");
                            throw new Exception();
                        }
                    }
                    playerNames.add(args[3]);
                    playerNames.add(args[5]);
                    playerNames.add(args[7]);
                    playerNames.add(args[9]);
                    playerExpertise.add(args[4]);
                    playerExpertise.add(args[6]);
                    playerExpertise.add(args[8]);
                    playerExpertise.add(args[10]);
                    game = new Game(pointFilePath, Integer.parseInt(args[1]), playerNames, playerExpertise, Boolean.parseBoolean(args[11]));
                    game.GameLoop(Integer.parseInt(args[2]));
                    break;
                default:
                    System.out.println("Invalid number of players. Player amount can be 2 to 4.");
                    throw new Exception();
            }
        }catch (Exception e){
            System.out.println("Please enter inputs in the correct order: *point file name* *number of players* *round* *name + expertise level for each player* *verbose mode(true/false)*");
        }
    }
}
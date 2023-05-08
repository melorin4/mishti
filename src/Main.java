public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.createDeck();
        game.printDeck();
        System.out.println("------------------------------------");
        game.cutDeck();
        game.printDeck();

        game.InitGame(3);
    }
}
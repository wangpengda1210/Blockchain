import java.util.*;     

/** Observable */

/** Concrete Observable */
class RockstarGames {

    public String releaseGame;
    /** write your code here ... */

    public void release(String game) {
        this.releaseGame = game;
        /** write your code here ... */
    }

}

/** Observer */

/** Concrete Observer */
class Gamer {

    private String name;
    private String reaction;
   /** write your code here ... */

    private Set<String> games = new HashSet<>();

    public Gamer(String name,String reaction,/** */) {
        this.reaction = reaction;
        /** write your code here ... */
        this.name = name;
    }

    /** write your code here ... */

    public void buyGame(String game) {
        games.add(game);
        System.out.println(reaction);
    }

    @Override
    public String toString() {
        return this.name;
    }    
}

/** Main Class */

public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        String game = null;

        /** write your code here ... */

        Gamer garry = new Gamer("Garry Rose", "I want to pre-order", rockstarGames);
        Gamer peter = new Gamer("Peter Johnston", "Pinch me...", rockstarGames);
        Gamer helen = new Gamer("Helen Jack", "Jesus, it's new game from Rockstar!", rockstarGames);

        /** write your code here ... */

        game = scanner.nextLine();
        System.out.println("It's happened! RockstarGames releases new game - " + game + "!");

        /** write your code here ... */ 

        scanner.close();
    }
}
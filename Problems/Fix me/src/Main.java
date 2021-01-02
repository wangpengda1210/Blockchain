import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/** Observable */
interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

/** Concrete Observable */
class RockstarGames implements Observable {

    public String releaseGame;
    /** write your code here ... */
    ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            System.out.println("Inform message to : " + observer);
            observer.update();
        }
    }

    public void release(String game) {
        this.releaseGame = game;
        /** write your code here ... */
        notifyObservers();
    }

    @Override
    public String toString() {
        return this.releaseGame;
    }

}

/** Observer */
interface Observer {
    void update();
}

/** Concrete Observer */
class Gamer implements Observer {

    private String name;
    private String reaction;
    /** write your code here ... */
    private Observable observable;

    private Set<String> games = new HashSet<>();

    public Gamer(String name, String reaction, Observable observable) {
        this.reaction = reaction;
        /** write your code here ... */
        this.observable = observable;
        this.name = name;
    }

    /** write your code here ... */

    public void buyGame(String game) {
        games.add(game);
        System.out.println(name + " says: " + reaction);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void update() {
        buyGame(observable.toString());
    }

}

/** Main Class */

public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        String game = null;

        /** write your code here ... */
        RockstarGames rockstarGames = new RockstarGames();

        Gamer garry = new Gamer("Garry Rose", "I want to pre-order", rockstarGames);
        Gamer peter = new Gamer("Peter Johnston", "Pinch me...", rockstarGames);
        Gamer helen = new Gamer("Helen Jack", "Jesus, it's new game from Rockstar!", rockstarGames);

        /** write your code here ... */
        rockstarGames.addObserver(garry);
        rockstarGames.addObserver(peter);
        rockstarGames.addObserver(helen);

        game = scanner.nextLine();
        System.out.println("It's happened! RockstarGames releases new game - " + game + "!");

        /** write your code here ... */
        rockstarGames.release(game);

        scanner.close();
    }
}
package textbasedadventurejj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunGame {

    static boolean gameIsRunning;

    private RunGame() {
    }

    public static String setDirectory() throws IOException {
        File currentDirFile = new File(".");
        String path = currentDirFile.getAbsolutePath();
        path+="\\beta\\";
        return path;
    }

    public static void loadSavedGame() {
        try {
            String events = Utils.readFile(Constants.ROOT + Constants.SAVE_FILE);
            events = events.trim();
            String[] eventArr = events.split("\n");
            for (String element : eventArr) {
                Interpreter.getInstance().interpret(element);
            }
        } catch (IOException ex) {
            File file = new File(Constants.ROOT + Constants.SAVE_FILE);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printNewGameText() {
        System.out.println("WELCOME TO JERRY AND JARED'S TEXT BASAED ADVENTURE!");
        System.out.println("Here's some basic tips:");
        System.out.println("In general to travel between rooms you have to type \"enter door.\" If there is more than door in a room, type something like \"enter hallway\".");
        System.out.println("When in doubt, type \"look around\" or \"examine\" to examine your surroundings and objects and \"take\" to take items.");
        System.out.println("Make sure you start each command you type in with a verb, like \"say yes\" or \"open door.\" Avoid typing in articles like \"the\" and if something doesn't work, try using a synonym.");
        System.out.println("Your game automatically saves progress. If you want to exit game, type \"exit.\" Typing \"inventory\" will show your current items. If you want to restore to a new game, type \"restore.\"");
        System.out.println("");
        System.out.println("CLOSET");
    }

    public static void newGame() {
        System.out.println("Your game is being restarted.\n\n\n\n");
        printNewGameText();
        gameIsRunning = false;
        File file = new File(Constants.ROOT + Constants.SAVE_FILE);
        file.delete();

        runGame();
    }

    public static void exitGame() {
        System.out.println("Thank you for playing.");
        gameIsRunning = false;
    }

    public static void runGame() {

        GameObjectManager gmanager = GameObjectManager.getInstance();
        LocationManager lmanager = LocationManager.getInstance();

        gmanager.loadObjects();
        lmanager.load();

        lmanager.setContext(LocationManager.getInstance().getSubLocation("World.Ship.Closet"));
        try {
            loadSavedGame();
            gameIsRunning = true;
            Scanner scanner = new Scanner(System.in);
            while (gameIsRunning) {
                String nextLine = "do " + scanner.nextLine() + " player";
                Interpreter.getInstance().interpret(nextLine);
                Utils.writeEvent(nextLine);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

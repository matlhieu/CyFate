package fr.mathieu;

import java.util.Scanner;

/**
 * Application entry point
 */
public class Main {

    // --- Main ---

    /**
     * Game loop controller
     * @param args Level files
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java -jar CyFate.jar <level1.txt> [level2.txt...]");
            System.exit(1);
        }

        Scanner input = new Scanner(System.in);
        boolean keepPlaying = true;

        System.out.print("Entrez le nom du joueur : ");
        String playerName = input.next();

        do {
            Level.resetLevelCount();
            Player player = new Player(playerName);
            boolean gameBeaten = true;

            for (int i = 0; i < args.length; i++) {
                String levelFile = args[i];
                Level currentLevel = new Level(player, levelFile);
                Level.nextLevel();

                while (Level.getNbGold() > 0 && player.getLife() > 0) {
                    currentLevel.generateLevel();
                    System.out.print("Movement Direction (zqsd) : ");
                    String key = input.next().toLowerCase();

                    Level.Direction dir = null;
                    switch (key) {
                        case "z": dir = Level.Direction.UP; break;
                        case "s": dir = Level.Direction.DOWN; break;
                        case "q": dir = Level.Direction.LEFT; break;
                        case "d": dir = Level.Direction.RIGHT; break;
                        default: continue;
                    }
                    currentLevel.movePlayer(dir);
                }

                if (player.getLife() <= 0) {
                    System.out.println("\nGAME OVER");
                    gameBeaten = false;
                    break;
                } else {
                    System.out.println("\nLevel completed !");
                }
            }

            if (gameBeaten) {
                System.out.println("\nThanks for playing ! All levels have been completed");
                keepPlaying = false;
            } else {
                System.out.println("Do you want to restart the game ? (y/n)");
                String answer = input.next().toLowerCase();
                if (!answer.equals("y")) {
                    keepPlaying = false;
                }
            }
        } while (keepPlaying);
        input.close();
    }
}
package fr.mathieu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean keepPlaying = true;

        do {
            Player alice = new Player("Alice");
            Level level1 = new Level(alice);

            while (Level.getNbGold() > 0 && alice.getLife() > 0) {
                level1.generateLevel();

                System.out.print("Movement Direction (zqsd) : ");
                String key = input.next().toLowerCase();

                Level.Direction dir = null;
                switch (key) {
                    case "z": dir = Level.Direction.UP; break;
                    case "s": dir = Level.Direction.DOWN; break;
                    case "q": dir = Level.Direction.LEFT; break;
                    case "d": dir = Level.Direction.RIGHT; break;
                    default:
                        System.out.println("Error : Invalid direction");
                        continue;
                }

                level1.movePlayer(dir);
            }

            if (alice.getLife() <= 0) {
                System.out.println("\nGAME OVER");
                System.out.println("Do you want to restart the game ? (y/n)");
                String answer = input.next().toLowerCase();
                if (!answer.equals("y")) {
                    keepPlaying = false;
                }
            } else {
                System.out.println("\nLevel completed !");
                keepPlaying = false;
            }

        } while (keepPlaying);

        input.close();
    }
}
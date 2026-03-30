package fr.mathieu;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Files;

public class Level {
    private Cell[][] grid;
    private int rows;
    private int cols;
    private static int numberlevel = 1;
    private static Player player;
    private static int nbgold;
    private int startpX;
    private int startpY;
    private int pX;
    private int pY;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    /**
     * Main Constructor
     * @param player
     */
    public Level(Player player, String filepath) {
        nbgold = 0;
        this.player = player;
        numberlevel++;
        Path path = Path.of(filepath);

        try {
            List<String> lines = Files.readAllLines(path);

            if (lines.isEmpty()) throw new RuntimeException("File is empty");
            this.rows = lines.size();
            this.cols = lines.get(0).length();
            this.grid = new Cell[rows][cols];

            boolean playerFound = false;

            for (int i = 0; i < rows; i++) {
                String line = lines.get(i);
                int limit = Math.min(line.length(), cols);

                for (int j = 0; j < cols; j++) {
                    char c = (j < limit) ? line.charAt(j) : ' ';

                    Cell.Type type = Cell.Type.EMPTY;
                    boolean gold = false;

                    if (c =='#'){
                        type = Cell.Type.WALL;
                    }

                    else if (c == 'D'){
                        type = Cell.Type.DOOR;
                    }

                    else if ( c == '*'){
                        type = Cell.Type.TRAP;
                    }

                    else if (c == '.'){
                        gold = true;
                        nbgold++;
                    }

                    else if (c == '1') {
                        this.pX = j;
                        this.pY = i;
                        this.startpX = j;
                        this.startpY = i;
                        playerFound = true;
                    }
                    this.grid[i][j] = new Cell(i, j, type, gold);
                }
            }

            if (!playerFound) {
                throw new RuntimeException("Player not found in the file");
            }

        } catch (IOException e){
            System.err.println("Error reading file : " + e.getMessage());
            System.exit(1);
        }
    }

    public void generateLevel() {
        System.out.println("\n------------ Level " + numberlevel + " ------------");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == pY && j == pX) {
                    System.out.print("1 ");
                } else {
                    System.out.print(grid[i][j].getCharRepresentation() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("[Player] " + player.toString() + " | Position : [" + pX + "][" + pY + "]\n Gold on the map : " + nbgold);
    }

    public void movePlayer(Direction d) {
        int nextX = pX;
        int nextY = pY;

        switch (d) {
            case UP:    nextY -= 1; break;
            case DOWN:  nextY += 1; break;
            case LEFT:  nextX -= 1; break;
            case RIGHT: nextX += 1; break;
        }

        if (nextY >= 0 && nextY < rows && nextX >= 0 && nextX < cols) {
            Cell target = grid[nextY][nextX];
            if (target.isWalkable()) {
                System.err.println("The path is blocked !");
                return;
            }
            pX = nextX;
            pY = nextY;

            if (target.getHasgold()){
                nbgold--;
                player.addScore(10);
                target.setHasgold(false);
            }

            if (target.getType() == Cell.Type.TRAP) {
                player.subLife();
                this.pX = startpX;
                this.pY = startpY;
            }

        } else {
            System.err.println("Out of boundaries of the map");
        }
    }
    public static int getNbGold() {
        return nbgold;
    }
}
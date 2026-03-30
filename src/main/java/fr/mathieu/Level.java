package fr.mathieu;

import java.io.IOException;
import java.util.List;
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

    public Level(Player player, String filepath) {
        nbgold = 0;
        Level.player = player;
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

                    if (c == '#') { type = Cell.Type.WALL; }
                    if (c == '*') { type = Cell.Type.TRAP; }
                    if (c == 'D') { type = Cell.Type.DOOR; }
                    if (c == '.') {
                        gold = true;
                        nbgold++;
                    }
                    if (c == '1') {
                        this.pX = j;
                        this.pY = i;
                        this.startpX = j;
                        this.startpY = i;
                        playerFound = true;
                    }

                    this.grid[i][j] = new Cell(i, j, type, gold);
                }
            }

            if (!playerFound) { throw new RuntimeException("Player not found"); }

        } catch (IOException e) {
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
        System.out.println("[Player] " + player.toString() + " | Gold : " + nbgold);
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

        // Logic Torique (Wrap-around)
        if (nextX < 0) {
            nextX = cols - 1;
        }
        if (nextX >= cols) {
            nextX = 0;
        }
        if (nextY < 0) {
            nextY = rows - 1;
        }
        if (nextY >= rows) {
            nextY = 0;
        }

        Cell target = grid[nextY][nextX];

        if (!target.isWalkable()) {
            System.err.println("The path is blocked !");
            return;
        }

        pX = nextX;
        pY = nextY;

        if (target.getHasGold()) {
            nbgold--;
            player.addScore(10);
            target.setHasGold(false);
        }

        if (target.getType() == Cell.Type.TRAP) {
            player.subLife();
            pX = startpX;
            pY = startpY;
        }
    }

    public static int getNbGold() { return nbgold; }

    public static void nextLevel() { numberlevel++; }

    public static void resetLevelCount() { numberlevel = 1; }
}
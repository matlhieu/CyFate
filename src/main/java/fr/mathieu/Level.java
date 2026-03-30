package fr.mathieu;

import java.io.IOException;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;

/**
 * Represents a game level
 */
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

    /**
     * Possible directions
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    // --- Constructor ---

    /**
     * Loads level from file
     * @param player player instance
     * @param filepath level file path
     */
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
            System.exit(1);
        }
    }

    // --- Getters ---

    /**
     * Get remaining gold
     * @return global count
     */
    public static int getNbGold() {
        return nbgold;
    }

    // --- Setters ---

    /**
     * Increment level count
     */
    public static void nextLevel() {
        numberlevel++;
    }

    /**
     * Reset level count to 0
     */
    public static void resetLevelCount() {
        numberlevel = 0;
    }

    // --- Methods ---

    /**
     * Display the level grid
     */
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

    /**
     * Move player with toroidal pac-man logic
     * @param d Direction
     */
    public void movePlayer(Direction d) {
        int nextX = pX;
        int nextY = pY;

        switch (d) {
            case UP:    nextY -= 1; break;
            case DOWN:  nextY += 1; break;
            case LEFT:  nextX -= 1; break;
            case RIGHT: nextX += 1; break;
        }

        if (nextX < 0) nextX = cols - 1;
        if (nextX >= cols) nextX = 0;
        if (nextY < 0) nextY = rows - 1;
        if (nextY >= rows) nextY = 0;

        Cell target = grid[nextY][nextX];

        if (target.isWalkable()) {
            pX = nextX;
            pY = nextY;

            if (target.getHasGold()) {
                nbgold--;
                player.addScore(10);
                target.setHasGold(false);
            }

            if (target.getType() == Cell.Type.TRAP) {
                target.setType(Cell.Type.EMPTY);
                player.subLife();
                pX = startpX;
                pY = startpY;
            }
        }
    }
}
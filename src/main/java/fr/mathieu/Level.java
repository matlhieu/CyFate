package fr.mathieu;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Files;

public class Level {
    private char[][] maze;
    private int rows;
    private int cols;
    private static int numberlevel = 1;
    private Player player;
    private int pX;
    private int pY;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Level(Player player) {
        this.player = player;
        Path path = Path.of("Layout.txt");

        try {
            List<String> lines = Files.readAllLines(path);

            if (lines.isEmpty()) throw new RuntimeException("File is empty");
            this.rows = lines.size();
            this.cols = lines.get(0).length();
            this.maze = new char[rows][cols];

            boolean playerFound = false;

            for (int i = 0; i < rows; i++) {
                String line = lines.get(i);
                int limit = Math.min(line.length(), cols);

                for (int j = 0; j < cols; j++) {
                    char c = (j < limit) ? line.charAt(j) : ' ';

                    if (c == '1') {
                        this.pX = j;
                        this.pY = i;
                        this.maze[i][j] = ' ';
                        playerFound = true;
                    } else {
                        this.maze[i][j] = c;
                    }
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
                    System.out.print(maze[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("[Player] " + player.toString() + " | Position : [" + pX + "][" + pY + "]");
    }

    public void addObstacle(int x, int y, int obstacle_length) {
        for (int i = 0; i < obstacle_length; i++) {
            if ((y > 0 && y < rows - 1 && x > 0 && x < cols - 1) && !(x == pX && y == pY)) {
                maze[y][x] = '#';
            }
            y++;
        }
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
            if (maze[nextY][nextX] != '#') {
                pX = nextX;
                pY = nextY;
            } else {
                System.err.println("A wall is on the path");
            }
        } else {
            System.err.println("Out of boundaries of the map");
        }
    }

    public static void main(String[] args) {
        Player alice = new Player("Alice");
        Level level1 = new Level(alice);

        Scanner input = new Scanner(System.in);

        while (true) {
            level1.generateLevel();

            System.out.print("Movement Direction (zqsd) : ");
            String key = input.next().toLowerCase();

            Direction dir = null;
            switch (key) {
                case "z": dir = Direction.UP; break;
                case "s": dir = Direction.DOWN; break;
                case "q": dir = Direction.LEFT; break;
                case "d": dir = Direction.RIGHT; break;
                default:
                    System.out.println("Error : Invalid direction");
                    continue;
            }

            level1.movePlayer(dir);
        }
    }
}
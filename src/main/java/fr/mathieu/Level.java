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

    private enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Level(Player player) {
        this.player = player;
        Path path = Path.of("C:\\Users\\cytech\\IdeaProjects\\CyFate\\Layout.txt");

        try {
            List<String> lines = Files.readAllLines(path);
            this.rows = lines.size();
            this.cols = lines.get(0).length();
            this.maze = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = lines.get(i);
                for (int j = 0; j < cols; j++) {
                    char c = line.charAt(j);
                    if (c == '1' || c == 'P') {
                        this.pX = j;
                        this.pY = i;
                        this.maze[i][j] = ' ';
                    } else {
                        maze[i][j] = c;
                    }
                }
            }
        } catch (IOException e){
            System.err.println("Error reading file : " + e.getMessage());
            System.exit(1);
        }
    }

    public void generateLevel() {
        System.out.println("\n------------ fr.mathieu.Level " + numberlevel + "------------");
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
        System.out.println("[fr.mathieu.Player] " + player.toString() + " | Position : [" + pX + "][" + pY + "]");
        numberlevel++;
    }

    public void addObstacle(int x, int y, int obstacle_length) {
        for (int i = 0; i < obstacle_length; i++) {
            if ((y > 0 && y < rows - 1 && x > 0 && x < cols - 1) && !(x == pX && y == pY)) {
                maze[y][x] = '#';
            }
            y++;
        }
    }

    public void movePlayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Movement Direction (zqsd) : ");
        String direction = input.next().toLowerCase();

        Direction d = null;

        switch (direction) {
            case "z": d = Direction.UP; break;
            case "s": d = Direction.DOWN; break;
            case "q": d = Direction.LEFT; break;
            case "d": d = Direction.RIGHT; break;
            default:
                System.out.println("Error : Invalid direction");
                return;
        }

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

        while (true) {
            level1.generateLevel();
            level1.movePlayer();
        }




    }
}

/*
            if (y < 0 || y >= rows || x < 0 || x >= cols) {
                throw new RuntimeException("Error : fr.mathieu.Player out of limits");
            }
            if (maze[y][x] == '#') {
                throw new RuntimeException("Error : fr.mathieu.Player can't be placed on a wall");
            }
            if (player == null) {
                throw new RuntimeException("Error : no player found");
            }

            this.player = player;
            this.pX = x;
            this.pY = y;
*/
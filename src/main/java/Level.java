/**
 * @author : Mathieu
 */

import java.util.Scanner;

public class Level {
    private final char[][] maze;
    private final int length;
    private final int width;
    private static int numberlevel = 1;
    private Player player;
    private int pX;
    int pY;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Level(int length, int width, Player player, int x, int y) {
        this.length = length;
        this.width = width;
        this.maze = new char[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == length - 1 || j == width - 1) { // add maze only on the border
                    maze[i][j] = '#';
                } else maze[i][j] = ' ';                                 //Empty the interior
            }
        }

        if (x < 0 || x >= length || y < 0 || y >= width) {
            throw new RuntimeException("Error : Player out of limits");
        }
        if (maze[x][y] == '#') {
            throw new RuntimeException("Error : Player can't be place on a wall");
        }
        if (player == null) {
            throw new RuntimeException("Error : no player found");
        }
        this.player = player;
        this.pX = x;
        this.pY = y;
    }

    public void addObstacle(int x, int y) {
        if (x > 0 && x < length - 1 && y > 0 && y < width - 1) {
            maze[x][y] = '#';
        }
    }

    public void generateLevel() {
        System.out.println("\n------------ Level " + numberlevel + "------------");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (i == pX && j == pY) {
                    System.out.print("1 ");
                } else {
                    System.out.print(maze[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("[Player] " + player.toString() + " | Position : [" + pX + "][" + pY + "]");
        numberlevel++;
    }

    public void movePlayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Movement Direction (zqsd) : ");
        String direction = input.next().toLowerCase();

        Direction d = null;

        switch (direction) {
            case "z":
                d = Direction.UP;
                break;
            case "s":
                d = Direction.DOWN;
                break;
            case "q":
                d = Direction.LEFT;
                break;
            case "d":
                d = Direction.RIGHT;
                break;
            default:
                System.out.println("Error : Invalid direction");
                break;

        }
        int nextX = pX;
        int nextY = pY;

        switch (d) {
            case UP:
                nextX -= 1;
                break;
            case DOWN:
                nextX += 1;
                break;
            case LEFT:
                nextY -= 1;
                break;
            case RIGHT:
                nextY += 1;
                break;
            default:
                System.out.println("Error : Invalid direction");
                break;
        }
        if (nextX >= 0 && nextX < length && nextY >= 0 && nextY < width) {
            if (maze[nextX][nextY] != '#') {
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

        Level level1 = new Level(10, 12, alice, 5, 8);
        level1.addObstacle(2, 5);

        for (int i = 0; i < 10; i++) {
            level1.generateLevel();
            level1.movePlayer();

        }


    }

}

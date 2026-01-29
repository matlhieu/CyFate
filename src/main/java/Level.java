import java.util.Scanner;

public class Level {
    private final char[][] maze;
    private final int rows;
    private final int cols;
    private static int numberlevel = 1;
    private Player player;
    private int pX;
    private int pY;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Level(int rows, int cols, Player player, int x, int y) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    maze[i][j] = '#';
                } else {
                    maze[i][j] = ' ';
                }
            }
        }

        if (y < 0 || y >= rows || x < 0 || x >= cols) {
            throw new RuntimeException("Error : Player out of limits");
        }
        if (maze[y][x] == '#') {
            throw new RuntimeException("Error : Player can't be placed on a wall");
        }
        if (player == null) {
            throw new RuntimeException("Error : no player found");
        }

        this.player = player;
        this.pX = x;
        this.pY = y;
    }

    public void generateLevel() {
        System.out.println("\n------------ Level " + numberlevel + "------------");
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

        Level level1 = new Level(7, 12, alice, 8, 2);
        level1.addObstacle(8, 1, 6);

        while (true) {
            level1.generateLevel();
            level1.movePlayer();
        }
    }
}
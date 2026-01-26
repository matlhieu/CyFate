/**
 * @author : Mathieu
 */

public class Level {
    private final char[][] maze;
    private final int length;
    private final int width;
    private static int numberlevel = 1;

    public Level(int length, int width){
        this.length = length;
        this.width = width;
        maze = new char[length][width];
        for(int i = 0; i < length; i++){
            for(int j = 0 ; j < width;j++){
                if(i == 0 || j == 0 || i == length-1 || j == width-1){ // add maze only on the border
                    maze[i][j] = '#';
                }
                else maze[i][j] = ' ';                                 //Empty the interior
            }
        }
    }

    public void addObstacle(int x, int y){
        if(x > 0 && x < length-1 && y > 0 && y < width-1){
            maze[x][y] = '#';
        }
    }

    public void generateLevel() {
        System.out.println("\n------------ Level " + numberlevel + "------------");
        for(int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(maze[i][j]+ " ");
            }
            System.out.println();
        }
        numberlevel++;
    }

    public void placePlayer(Player player){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if(maze[i][j] == '#'){

                }
                if ( maze[i][j] != '#' && (i == 0 || j == 0 || i == length-1 || j == width-1)){
                    maze[i][j] = '1';

                }
            }
        }
    }
    public static void main(String[] args){
        Level level1 = new Level(10, 12);
        level1.generateLevel();

        Level level2 = new Level(5, 18);
        level2.addObstacle(2,5);
        level2.generateLevel();


    }

}

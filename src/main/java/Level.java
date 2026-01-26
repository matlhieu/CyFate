/**
 * @author : Mathieu
 */

public class Level {
    private static char[][] wall;
    private static final int length = 20;
    private static final int width = 20;

    public Level(String level){
        wall = new char[length][width];
        for(int i = 0; i < length; i++){
            for(int j = 0 ; j < width;j++){
                wall[i][j] = level.charAt(i);

            }
        }
    }

    public void generateLevel(char[][] level) {
        for(int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                level[i][j] = wall[i][j];
                System.out.print(level[i][j] + " ");
            }
        }
    }


    public static void main(String[] args){
        System.out.println("Level 1");
        Level level1 = new Level("level 1");
        level1.generateLevel(wall);


    }

}

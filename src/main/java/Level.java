/**
 * @author : Mathieu
 */

public class Level {
    private char[][] wall;
    private int length;
    private int width;
    private static int numberlevel = 1;

    public Level(int length, int width){
        this.length = length;
        this.width = width;
        wall = new char[length][width];
        for(int i = 0; i < length; i++){
            for(int j = 0 ; j < width;j++){
                if(i == 0 || j == 0 || i == length-1 || j == width-1){ // add wall only on the border
                    wall[i][j] = '#';
                }
                else wall[i][j] = ' ';                                 //Empty the interior
            }
        }
    }

    public void addObstacle(int x, int y){
        if(x > 0 && x < length-1 && y > 0 && y < width-1){
            wall[x][y] = '#';
        }
    }

    public void generateLevel() {
        System.out.println("\n------------ Level " + numberlevel + "------------");
        for(int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(wall[i][j]+ " ");
            }
            System.out.println();
        }
        numberlevel++;
    }

    public void placePlayer(Player player){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if(wall[i][j] == '#'){

                }
                if ( wall[i][j] != '#' && (i == 0 || j == 0 || i == length-1 || j == width-1)){
                    wall[i][j] = '1';

                }
            }
        }
    }
    public static void main(String[] args){
        Level level1 = new Level(12, 12);
        level1.generateLevel();

        Level level2 = new Level(5, 12);
        level2.addObstacle(2,5);
        level2.generateLevel();


    }

}

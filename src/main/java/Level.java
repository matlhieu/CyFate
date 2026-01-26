/**
 * @author : Mathieu
 */

public class Level {
    private char[][] wall;
    private int length;
    private int width;
    private int numberlevel = 1;

    public Level(int length, int width){
        this.length = length;
        this.width = width;
        wall = new char[length][width];
        for(int i = 0; i < length; i++){
            for(int j = 0 ; j < width;j++){
                if(i == 0 || j == 0 || i == length-1 || j == width-1){
                    wall[i][j] = '#';
                }
                else wall[i][j] = ' ';
            }
        }
    }

    public void generateLevel() {
        System.out.println("------------ Level " + numberlevel + "------------");
        for(int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(wall[i][j]+ " ");
            }
            System.out.println();
        }
        numberlevel++;
    }



    public static void main(String[] args){
        Level level = new Level(12, 12);
        level.generateLevel();


    }

}

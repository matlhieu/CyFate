/**
 * @author : Mathieu
 */

public class Player{

    private final String name;
    private int score;
    private static int count = 0;
    private static final int MAX_PLAYERS = 100;
    private static Player[] allPlayers = new Player[MAX_PLAYERS];

    /**.
     * @param name Name of player
     */

    public Player(String name){         //Player struct
        this.name = name;
        this.score = 0;
        if (count < MAX_PLAYERS) {
            allPlayers[count] = this;
            System.out.println("New player " + this.toString());
            count++;
        } else {
            System.err.println("Error : Max capacity");
        }
    }

    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }

    public void addScore(int scores){          //Add score to the player
        this.score += scores;
    }

    public int subScore(int scores){        //Sub score to the player
        if (this.score - scores < 0){
            this.score = 0;
            return this.score;
        } else {
            return this.score -= scores;
        }
    }

    public String toString(){               //Level 4 : Show Name and Score
        if (this.score == 0 || this.score == 1){
            return this.name + " : " + this.score +" pt";
        }else {
            return this.name + " : " + this.score +" pts";
        }
    }

    public static void getCount(){       //Level 5
        System.out.println("Number of player : " + count);
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Player)){
            return false;
        }

        Player other = (Player) obj;

        if (this.name == null) {
            return other.name == null;
        }
        return this.name.equalsIgnoreCase(other.name);

    }

    public Player(){
        this("Joueur"+(count+1));
    }

    public static void showPlayers(){
        System.out.println("\n--- Player List ---");
        for (int i = 0; i < count; i++){
            System.out.println("~ "+ allPlayers[i].toString());
        }
    }

    public static void main(String[] args){
        Player.getCount();

        Player alice = new Player("Alice");            //Create a player with name : Alice
        Player bob = new Player("Bob");

        System.out.println("Game is starting...\n");

        System.out.println(alice);
        System.out.println(bob);

        System.out.println("\nModification\n");

        alice.addScore(1);
        bob.addScore(2);

        System.out.println(alice);
        System.out.println(bob);

        //Level 5 Part
        Player bobUpper = new Player("BOB");
        Player b = bob;

        System.out.println("Alice equals 'Alice' : " + alice.equals("Alice"));
        System.out.println("Alice equals Bob : " + alice.equals(bob));
        System.out.println("Bob equals BOB : " + bob.equals(bobUpper));
        System.out.println("Bob == BOB : " + (bob == bobUpper));
        System.out.println("Bob == b : " + (bob == b));

        Player p3 = new Player();
        Player p4 = new Player();

        Player.showPlayers();
        Player.getCount();

        alice = null; //Garbage
    }
}
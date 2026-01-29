/**
 * Represents a player.
 * @author Mathieu
 */
public class Player {

    private final String name;
    private int score;
    private static int count = 0;

    /**
     * Constructor with name.
     * @param name Player's name
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        count++;
    }

    /**
     * Default constructor.
     */
    public Player() {
        this("Player" + (count + 1));
    }

    // --- Getters ---

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public static int getCount() {
        return count;
    }

    // --- Methods ---

    /**
     * Add points to score.
     * @param points points to add (can be negative)
     */
    public void addScore(int points) {
        this.score += points;
        if (this.score < 0) {
            this.score = 0; // Prevent negative score
        }
    }

    /**
     * Remove points from score
     * @param points points to remove
     */
    public void subScore(int points) {
        this.score -= points;
        if (this.score < 0) {
            this.score = 0;
        }
    }

    /**
     * Player info
     * @return String format
     */
    @Override
    public String toString() {
        return name + " : " + score + (score > 1 ? " pts" : " pt");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)){
            return false;
        }
        Player other = (Player) obj;
        return this.name != null && this.name.equalsIgnoreCase(other.name);
    }

    // --- Main ---

    public static void main(String[] args) {
        System.out.println("Total created: " + Player.getCount());

        Player alice = new Player("Alice");            //Create a player with name : Alice
        Player bob = new Player("Bob");

        System.out.println("Game is starting...\n");

        System.out.println(alice);
        System.out.println(bob);

        System.out.println("\nModification\n");

        alice.addScore(1);
        bob.addScore(-2); // Test

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


        System.out.println("\nTotal created: " + Player.getCount());
    }
}
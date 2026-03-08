package fr.mathieu;
/**
 * Represents a player
 * @author Mathieu
 */
public class Player {

    private final String name;
    private int score;
    private static int count = 0;
    private int life = 5;

    /**
     * Constructor with name
     * @param name Player's name
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        count++;
    }

    /**
     * Default constructor
     */
    public Player() {
        this("Player" + (count + 1));
    }

    // --- Getters ---

    /**
     * Get Player Name
     * @return String format
     */
    public String getName() {
        return name;
    }

    /**
     * Get Player Score
     * @return Current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get Total Number of players
     * @return Global count
     */
    public static int getCount() {
        return count;
    }

    public int getLife() {
        return this.life;
    }

    // --- Methods ---

    /**
     * Add points to score
     * @param points points to add
     */
    public void addScore(int points) {
        this.score += points;
        if (this.score < 0) {
            this.score = 0; // Prevent negative score
        }
    }

    /**
     * Remove points from score
     */
    public void subScore() {
        this.score -= 2;
        if (this.score < 0) {
            this.score = 0;
        }
    }

    public void subLife() {
        this.life -= 2;
        if (this.life < 0) {
            this.life = 0;
        }
    }
    /**
     * Player info
     * @return String format
     */
    @Override
    public String toString() {
        return name + " : " + score + (score > 1 ? " pts" : " pt") + " | Life : " + life;
    }

    /**
     * Checks equality based on name
     * @param obj Object to compare
     * @return true if names are identical
     **/
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)){
            return false;
        }
        Player other = (Player) obj;
        return this.name.equalsIgnoreCase(other.name);
    }


}

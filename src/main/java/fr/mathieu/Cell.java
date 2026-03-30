package fr.mathieu;

/**
 * Represents a single grid cell
 */
public class Cell {

    /**
     * Cell possible types
     */
    public enum Type {
        TRAP, WALL, DOOR, EMPTY;
    }

    private int row;
    private int col;
    private Type type;
    private boolean hasGold;

    // --- Constructor ---

    /**
     * Full constructor
     * @param row position
     * @param col position
     * @param type cell nature
     * @param hasGold presence of item
     */
    public Cell(int row, int col, Type type, boolean hasGold) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.hasGold = hasGold;
    }

    // --- Getters ---

    /**
     * Get cell type
     * @return Type enum
     */
    public Type getType() {
        return type;
    }

    /**
     * Check if gold is present
     * @return true if gold exists
     */
    public boolean getHasGold() {
        return hasGold;
    }

    /**
     * Check if player can walk on it
     * @return true if not a wall or door
     */
    public boolean isWalkable() {
        return type != Type.WALL && type != Type.DOOR;
    }

    // --- Setters ---

    /**
     * Update cell type
     * @param type new type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Update gold status
     * @param hasGold new status
     */
    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    // --- Methods ---

    /**
     * Visual representation
     * @return char symbol
     */
    public char getCharRepresentation() {
        if (hasGold) return '.';
        return switch (type) {
            case TRAP -> '*';
            case WALL -> '#';
            case DOOR -> 'D';
            default -> ' ';
        };
    }
}
package fr.mathieu;

public class Cell {
    public enum Type{
        TRAP, WALL, DOOR, EMPTY;
    }

    private int row;
    private int col;
    private Type type;
    private boolean hasgold;

    public Cell(int row, int col, Type type, boolean hasgold) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.hasgold = hasgold;
    }

    //Getters et Setters
    public int getRow() {
        return row;
    }
    public void setRow(int row) {this.row = row;}

    public int getCol() {
        return col;
    }
    public void setCol(int col) {this.col = col;}

    public Type getType() {
        return type;
    }
    public void setType(Type type) {this.type = type;}

    public boolean getHasgold() {return hasgold;}
    public void setHasgold (boolean getHasgold) {
        this.hasgold = getHasgold;
    }

    public boolean isWalkable(){
        return type != Type.WALL && type != Type.DOOR;
    }
    public char getCharRepresentation(){
        if (hasgold) return '.';
        return switch (type){
            case TRAP -> '*';
            case WALL -> '#';
            case DOOR -> 'D';
            default -> ' ';
        };
    }


}

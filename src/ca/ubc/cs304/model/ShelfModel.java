package ca.ubc.cs304.model;

public class ShelfModel {
    private final int posID;
    private final float roomTemp;

    public ShelfModel(int posID, float roomTemp) {
        this.posID = posID;
        this.roomTemp = roomTemp;
    }

    public int getPosID() {
        return posID;
    }

    public float getRoomTemp() {
        return roomTemp;
    }
}

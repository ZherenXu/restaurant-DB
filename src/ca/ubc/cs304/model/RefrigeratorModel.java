package ca.ubc.cs304.model;

public class RefrigeratorModel {
    private final int posID;
    private final float refrigeratorTemp;

    public RefrigeratorModel(int posID, float refrigeratorTemp) {
        this.posID = posID;
        this.refrigeratorTemp = refrigeratorTemp;
    }

    public int getPosID() {
        return posID;
    }

    public float getRefrigeratorTemp() {
        return refrigeratorTemp;
    }
}

package ca.ubc.cs304.model;

public class FreezerModel {
    private final int posID;
    private final float freezerTemp;

    public FreezerModel(int posID, float freezerTemp) {
        this.posID = posID;
        this.freezerTemp = freezerTemp;
    }

    public int getPosID() {
        return posID;
    }

    public float getFreezerTemp() {
        return freezerTemp;
    }
}

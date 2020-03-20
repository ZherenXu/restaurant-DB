package ca.ubc.cs304.model;

public class HasModel {
    private final String address;
    private final int posID;

    public HasModel(String address, int posID) {
        this.address = address;
        this.posID = posID;
    }

    public String getAddress() {
        return address;
    }

    public int getPosID() {
        return posID;
    }
}

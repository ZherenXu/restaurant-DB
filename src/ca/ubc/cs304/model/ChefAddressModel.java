package ca.ubc.cs304.model;

public class ChefAddressModel {
    private final String SIN;
    private final String branchAddress;

    public ChefAddressModel(String SIN, String branchAddress) {
        this.SIN = SIN;
        this.branchAddress = branchAddress;
    }

    public String getSIN() {
        return SIN;
    }

    public String getBranchAddress() {
        return branchAddress;
    }
}

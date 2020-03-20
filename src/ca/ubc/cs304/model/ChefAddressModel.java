package ca.ubc.cs304.model;

public class ChefAddressModel {
    private final String homeAddress;
    private final String branchAddress;

    public ChefAddressModel(String homeAddress, String branchAddress) {
        this.homeAddress = homeAddress;
        this.branchAddress = branchAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getBranchAddress() {
        return branchAddress;
    }
}

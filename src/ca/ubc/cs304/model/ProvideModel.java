package ca.ubc.cs304.model;

public class ProvideModel {
    private final String companyName;
    private final String lotNumber;

    public ProvideModel(String companyName, String lotNumber) {
        this.companyName = companyName;
        this.lotNumber = lotNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLotNumber() {
        return lotNumber;
    }
}

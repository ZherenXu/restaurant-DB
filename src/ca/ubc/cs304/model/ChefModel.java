package ca.ubc.cs304.model;

public class ChefModel {
    private final String name;
    private final String sin;
    private final String contactNumber;
    private final String homeAddress;

    public ChefModel(String name, String sin, String contactNumber, String homeAddress) {
        this.name = name;
        this.sin = sin;
        this.contactNumber = contactNumber;
        this.homeAddress = homeAddress;
    }

    public String getName() {
        return name;
    }

    public String getSin() {
        return sin;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }
}

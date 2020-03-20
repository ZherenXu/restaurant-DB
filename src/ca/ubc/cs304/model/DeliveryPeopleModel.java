package ca.ubc.cs304.model;

public class DeliveryPeopleModel {
    private final String sin;
    private final String name;
    private final String contactNumber;
    private final String address;

    public DeliveryPeopleModel(String sin, String name, String contactNumber, String address) {
        this.sin = sin;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getSin() {
        return sin;
    }
}

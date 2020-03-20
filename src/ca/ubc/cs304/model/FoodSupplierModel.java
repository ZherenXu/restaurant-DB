package ca.ubc.cs304.model;

public class FoodSupplierModel {
    private final String companyName;
    private final String address;
    private final String contactNumber;
    private final String email;

    public FoodSupplierModel(String companyName, String address, String contactNumber, String email){
        this.companyName = companyName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }
}

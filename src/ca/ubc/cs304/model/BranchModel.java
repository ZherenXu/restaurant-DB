package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class BranchModel {
	private final String address;
	private final String contact;
	private final String managerName;

	public BranchModel(String address, String contact, String managerName) {
		this.address = address;
		this.contact = contact;
		this.managerName = managerName;
	}
	public String getAddress() {
		return address;
	}
}

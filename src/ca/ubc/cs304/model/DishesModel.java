package ca.ubc.cs304.model;

public class DishesModel {
    private final String name;
    private final int orderNumber;
    private final String tastePreference;

    public DishesModel(String name, int orderNumber, String tastePreference) {
        this.name = name;
        this.orderNumber = orderNumber;
        this.tastePreference = tastePreference;
    }

    public String getName() {
        return name;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getTastePreference() {
        return tastePreference;
    }
}

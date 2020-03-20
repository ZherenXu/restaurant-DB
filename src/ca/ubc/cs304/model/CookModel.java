package ca.ubc.cs304.model;

public class CookModel {
    private final String sin;
    private final String dishesName;
    private final int orderNumber;

    public CookModel(String sin, String dishesName, int orderNumber) {
        this.sin = sin;
        this.dishesName = dishesName;
        this.orderNumber = orderNumber;
    }

    public String getSin() {
        return sin;
    }

    public String getDishesName() {
        return dishesName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}

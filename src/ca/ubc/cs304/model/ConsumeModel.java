package ca.ubc.cs304.model;

public class ConsumeModel {
    private final String dishesName;
    private final int orderNumber;
    private final String lotNumber;
    private final int quantity;

    public ConsumeModel(String dishesName, int orderNumber, String lotNumber, int quantity) {
        this.dishesName = dishesName;
        this.orderNumber = orderNumber;
        this.lotNumber = lotNumber;
        this.quantity = quantity;
    }

    public String getDishesName() {
        return dishesName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public int getQuantity() {
        return quantity;
    }
}

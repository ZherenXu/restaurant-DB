package ca.ubc.cs304.model;

import oracle.sql.TIMESTAMP;

public class OrdersModel {
    private final int orderNumber;
    private final TIMESTAMP time;

    public OrdersModel(int orderNumber, TIMESTAMP time) {
        this.orderNumber = orderNumber;
        this.time = time;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public TIMESTAMP getTime() {
        return time;
    }
}

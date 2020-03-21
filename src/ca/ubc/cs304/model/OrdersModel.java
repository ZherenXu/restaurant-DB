package ca.ubc.cs304.model;

import oracle.sql.TIMESTAMP;

import java.sql.Timestamp;

public class OrdersModel {
    private final int orderNumber;
    private final Timestamp time;

    public OrdersModel(int orderNumber, Timestamp time) {
        this.orderNumber = orderNumber;
        this.time = time;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Timestamp getTime() {
        return time;
    }
}

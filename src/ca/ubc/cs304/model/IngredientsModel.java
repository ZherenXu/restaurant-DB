package ca.ubc.cs304.model;

import oracle.sql.DATE;

import java.sql.Date;

public class IngredientsModel {
    private final String lotNumber;
    private final String name;
    private final Date productionDate;
    private final int quantity;
    private final int posID;
    private final String sin;

    public IngredientsModel(String lotNumber, String name, java.sql.Date productionDate, int quantity, int posID, String sin) {
        this.lotNumber = lotNumber;
        this.name = name;
        this.productionDate = productionDate;
        this.quantity = quantity;
        this.posID = posID;
        this.sin = sin;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public String getName() {
        return name;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPosID() {
        return posID;
    }

    public String getSin() {
        return sin;
    }
}

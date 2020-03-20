package ca.ubc.cs304.model;

import oracle.sql.DATE;

public class IngredientsModel {
    private final String lotNumber;
    private final String name;
    private final DATE productionDate;
    private final int quantity;
    private final int posID;
    private final String sin;

    public IngredientsModel(String lotNumber, String name, DATE productionDate, int quantity, int posID, String sin) {
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

    public DATE getProductionDate() {
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

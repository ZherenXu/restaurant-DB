package ca.ubc.cs304.model;

public class CategoryModel {
    private final String name;
    private final String type;

    public CategoryModel(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}

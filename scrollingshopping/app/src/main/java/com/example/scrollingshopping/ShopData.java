package com.example.scrollingshopping;

public class ShopData {

    public String name;
    public int id;
    public int imageResource;
    public String description;
    public int cost;
    public boolean purchased;

    public ShopData(String name, int imageResource, String desc, int cost) {

        this.name = name;
        this.imageResource = imageResource;
        this.description = desc;
        this.cost = cost;
        this.purchased = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }



}

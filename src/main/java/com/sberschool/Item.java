package com.sberschool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Item {
    private String name;
    private String description;
    private final Food food;
    private static Map<String,Item> items;
    public Item(String name,String description, Food food){
        this.name = name;
        this.description = description;
        this.food = food;
        if(items ==null){
            items = new HashMap<>();
        }
        items.put(name,Item.this);
    }
    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Food getFood() {
        return food;
    }

    public static Map<String, Item> getItems() {
        return items;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(description, item.description) && Objects.equals(food, item.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return  name;
    }
}

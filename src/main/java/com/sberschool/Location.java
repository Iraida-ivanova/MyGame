package com.sberschool;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Location {

    private final String name;
    private final String description;
    private Inventory inventory;
    private Map<Direction, Location> directions;
    private Animal animal;


    private FoodForAnimal foodForAnimal;
    public Location(String name,String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public FoodForAnimal getFoodForAnimal() {
        return foodForAnimal;
    }

    public void setFoodForAnimal(FoodForAnimal foodForAnimal) {
        this.foodForAnimal = foodForAnimal;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    public Map<Direction, Location> getDirections() {
        return directions;
    }
    public void setDirections(Map<Direction, Location> directions) {
        this.directions = directions;
    }




}

package com.games;

import java.util.HashMap;
import java.util.Map;

public class Animal {
    private String name;
    private Food food;
    private boolean hunger;
    private static Map<String,Animal> animals;
    public String isHungry(){
        if(hunger){
            return name + " хочет есть.";
        }
        else {
            return (name + " накормлен.");}

    }
    public Animal (String name,Food food, boolean hunger){
        this.name = name;
        this.food =food;
        this.hunger = hunger;
        if (animals == null){
            animals = new HashMap<>();
        }
        animals.put(this.name,Animal.this);
    }

    public String getName() {
        return name;
    }


    public Food getFood() {
        return food;
    }

    public static Map<String, Animal> getAnimals() {
        return animals;
    }


    public boolean isHunger() {
        return hunger;
    }

    public void setHunger(boolean hunger) {
        this.hunger = hunger;
    }
}

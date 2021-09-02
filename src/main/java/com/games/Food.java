package com.games;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Food {
    private String name;
    private static Map<String,Food> foods;



    public Food(String name) {

        this.name = name;
        if(foods==null){
            foods=new HashMap<>();
            foods.put(this.name,Food.this);
        }
        else foods.put(name,Food.this);
    }

    public String getName() {
        return name;
    }

    public static Map<String, Food> getFoods() {
        return foods;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


package com.games;


import java.util.List;
import java.util.stream.Collectors;

public class FoodForAnimal {
    private List<Food> foods;

    public FoodForAnimal(List<Food> foods) {
        this.foods= foods;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public List<Food> add(Food food) {
        foods.add(food);
        return foods;
    }

    public List<Food> remove(Food food) {
        foods.remove(food);
        return foods;
    }

    @Override
    public String toString() {
        return "Еда: "+foods.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }
}

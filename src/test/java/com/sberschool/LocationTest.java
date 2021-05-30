package com.sberschool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LocationTest {
    private Location location;
    private Inventory inventory;
    private Map<Direction, Location> directions;
    private Animal animal;
    private Food carrot;
    private List<Food> foods;
    private Item shovel;
    private Item bucket;
    private List<Item> items;
    private FoodForAnimal foodForAnimal;
    @Before
    public void setUp() throws Exception {
        carrot = new Food("морковь");
        foods = new ArrayList<>();
        foods.add(carrot);
        foodForAnimal = new FoodForAnimal(foods);
        animal = new Animal("животное",carrot,true);
        shovel = new Item("лопата","металлическая",new Food(("морковь")));
        items = new ArrayList<>();
        items.add(shovel);
        inventory = new Inventory(items);
        location = new Location("грядка","здесь есть морковь");
        foodForAnimal = new FoodForAnimal(foods);
        directions = new HashMap<>();
    }

    @After
    public void tearDown() throws Exception {
        location = null;
        inventory = null;
        directions = null;
        animal = null;
        carrot = null;
        foods = null;
        shovel = null;
        bucket = null;
        items = null;
        foodForAnimal = null;
    }

    @Test
    public void getName() {
        assertEquals("грядка",location.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("здесь есть морковь",location.getDescription());
    }

    @Test
    public void getAnimal() {
        location.setAnimal(animal);
        assertEquals(animal,location.getAnimal());
    }

    @Test
    public void setAnimal() {
        Animal frog = new Animal("лягушка",new Food("вода"),true);
        location.setAnimal(frog);
        assertEquals(frog,location.getAnimal());
    }

    @Test
    public void getFoodForAnimal() {
        location.setFoodForAnimal(foodForAnimal);
        assertEquals(foodForAnimal,location.getFoodForAnimal());
    }
    @Test
    public void getFoodForAnimalToArray(){
        location.setFoodForAnimal(foodForAnimal);
        assertArrayEquals(new Food[]{carrot},location.getFoodForAnimal().getFoods().toArray());
    }

    @Test
    public void setFoodForAnimal() {
        location.setFoodForAnimal(foodForAnimal);
        assertEquals(foodForAnimal,location.getFoodForAnimal());
    }

    @Test
    public void getInventoryAndSetInventory() {
        location.setInventory(inventory);
        assertEquals(inventory,location.getInventory());
    }


    @Test
    public void getDirectionsAndSetDirections() {
        directions.put(Direction.WEST,new Location("пруд","здесь есть лягушка"));
        location.setDirections(directions);
        assertEquals(directions,location.getDirections());
    }


}
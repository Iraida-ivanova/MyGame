package com.sberschool;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FoodForAnimalTest {
    private FoodForAnimal foodForAnimal;
    private List<Food> foods;
    private Food hay;
    private Food water;
    private Food carrot;
    @Before
    public void setUp() throws Exception {
        hay = new Food("сено");
        water = new Food("вода");
        carrot = new Food("морковь");
        foods = new ArrayList<>();
        foods.add(hay);
        foods.add(carrot);

        foodForAnimal = new FoodForAnimal(foods);
    }



    @After
    public void tearDown() throws Exception {
        hay = null;
        water = null;
        carrot = null;
        foods = null;
        foodForAnimal = null;
    }
    @Test
    public void testGetFoods() {
        assertArrayEquals(new Food[]{hay,carrot},foodForAnimal.getFoods().toArray());
    }

    @Test
    public void add() {
        Assert.assertArrayEquals(new Food[]{hay,carrot,water},foodForAnimal.add(water).toArray());
    }

    @Test
    public void remove() {

        Assert.assertArrayEquals(new Food[]{carrot},foodForAnimal.remove(hay).toArray());
    }
}
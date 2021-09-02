package com.games;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FoodTest {
    private Food hay;
    private Food water;
    private Food carrot;
    private static Map<String,Food> foods;
    @Before
    public void setUp() throws Exception {
        hay = new Food("сено");
        water = new Food("вода");

        foods = new HashMap<>();
        foods.put("сено",hay);
        foods.put("вода",water);
    }

    @After
    public void tearDown() throws Exception {
        hay = null;
        foods = null;
    }

    @Test
    public void getName() {
        assertEquals("сено",hay.getName());
    }

    @Test
    public void testGetFoods(){
        assertEquals(foods,Food.getFoods());
    }
    @Test
    public void testGetFoodsWithNewFood(){
        carrot = new Food("морковь");
        foods.put("морковь",carrot);
        assertEquals(foods,Food.getFoods());
    }


    @Test
    public void testToString() {
        assertEquals("сено",hay.toString());
    }
}
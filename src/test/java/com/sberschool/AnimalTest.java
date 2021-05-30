package com.sberschool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AnimalTest {
    private Animal caw;
    private Animal rabbit;
    private Food carrot;
    @Before
    public void setUp() throws Exception {
        carrot = new Food("морковь");
        caw = new Animal("корова",new Food("сено"),true);
        rabbit = new Animal("кролик",carrot,false);
    }

    @After
    public void tearDown() throws Exception {
        carrot = null;
        caw = null;
        rabbit = null;
    }
    @Test
    public void getName() {
        assertEquals("кролик",rabbit.getName());
    }

    @Test
    public void getFood() {
        assertEquals(carrot,rabbit.getFood());
    }

    @Test
    public void getAnimals() {
        Map<String,Animal> animalHashMap= new HashMap<>();
        animalHashMap.put("кролик",rabbit);
        animalHashMap.put("корова",caw);
        assertEquals(animalHashMap,Animal.getAnimals());
    }

    @Test
    public void isHunger() {
        assertEquals(true,caw.isHunger());
    }

    @Test
    public void setHunger() {
        rabbit.setHunger(true);
        assertEquals(true,rabbit.isHunger());
    }

    @Test
    public void isHungry() {
        assertEquals("корова хочет есть.",caw.isHungry());
        assertEquals("кролик накормлен.",rabbit.isHungry());
    }
}
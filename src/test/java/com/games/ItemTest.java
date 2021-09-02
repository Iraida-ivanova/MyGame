package com.games;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ItemTest {
    private Item hayfork;
    private Item shovel;
    Food hay = new Food("сено");
    Food carrot = new Food("морковь");
    private Map<String,Item> items;

    @Before
    public void setUp() throws Exception {
        hayfork = new Item("вилы", "Вилы можно использовать, чтобы взять сено. ", hay);
        shovel = new Item("лопата", "Возьми лопату, чтобы выкопать морковь для кролика. ", carrot);
        items = new HashMap<>();
    }

    @After
    public void tearDown() throws Exception {
        hay = null;
        hayfork = null;
        shovel = null;
        carrot = null;
    }

    @Test
    public void getDescription() {
        assertEquals("Вилы можно использовать, чтобы взять сено. ",hayfork.getDescription());
    }

    @Test
    public void getName() {
        assertEquals("вилы",hayfork.getName());
    }

    @Test
    public void getFood() {
        assertEquals(hay,hayfork.getFood());
    }

    @Test
    public void getItems() {
        items.put("вилы",hayfork);
        items.put("лопата",shovel);
        assertEquals(items,Item.getItems());
    }

    @Test
    public void testEqualsTrue() {
        Item fork = new Item("вилы","Вилы можно использовать, чтобы взять сено. ",hay);
        assertTrue(hayfork.equals(fork));
    }
    @Test
    public void testEqualsFalse() {
        Item fork = new Item("вилы","Вилы можно использовать, чтобы взять сен. ",hay);
        assertFalse(hayfork.equals(fork));
    }

    @Test
    public void testToString() {
        assertEquals("вилы",hayfork.toString());
    }
}
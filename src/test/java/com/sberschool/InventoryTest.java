package com.sberschool;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InventoryTest {
    private Inventory inventory;
    private Item shovel;
    private Item bucket;
    private List<Item> items;

    @Before
    public void setUp() throws Exception {
        shovel = new Item("лопата","металлическая",new Food(("морковь")));
        bucket = new Item("ведро","пустое",new Food(("вода")));
        items = new ArrayList<>();
        items.add(shovel);
        items.add(bucket);
        inventory = new Inventory(items);

    }

    @After
    public void tearDown() throws Exception {
        inventory = null;
        shovel = null;
         bucket = null;
         items = null;
    }

    @Test
    public void add() {
        Food grain = new Food("зерно");
        Item bag = new Item("мешок", "В мешок можно набрать зерно для курицы. ", grain);

        Assert.assertArrayEquals(new Item[]{shovel,bucket,bag},inventory.add(bag).toArray());
    }

    @Test
    public void remove() {
        Assert.assertArrayEquals(new Item[]{shovel},inventory.remove(bucket).toArray());
    }


    @Test
    public void show() {
        Assert.assertEquals("лопата, ведро",inventory.show());
    }
}
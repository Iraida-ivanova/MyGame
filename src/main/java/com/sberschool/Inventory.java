package com.sberschool;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Inventory {
    private List<Item> items;

    public Inventory(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Item> add(Item e) {
        items.add(e);
        return items;
    }

    public List<Item> remove(Item e) {
        items.remove(e);
        return items;
    }


    public String show() {
        return items.stream().map(String::valueOf).collect(Collectors.joining(", "));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(items, inventory.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}

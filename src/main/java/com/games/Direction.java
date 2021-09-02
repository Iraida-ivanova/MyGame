package com.games;

public enum Direction {
    UP("вверх"),
    DOWN("вниз"),
    WEST("запад"),
    EAST("восток"),
    NORTH("север"),
    SOUTH("юг");
    private String title;
    Direction(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}

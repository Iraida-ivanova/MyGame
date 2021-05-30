package com.sberschool;

import java.util.*;
import java.util.stream.Collectors;


public class Player {
    private Location location;
    private Inventory inventory;
    private FoodForAnimal foodForAnimal;
    private int countFedAnimals;

    public Player() {
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public FoodForAnimal getFoodForAnimal() {
        return foodForAnimal;
    }

    public void setFoodForAnimal(FoodForAnimal foodForAnimal) {
        this.foodForAnimal = foodForAnimal;
    }

    public int getCountFedAnimals() {
        return countFedAnimals;
    }

    public void setCountFedAnimals(int countFedAnimals) {
        this.countFedAnimals = countFedAnimals;
    }

    public void lookAround() {
        System.out.println("Ты пришел в " + location.getName() + ". " + location.getDescription());
        if (location.getInventory() != null){
            if(!location.getInventory().getItems().isEmpty()) {
                System.out.println(" Здесь есть " + location.getInventory().getItems().stream().map(item -> item.getName() + ". " + item.getDescription()).collect(Collectors.joining(". ")));}
        }
        if(location.getAnimal()!=null){
            System.out.println(location.getAnimal().isHungry());
        }
    }

    public void take(String message){
        if(Food.getFoods().containsKey(message)){
            System.out.println("Ты не можешь взять еду без инструмента! Чтобы взять " + message + " нужно использовать инвентарь! ");
        }
        else {
            if(Item.getItems().containsKey(message)) {
                Item item = Item.getItems().get(message);
                if (location.getInventory() != null) {
                    if (location.getInventory().getItems().contains(item)) {
                        if (inventory == null) {
                            List<Item> items = new ArrayList<>();
                            items.add(item);
                            setInventory(new Inventory(items));
                        } else {
                            inventory.add(item);
                        }
                        location.getInventory().remove(item);
                        System.out.println("У тебя есть " + item);
                    } else {
                        System.out.println("Здесь нет " + message);
                    }
                }
                else {
                    System.out.println("Здесь нет " + message);
                }
            }
            else { System.out.println("Ты не можешь взять "+message); }

        }
    }

    public void use (String a,String b){
        if (Item.getItems().containsKey(a)&&Food.getFoods().containsKey(b)) {
            Item item = Item.getItems().get(a);
            Food food = Food.getFoods().get(b);
            if (inventory != null) {
                if (inventory.getItems().contains(item)) {
                    if (location.getFoodForAnimal().getFoods().contains(food)) {
                        if (item.getFood().getName().equals(food.getName())) {

                            if (foodForAnimal == null) {
                                List<Food> foodList = new ArrayList<>();
                                foodList.add(food);
                                setFoodForAnimal(new FoodForAnimal(foodList));
                            } else { foodForAnimal.add(food); }

                            System.out.println("У тебя есть " + food.getName());
                        }
                        else { System.out.println("Невозможно использовать " + a + " для " + food.getName()); }
                    }
                    else { System.out.println("Здесь нет "+b); }
                }
                else { System.out.println("У тебя нет " + a); }
            }
            else { System.out.println("У тебя нет " + a); }
        }
            else { System.out.println("Команда введена некорректно.Тебе нужно использовать инвентарь для еды!"); }

    }

    public void inventory(){
        if(inventory!=null&&!inventory.getItems().isEmpty()){
            System.out.println(inventory.show());}
        else {
            System.out.println("У тебя нет инвентаря!");
        }

    }

    public void feedAnimals(String a,String b){

            if (Animal.getAnimals().containsKey(a)){
                Animal animal= Animal.getAnimals().get(a);
                if (Food.getFoods().containsKey(b)){
                   Food food = Food.getFoods().get(b);
                   if(location.getAnimal()!=null){
                       if(location.getAnimal().getName().equals(a)){
                           if (foodForAnimal!=null){
                               if(foodForAnimal.getFoods().contains(food)){
                                   if(animal.getFood().getName().equals(food.getName())){
                                       if(location.getAnimal().isHunger()==true){
                                           foodForAnimal.getFoods().remove(food);
                                           location.getAnimal().setHunger(false);
                                           System.out.println(location.getAnimal().isHungry());
                                           countFedAnimals++;
                                       }
                                       else{
                                           System.out.println(a+ " уже накормлен!");

                                       }
                                   }
                                   else {
                                       System.out.println(a +" не ест "+b );
                                   }
                               }
                               else {
                                   System.out.println("У тебя нет "+b);
                               }
                           }
                           else {
                               System.out.println("У тебя нет "+b);
                           }
                       }
                       else {
                           System.out.println("Здесь нет "+ a);
                       }
                   }
                   else {
                       System.out.println("Здесь нет "+ a);
                   }
                }
                else {
                    System.out.println(a+" не ест "+b);
                }

            }
            else {
                System.out.println("Ты некорректно ввел команду! Сначала выбери животное, потом выбери один продукт для него.");
            }
    }

    public void go(String message) {

           Optional<Location> nextLocation = location.getDirections().entrySet().stream().filter(entry->message.equals(entry.getKey().getTitle())).map(Map.Entry::getValue).findFirst();
           if(nextLocation.isPresent()){
               setLocation(nextLocation.get());
               lookAround();
           }
           else {
               System.out.println("Туда пойти невозможно!");
           }

        }
}






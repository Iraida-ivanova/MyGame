package com.sberschool;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Location stall = new Location("стойло", "В стойле живет корова. Севернее грядка с морковью, центр на западе. На втором этаже находится сеновал");
        Location hayloft = new Location("сеновал", "На сеновале хранится сено. Спустившись вниз, ты окажешься в стойле. ");
        Location henHouse = new Location("курятник", "В курятнике живут курицы. На севере амбар с зерном. Восточнее находится центр фермы.");
        Location warren = new Location("крольчатник", "В крольчатнике живет кролик. Восточнее есть грядка с морковью. Южнее находится центр фермы. На западе амбар с зерном. ");
        Location barn = new Location("амбар", "В амбаре хранится зерно. На востоке - крольчатник. Южнее - курятник. ");
        Location centre = new Location("центр фермы", "Отсюда видно всю ферму. Севернее от этого места находится крольчатник. Западнее - курятник. На востоке находится стойло, в котором живет корова. ");
        Location carrotPatch = new Location("грядка с морковью", "На грядке можно собрать морковь для кроликов. Южнее от грядки - стойло. На западе - крольчатник. ");

        stall.setDirections(Map.of(Direction.NORTH, carrotPatch, Direction.WEST, centre, Direction.UP, hayloft));
        hayloft.setDirections(Map.of(Direction.DOWN, stall));
        henHouse.setDirections(Map.of(Direction.NORTH, barn, Direction.EAST, centre));
        warren.setDirections(Map.of(Direction.SOUTH, centre, Direction.WEST, barn, Direction.EAST, carrotPatch));
        barn.setDirections(Map.of(Direction.EAST, warren, Direction.SOUTH, henHouse));
        centre.setDirections(Map.of(Direction.NORTH, warren, Direction.WEST, henHouse, Direction.EAST, stall));
        carrotPatch.setDirections(Map.of(Direction.SOUTH, stall, Direction.WEST, warren));

        Food hay = new Food("сено");
        Food carrot = new Food("морковь");
        Food grain = new Food("зерно");


        Item hayfork = new Item("вилы", "Вилы можно использовать, чтобы взять сено. ", hay);
        Item shovel = new Item("лопата", "Возьми лопату, чтобы выкопать морковь для кролика. ", carrot);
        Item bag = new Item("мешок", "В мешок можно набрать зерно для курицы. ", grain);

        List<Item> hayloftItems = new ArrayList<>();
        List<Item> carrotPatchItems = new ArrayList<>();
        List<Item> barnItems = new ArrayList<>();

        hayloftItems.add(hayfork);
        carrotPatchItems.add(shovel);
        barnItems.add(bag);

        hayloft.setInventory(new Inventory(hayloftItems));
        carrotPatch.setInventory(new Inventory(carrotPatchItems));
        barn.setInventory(new Inventory(barnItems));

        List<Food> foodHayloft = new ArrayList<>();
        List<Food> foodCarrotPatch = new ArrayList<>();
        List<Food> foodBarn = new ArrayList<>();

        foodHayloft.add(hay);
        foodCarrotPatch.add(carrot);
        foodBarn.add(grain);
        hayloft.setFoodForAnimal(new FoodForAnimal(foodHayloft));
        carrotPatch.setFoodForAnimal(new FoodForAnimal(foodCarrotPatch));
        barn.setFoodForAnimal(new FoodForAnimal(foodBarn));

        Animal caw = new Animal("корова", hay, true);
        Animal hen = new Animal("курица", grain, true);
        Animal rabbit = new Animal("кролик", carrot, true);


        stall.setAnimal(caw);
        henHouse.setAnimal(hen);
        warren.setAnimal(rabbit);
        Player player = new Player();
        player.setLocation(centre);
        player.setCountFedAnimals(0);
        ExecutorTheCommand executorTheCommand = new ExecutorTheCommand(player);
        System.out.println("Игра ФЕРМА");
        System.out.println("Правила игры: чтобы выиграть, игроку нужно накормить всех животных.");
        System.out.println("Можно использовать следующие команды:");
        System.out.println("\"осмотреться\",\"инвентарь\",\"идти + одно из направлений(вверх,вниз,запад,восток,север,юг)\",\"взять + название инвентаря(лопата,вилы,мешок),\"");
        System.out.println("\"использовать + название инвентаря + для + название еды для животного(сено, морковь, зерно)\",\"накормить + название животного(корова, курица, кролик) + название еды\"");
        System.out.println("Для обозначения животных,предметов,еды используй слова в единственном числе и именительном падеже. Пример команды:\"накормить корова\"");
        System.out.println("Чтобы начать - введи \"старт\".Чтобы закончить - введи \"выйти\"");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()&& executorTheCommand.stop==false) {
            executorTheCommand.executeCommand(scanner.nextLine());
        }
        scanner.close();
    }


}
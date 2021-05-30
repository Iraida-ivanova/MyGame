package com.sberschool;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;
    private Location hayloft;
    private Location stall;
    private Location carrotPatch;
    private Inventory inventory;
    private FoodForAnimal foodForAnimal;
    private int countFedAnimals;
    private Item hayfork;
    private Food hay;
    private List<Food> foods;
    private List<Item> items;
    private Animal caw;





    @Before
    public void setUp() {
        hay = new Food("сено");
        hayfork = new Item("вилы", "Вилы можно использовать, чтобы взять сено. ", hay);
        items = new ArrayList<>();
        items.add(hayfork);
        inventory = new Inventory(items);
        foods = new ArrayList<>();


        hayloft = new Location("сеновал", "На сеновале хранится сено. Спустившись вниз, ты окажешься в стойле. ");
        carrotPatch = new Location("грядка с морковью", "На грядке можно собрать морковь для кроликов. Южнее от грядки - стойло. На западе - крольчатник. ");
        stall = new Location("стойло", "В стойле живет корова. Севернее грядка с морковью, центр на западе. На втором этаже находится сеновал");
        stall.setDirections(Map.of(Direction.NORTH, carrotPatch, Direction.UP, hayloft));
        caw = new Animal("корова",new Food("сено"),true);


        player = new Player();

    }

    @After
    public void tearDown() {
        hay = null;
        hayloft = null;
        hayfork = null;
        items = null;
        inventory= null;
        caw = null;
        carrotPatch = null;
        stall = null;
        player = null;
        foods = null;
    }

    @Test
    public void getInventoryAndSetInventory() {
        player.setInventory(inventory);
        assertEquals(inventory,player.getInventory());

    }

    @Test
    public void getLocationAndSetLocation() {
        player.setLocation(hayloft);
        assertEquals(hayloft,player.getLocation());
    }

    @Test
    public void getAndSetFoodForAnimal() {
        //Food hay = new Food("сено");
        List<Food> foodHay = new ArrayList<>();
        foodForAnimal = new FoodForAnimal(foodHay);

        player.setFoodForAnimal(foodForAnimal);
        assertEquals(foodForAnimal,player.getFoodForAnimal());
    }


    @Test
    public void getCountFedAnimals() {
        player.setCountFedAnimals(0);
        assertEquals(0,player.getCountFedAnimals());
    }

    @Test
    public void setCountFedAnimals() {
        player.setCountFedAnimals(2);
        assertEquals(2,player.getCountFedAnimals());
    }

    @Test
    public void testLookAroundWithAnimal() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            stall.setAnimal(caw);
            player.setLocation(stall);
            player.lookAround();

            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Ты пришел в стойло. В стойле живет корова. Севернее грядка с морковью, центр на западе. На втором этаже находится сеновал\r\n" +
                "корова хочет есть.\r\n",consoleOutput);
    }
    @Test
    public void testLookAroundWithOutAnimal() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setLocation(hayloft);
            player.lookAround();

            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Ты пришел в сеновал. На сеновале хранится сено. Спустившись вниз, ты окажешься в стойле. \r\n",consoleOutput);
    }
    @Test
    public void testLookAroundWithItem(){
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            hayloft.setInventory(inventory);
            player.setLocation(hayloft);

            player.lookAround();

            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Ты пришел в сеновал. На сеновале хранится сено. Спустившись вниз, ты окажешься в стойле. \r\n" +
                " Здесь есть вилы. Вилы можно использовать, чтобы взять сено. \r\n",consoleOutput);
    }

    @Test
    public void testTakeItemIsInStock() {
    String consoleOutput = null;
    PrintStream originalOutPut = System.out;
    try {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
        PrintStream capture = new PrintStream(outputStream);
        System.setOut(capture);
        hayloft.setInventory(inventory);
        player.setLocation(hayloft);
        player.take("вилы");
        capture.flush();
        consoleOutput = outputStream.toString();
        System.setOut(originalOutPut);
    }
    catch (Exception e){
        e.printStackTrace();
    }
    assertEquals("У тебя есть вилы\r\n",consoleOutput);
    assertArrayEquals(new Item[]{},hayloft.getInventory().getItems().toArray());
    assertArrayEquals(new Item[]{hayfork},player.getInventory().getItems().toArray());
    }
    @Test
    public void testTakeItemIsNotStock() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            Item shovel = new Item("лопата", "Возьми лопату, чтобы выкопать морковь для кролика. ", hay);
            hayloft.setInventory(inventory);
            player.setLocation(hayloft);
            player.take("лопата");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Здесь нет лопата\r\n",consoleOutput);
    }

    @Test
    public void testTakeFood() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.take("сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Ты не можешь взять еду без инструмента! Чтобы взять сено нужно использовать инвентарь! \r\n",consoleOutput);
    }
    @Test
    public void testTakeAnythingButIsNotItem() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);

            player.take("цветок");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Ты не можешь взять цветок\r\n",consoleOutput);
    }

    @Test
    public void testTakeAnimal() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);

            player.take("корова");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Ты не можешь взять корова\r\n",consoleOutput);}

    @Test
    public void testUseItemForWrongFood() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            Food carrot = new Food("морковь");
            Item shovel = new Item("лопата", "Возьми лопату, чтобы выкопать морковь для кролика. ", carrot);
            inventory.add(shovel);
            player.setInventory(inventory);
            foods.add(hay);
            foods.add(carrot);
            foodForAnimal = new FoodForAnimal(foods);
            hayloft.setFoodForAnimal(foodForAnimal);
            player.setLocation(hayloft);
            player.use("вилы","морковь");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Невозможно использовать вилы для морковь\r\n",consoleOutput);

    }
    @Test
    public void testUse() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setInventory(inventory);
            foods.add(hay);
            foodForAnimal = new FoodForAnimal(foods);
            hayloft.setFoodForAnimal(foodForAnimal);
            player.setLocation(hayloft);
            player.use("вилы","сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("У тебя есть сено\r\n",consoleOutput);
        assertArrayEquals(new Food[]{hay},player.getFoodForAnimal().getFoods().toArray());
    }
    @Test
    public void testUseItemIfFoodNotHere() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setInventory(inventory);
            foodForAnimal = new FoodForAnimal(foods);
            hayloft.setFoodForAnimal(foodForAnimal);
            player.setLocation(hayloft);
            player.use("вилы","сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Здесь нет сено\r\n",consoleOutput);

    }
    @Test
    public void testUseIfNotHaveItem() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);

            foods.add(hay);
            foodForAnimal = new FoodForAnimal(foods);
            hayloft.setFoodForAnimal(foodForAnimal);
            player.setLocation(hayloft);
            player.use("вилы","сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("У тебя нет вилы\r\n",consoleOutput);}
    @Test
    public void testUseIfIncorrectCommand1() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);

            foods.add(hay);
            foodForAnimal = new FoodForAnimal(foods);
            hayloft.setFoodForAnimal(foodForAnimal);
            player.setLocation(hayloft);
            player.use("сено","вилы");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Команда введена некорректно.Тебе нужно использовать инвентарь для еды!\r\n",consoleOutput);}
    @Test
    public void testUseIfIncorrectCommand2() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);

            foods.add(hay);
            foodForAnimal = new FoodForAnimal(foods);
            hayloft.setFoodForAnimal(foodForAnimal);
            player.setLocation(hayloft);
            player.use("вилы","вилы");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Команда введена некорректно.Тебе нужно использовать инвентарь для еды!\r\n",consoleOutput);}
    @Test
    public void testUseIfIncorrectCommand3() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);

            player.use("111","222");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Команда введена некорректно.Тебе нужно использовать инвентарь для еды!\r\n",consoleOutput);}
    @Test
    public void testInventory() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            Food carrot = new Food("морковь");
            Item shovel = new Item("лопата", "Возьми лопату, чтобы выкопать морковь для кролика. ", carrot);
            inventory.add(shovel);
            player.setInventory(inventory);
            player.inventory();
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("вилы, лопата\r\n",consoleOutput);
    }
    @Test
    public void testInventoryIfNotInventory() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.inventory();
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("У тебя нет инвентаря!\r\n",consoleOutput);
    }
    @Test
    public void testInventoryIfInventoryEmpty() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setInventory(new Inventory(new ArrayList<>()));
            player.inventory();
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("У тебя нет инвентаря!\r\n",consoleOutput);
    }

    @Test
     public void   testFeedAnimals() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setCountFedAnimals(0);
            foods.add(hay);
            foodForAnimal = new FoodForAnimal(foods);
            player.setFoodForAnimal(foodForAnimal);
            stall.setAnimal(caw);
            player.setLocation(stall);
            player.feedAnimals("корова","сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("корова накормлен.\r\n",consoleOutput);
        assertArrayEquals(new Food[]{},player.getFoodForAnimal().getFoods().toArray());
        assertFalse(caw.isHunger());
        assertEquals(1,player.getCountFedAnimals());
    }
    @Test
    public void   testFeedIfAnimalsFeed() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            foods.add(hay);
            foodForAnimal = new FoodForAnimal(foods);
            player.setFoodForAnimal(foodForAnimal);
            caw.setHunger(false);
            stall.setAnimal(caw);
            player.setLocation(stall);
            player.feedAnimals("корова","сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("корова уже накормлен!\r\n",consoleOutput);}
    @Test
    public void   testFeedAnimalsIfAnimalNotEatThisFood() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            Food carrot = new Food("морковь");
            foods.add(carrot);
            foodForAnimal = new FoodForAnimal(foods);
            player.setFoodForAnimal(foodForAnimal);
            stall.setAnimal(caw);
            player.setLocation(stall);
            player.feedAnimals("корова","морковь");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("корова не ест морковь\r\n",consoleOutput);}
    @Test
    public void   testFeedAnimalsIfPlayerNotHaveFood() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            stall.setAnimal(caw);
            player.setLocation(stall);
            player.feedAnimals("корова","сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("У тебя нет сено\r\n",consoleOutput);}
    @Test
    public void   testFeedAnimalsIfPlayerNotHaveThisFood() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            Food carrot = new Food("морковь");
            foods.add(carrot);
            foodForAnimal = new FoodForAnimal(foods);
            player.setFoodForAnimal(foodForAnimal);
            stall.setAnimal(caw);
            player.setLocation(stall);
            player.feedAnimals("корова","сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("У тебя нет сено\r\n",consoleOutput);}
    @Test
    public void   testFeedAnimalsIfAnimalsNotHere() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setCountFedAnimals(0);
            foods.add(hay);
            foodForAnimal = new FoodForAnimal(foods);
            player.setFoodForAnimal(foodForAnimal);
            stall.setAnimal(new Animal("лягушка",hay,true));
            player.setLocation(stall);
            player.feedAnimals("корова","сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Здесь нет корова\r\n",consoleOutput);}
    @Test
    public void   testFeedAnimalsIfLocationDon_tHaveAnimals() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setCountFedAnimals(0);
            foods.add(hay);
            foodForAnimal = new FoodForAnimal(foods);
            player.setFoodForAnimal(foodForAnimal);
            player.setLocation(stall);
            player.feedAnimals("корова","сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Здесь нет корова\r\n",consoleOutput);}
    @Test
    public void   testFeedAnimalsSomethingOtherThanFood() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            stall.setAnimal(caw);
            player.setLocation(stall);
            player.feedAnimals("корова","вилы");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("корова не ест вилы\r\n",consoleOutput);}
    @Test
    public void   testFeedAnimalsIncorrectCommand() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            stall.setAnimal(caw);
            player.setLocation(stall);
            player.feedAnimals("сено","корова");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Ты некорректно ввел команду! Сначала выбери животное, потом выбери один продукт для него.\r\n",consoleOutput);}

    @Test
    public void testGo() {
            String consoleOutput = null;
            PrintStream originalOutPut = System.out;
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
                PrintStream capture = new PrintStream(outputStream);
                System.setOut(capture);
                player.setLocation(stall);
                player.go("вверх");
                capture.flush();
                consoleOutput = outputStream.toString();
                System.setOut(originalOutPut);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            assertEquals("Ты пришел в сеновал. На сеновале хранится сено. Спустившись вниз, ты окажешься в стойле. \r\n",consoleOutput);
            assertEquals(hayloft,player.getLocation());
    }
    @Test
    public void testGoIfThereNothingThere() {
        String consoleOutput = null;
        PrintStream originalOutPut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setLocation(stall);
            player.go("вниз");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertEquals("Туда пойти невозможно!\r\n",consoleOutput);
    }
}
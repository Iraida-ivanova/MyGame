package com.games;

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

public class ExecutorTheCommandTest {
    private Player player;
    private ExecutorTheCommand executorTheCommand;
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
    private String consoleOutput;
    private PrintStream originalOutPut;

    @Before
    public void setUp() throws Exception {
        player = new Player();
        executorTheCommand = new ExecutorTheCommand(player);
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

        consoleOutput = null;
        originalOutPut = System.out;
    }

    @After
    public void tearDown() throws Exception {
        player = null;
        hay = null;
        hayloft = null;
        hayfork = null;
        items = null;
        inventory= null;
        caw = null;
        carrotPatch = null;
        stall = null;
        foods = null;
    }

    @Test
    public void executeCommandStart() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            executorTheCommand.executeCommand("старт");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Добро пожаловать на ферму! Помоги пожалуйста накормить животных!\r\n",consoleOutput);
    }
    @Test
    public void executeCommandExit() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            executorTheCommand.executeCommand("выйти");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Игра завершена!\r\n",consoleOutput);
    }
    @Test
    public void testExecuteCommandLookaroundAndDifCase() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setLocation(hayloft);
            executorTheCommand.executeCommand("оСМОТреться");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Ты пришел в сеновал. На сеновале хранится сено. Спустившись вниз, ты окажешься в стойле. \r\n",consoleOutput);
    }
    @Test
    public void testExecuteCommandInventoryAndUpperCase() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setInventory(inventory);
            executorTheCommand.executeCommand("ИНВЕНТАРЬ");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("вилы\r\n",consoleOutput);
    }
    @Test
    public void testExecuteIncorrectCommandWithOneWord() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            executorTheCommand.executeCommand("вход");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Некорректная команда!\r\n",consoleOutput);
    }
    @Test
    public void testExecuteIncorrectCommandWithTwoWord() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            executorTheCommand.executeCommand("осмотреться сеновал");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Некорректная команда!\r\n",consoleOutput);
    }
    @Test
    public void testExecuteIncorrectCommandWithNumbers() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            executorTheCommand.executeCommand("12355");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Некорректная команда!\r\n",consoleOutput);
    }
    @Test
    public void testExecuteIncorrectCommandWithEmptyMessage() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            executorTheCommand.executeCommand("");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Введи команду!\r\n",consoleOutput);
    }
    @Test
    public void testExecuteIncorrectCommandIfMessageIsBlank() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            executorTheCommand.executeCommand("   ");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Введи команду!\r\n",consoleOutput);
    }
    @Test
    public void testExecuteTakeAndMessageHasUnnecessarySpases() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            hayloft.setInventory(inventory);
            player.setLocation(hayloft);
            executorTheCommand.executeCommand("взять   вилы  ");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("У тебя есть вилы\r\n",consoleOutput);
    }
    @Test
    public void testExecuteCommandGo() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setLocation(stall);
            executorTheCommand.executeCommand("идти вверх");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Ты пришел в сеновал. На сеновале хранится сено. Спустившись вниз, ты окажешься в стойле. \r\n",consoleOutput);
        assertEquals(hayloft,player.getLocation());
    }
    @Test
    public void testExecuteCommandFeedAnimalWithoutFood() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            executorTheCommand.executeCommand("накормить корова");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("Нужно выбрать еду для корова\r\n",consoleOutput);

    }
    @Test
    public void testExecuteCommandUse() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            executorTheCommand.executeCommand("использовать вилы для сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("У тебя нет вилы\r\n",consoleOutput);

    }
    @Test
    public void testExecuteCommandFeedAnimal() {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream((100));
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            player.setCountFedAnimals(1);
            foods.add(hay);
            foodForAnimal = new FoodForAnimal(foods);
            player.setFoodForAnimal(foodForAnimal);
            stall.setAnimal(caw);
            player.setLocation(stall);
            executorTheCommand.executeCommand("накормить корова сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("корова накормлен.\r\n",consoleOutput);
        assertArrayEquals(new Food[]{},player.getFoodForAnimal().getFoods().toArray());
        assertFalse(caw.isHunger());
        assertEquals(2,player.getCountFedAnimals());

    }
    @Test
    public void testExecuteCommandFeedAllAnimalsAndPlayerWin() {

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
            executorTheCommand.executeCommand("накормить корова сено");
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOutPut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("корова накормлен.\r\n" + "Поздравляю! Ты накормил всех животных! Игра завершена!\r\n",consoleOutput);


    }


}
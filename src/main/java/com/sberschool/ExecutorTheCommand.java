package com.sberschool;

import org.apache.commons.lang3.StringUtils;

public class ExecutorTheCommand {
    Player player = new Player();
    public ExecutorTheCommand(Player player){
        this.player = player;
    }
    boolean stop = false;

    public void executeCommand(String message){

         if (message != null && !message.isBlank()) {
             message = StringUtils.normalizeSpace(message).toLowerCase();
             String[] words = StringUtils.split(message);
             if (words.length == 1) {
                 if (message.contains("старт")) {
                     System.out.println("Добро пожаловать на ферму! Помоги пожалуйста накормить животных!");


                 } else if (message.contains("осмотреться")) {
                     player.lookAround();
                 } else if (message.contains("инвентарь")) {
                     player.inventory();
                 } else if (message.contains("выйти")) {
                     stop = true;
                     System.out.println("Игра завершена!");
                 } else {
                     System.out.println("Некорректная команда!");
                 }
             } else if (words.length == 2) {
                 if (message.startsWith("взять")) {
                     String a = words[1];
                     player.take(a);
                 } else if (message.startsWith("идти")) {
                     String a = words[1];
                     player.go(a);
                 } else if (message.startsWith("накормить") && Animal.getAnimals().containsKey(words[1])) {
                     System.out.println("Нужно выбрать еду для " + words[1]);
                 } else {
                     System.out.println("Некорректная команда!");
                 }
             } else if (words.length == 3) {
                 if (message.startsWith("накормить")) {

                     String a = words[1];
                     String b = words[2];
                     player.feedAnimals(a, b);
                     if (player.getCountFedAnimals() == Animal.getAnimals().size()) {
                         System.out.println("Поздравляю! Ты накормил всех животных! Игра завершена!");
                         stop = true;
                     }

                 } else {
                     System.out.println("Некорректная команда!");
                 }
             } else if (words.length == 4) {
                 if (message.startsWith("использовать") && words[2].equals("для")) {
                     String a = words[1];
                     String b = words[3];
                     player.use(a, b);
                 } else {
                     System.out.println("Некорректная команда!");
                 }

             } else {
                 System.out.println("Некорректная команда!");
             }
         } else {
             System.out.println("Введи команду!");
         }
     }

}

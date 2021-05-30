# My Game - Ферма
console game FARM
## Описание
Это консольная игра, написанная на Java. Игрок вводит команду и программа выводит сообщение - результат его команды. Игрок может перемещаться по ферме, брать инвентарь, брать еду, кормить животных, предназначенной для них едой.

На ферме 7 локаций: центр фермы, стойло, сеновал, грядка с морковью, крольчатник, курятник, амбар. В них находятся животные,инвентарь или еда для животных. Для того, чтобы взять еду, нужно использовать специальный инвентарь.

На ферме есть следующие животные: корова, кролик, курица. Корову нужно накормить сеном. Кролика нужно накормить морковью. курицу нужно накормить зерном. 
## Как играть
Игрок должен вводить команды.

Нужно использовать следующие команды:

старт - чтобы начать игру;

осмотреться - чтобы получить сообщение с описанием местоположения игрока;

идти вверх/вниз/запад/восток/север/юг - чтобы изменить местоположение игрока;

взять "название инвентаря"(лопата/мешок/вилы) - пример: "взять лопата";

инвентарь - чтобы получить сообщение со списком инвентаря, который есть у игрока;

использовать "название инвентаря" для "название еды" - чтобы взять еду;

накормить "название животного" "название еды" - чтобы накормить животное;

выйти - чтобы завершить игру.

Для обозначения животных,предметов,еды нужно использовать слова в единственном числе и именительном падеже.

После каждой команды нужно нажать "Enter".

Чтобы выиграть нужно накормить всех трех животных.
## О проекте
В методе main создаются необходимые для игры объекты классов Location, Animal, Food, FoodForAnimal, Item, Inventory, создается объект класса Player - player. Класс Player содержит в себе основные методы, изменяющие состояние обьектов и выводящие результат изменения в виде сообщения. Класс ExecutorTheCommand содержит в себе метод executeCommand, который принимает в качестве аргумента введенную игроком команду, обрабатывает ее и вызывает соответствующие методы объекта player.

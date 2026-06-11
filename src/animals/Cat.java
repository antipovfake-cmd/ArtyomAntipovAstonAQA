package animals;

public class Cat extends Animal {

    // Статическая переменная для подсчета котов
    public static int catCount = 0;
    private boolean isFull; //Сытость кота (по умолчанию false, так как они создаются голодными)
    private final int appetite; //аппетит кота


    public Cat(String name, int appetite) {
        super(name, 200, 0);
        this.isFull = false;
        this.appetite = appetite;
        catCount++;
    }

    public void eat(Plate plate) {
        // Если кот уже сыт, ему незачем есть
        if (isFull) {
            System.out.println(name + " уже сыт.");
            return;
        }

        System.out.println(name + " хочет съесть " + appetite + " еды. (В миске: " + plate.getFood() + ")");

        // Попытка уменьшить еду
        if (plate.decreaseFood(appetite)) {
            this.isFull = true; // Успех: кот наелся полностью
            System.out.println(name + " успешно покушал! Статус: СЫТ.");
        } else {
            // Еды не хватило — кот к ней даже не прикоснулся
            System.out.println(name + " не стал трогать еду, так как её мало! Статус: ГОЛОДЕН.");
        }
    }

    public static int getCatCount() {
        return catCount;
    }

    // Геттер, чтобы узнать состояние сытости из другого класса
    public boolean isFull() {
        return isFull;
    }

    public void eat(Plate plate, int appetite) {
        System.out.println(name + " хочет есть " + appetite + " еды.");

        // Проверяем, удалось ли уменьшить количество еды в миске
        if (plate.decreaseFood(appetite)) {
            System.out.println(name + " успешно покушал. В миске осталось: " + plate.getFood());
        } else {
            System.out.println(name + " не стал кушать! В миске слишком мало еды (всего: " + plate.getFood() + ").");
        }
    }
}

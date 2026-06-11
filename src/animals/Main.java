package animals;

public class Main {
    public static void main(String[] args) {
        // Создаем миску с 30 единицами еды
        Plate plate = new Plate(30);

        Cat[] cats = {
                new Cat("Барсик", 10),
                new Cat("Мурзик", 15),
                new Cat("Рыжик", 12),// Ему не хватит еды в первый раз
                new Cat("Мартин", 13)// Ему не хватит еды в первый раз
        };

        System.out.println("--- Первый раунд кормления ---");
        for (Cat cat : cats) {
            cat.eat(plate);
        }

        System.out.println("\nРыжик и Мартин остались голодными. Досыпаем еду в миску...");
        // ИСПОЛЬЗОВАНИЕ МЕТОДА: Хозяин наполняет миску
        plate.addFood(20);

        System.out.println("\n--- Повторная попытка для голодных котов ---");
        for (Cat cat : cats) {
            // Коты пытаются поесть снова (внутри метода eat стоит проверка if (isFull) )
            cat.eat(plate);
        }

        System.out.println("\n--- Итоговый статус сытости ---");
        for (Cat cat : cats) {
            System.out.println("Кот " + cat.name + " -> " + (cat.isFull() ? "Сыт" : "Голоден"));
        }
    }
}


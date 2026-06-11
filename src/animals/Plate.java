package animals;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = Math.max(0, food);  // Исключаем отрицательное количество при создании объекта
    }

    // Метод для уменьшения еды в миске
    public boolean decreaseFood(int amount) {
        if (amount < 0 || food < amount) {
            return false; // Если еды меньше аппетита — метод вернет false, еда НЕ уменьшится
        }
        food -= amount;
        return true;
    }

    // Метод добавления еды в миску
    public void addFood(int amount) {
        if (amount > 0) {
            this.food += amount;
            System.out.println(">>> В миску добавили " + amount + " еды. Теперь в ней: " + food);
        } else {
            System.out.println(">>> Нельзя добавить отрицательное или нулевое количество еды!");
        }
    }

    public int getFood() {
        return food;
    }
}

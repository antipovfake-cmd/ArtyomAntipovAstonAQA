package animals;

public class Dog extends Animal {
    // Статическая переменная для подсчета собак
    private static int dogCount = 0;

    public Dog(String name, int maxRunDistance, int maxSwimDistance) {
        super(name, 500, 10);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }
}

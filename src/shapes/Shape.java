package shapes;

public interface Shape {

    // Абстрактные методы(каждая фигура реализует их по своему)
    double getArea();
    String getFillColor();
    String getBorderColor();
    String getName();

    default double getPerimeter(double... sides) {
        double sum = 0;
        for (double side : sides) {
            sum += side;
        }
        return sum;
    }

    // Дефолтный метод для красивого вывода характеристик фигуры в консоль
    default void printInfo() {
        System.out.printf("  Фигура: %s%n", getName());
        System.out.printf("  Площадь:       %.2f%n", getArea());
        System.out.printf("  Периметр:      %.2f%n", getPerimeter());
        System.out.printf("  Цвет заливки:  %s%n", getFillColor());
        System.out.printf("  Цвет границы:  %s%n", getBorderColor());
        System.out.println("-------------------------------------");
    }

    // Перегруженный метод без параметров, чтобы его было удобно вызывать у объекта
    double getPerimeter();
}

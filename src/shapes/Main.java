package shapes;

public class Main {
    public static void main(String[] args) {
        // Создаем массив фигур разного типа
        Shape[] shapes = {
                new Circle(5.0, "Красный", "Черный"),
                new Rectangle(4.0, 6.0, "Синий", "Белый"),
                new Triangle(3.0, 4.0, 5.0, "Зеленый", "Желтый")
        };

        System.out.println("=== ХАРАКТЕРИСТИКИ ГЕОМЕТРИЧЕСКИХ ФИГУР ===");
        System.out.println("-------------------------------------");

        // Выводим информацию по каждой фигуре с помощью полиморфизма
        for (Shape shape : shapes) {
            shape.printInfo();
        }

    }
}

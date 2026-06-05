import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //1.Задание №1
        Product laptop = new Product(
                "Laptop",
                LocalDate.of(2026, 06,05),
                "Asus",
                "China",
                85000.00,
                true
        );
        laptop.printInfo();

        //2.Задание №2
        Product[] products = new Product[5];

        products[0] = new Product("Смартфон", LocalDate.of(2026, 1, 10), "Samsung", "Вьетнам", 65000.0, false);
        products[1] = new Product("Ноутбук", LocalDate.of(2025, 11, 5), "Asus", "Китай", 95000.0, true);
        products[2] = new Product("Умные часы", LocalDate.of(2026, 3, 20), "Apple", "Китай", 35000.0, false);
        products[3] = new Product("Наушники", LocalDate.of(2025, 8, 14), "Sony", "Малайзия", 22000.0, false);
        products[4] = new Product("Монитор", LocalDate.of(2026, 2, 18), "LG", "Южная Корея", 28000.0, true);

        System.out.println("===СПИССОК ВСЕХ ТОВАРОВ В МАССИВЕ===");
        for (int i = 0; i < products.length; i++) {
            products[i].printInfo();
        }


        //3.Задание №3
        Park centralPark = new Park("Центральный Парк Культуры");

        // 2. Добавляем аттракционы через метод внешнего класса
        centralPark.addAttraction("Колесо обозрения", "10:00 - 22:00", 15.00);
        centralPark.addAttraction("Американские горки", "12:00 - 21:00", 25.50);

        // 3. Создаем объект внутреннего класса напрямую
        Park.Attraction carousel = new Park.Attraction("Карусель", "09:00 - 20:00", 8.00);
        centralPark.attractions.add(carousel);

        // 4. Выводим всю информацию
        centralPark.displayInformation();
    }
}

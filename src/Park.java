import java.util.ArrayList;
import java.util.List;

public class Park {
    private String name;
    List<Attraction> attractions;

    // Конструктор внешнего класса
    public Park(String name) {
        this.name = name;
        this.attractions = new ArrayList<>();
    }

    // Метод для добавления аттракциона через внешний класс
    public void addAttraction(String name, String workingHours, double cost) {
        Attraction newAttraction = new Attraction(name, workingHours, cost);
        this.attractions.add(newAttraction);
    }

    // Метод для вывода информации обо всех аттракционах
    public void displayInformation() {
        System.out.println("Парк: " + this.name);
        System.out.println("Список аттракционов:");
        for (Attraction attraction : attractions) {
            attraction.printDetails();
        }
    }

    public static class Attraction {
        private String attractionName;
        private String workingHours;
        private double cost;

        // Конструктор внутреннего класса
        public Attraction(String attractionName, String workingHours, double cost) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        // Метод вывода данных аттракциона
        public void printDetails() {
            // Внутренний класс имеет прямой доступ к членам внешнего класса (например, Park.this.name)
            System.out.printf("- %s | Время работы: %s | Стоимость: %.2f руб.\n",
                    this.attractionName, this.workingHours, this.cost);
        }
    }
}



package phonebook;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();

        // Добавляем контакты
        phonebook.add("Иванов", "+375-29-111-22-33");
        phonebook.add("Петров", "+375-33-222-33-44");
        phonebook.add("Иванов", "+375-44-555-66-77");
        phonebook.add("Сидоров", "+375-25-777-88-99");
        phonebook.add("Орлов", "+375-29-888-99-00");

        // Проверяем поиск для фамилии с несколькими номерами
        printNumbers(phonebook, "Иванов");

        // Проверяем поиск для фамилии с одним номером
        printNumbers(phonebook, "Петров");

        // Проверяем поиск отсутствующей фамилии
        printNumbers(phonebook, "Смирнов");
    }

    // Вспомогательный метод для вывода в консоль
    private static void printNumbers(Phonebook phonebook, String surname) {
        List<String> numbers = phonebook.get(surname);

        if (numbers.isEmpty()) {
            System.out.println("Фамилия " + surname + " не найдена в справочнике.");
        } else {
            System.out.println("Номера для фамилии " + surname + ": " + numbers);
        }
    }
}

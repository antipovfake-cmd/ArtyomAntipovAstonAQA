package phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phonebook {

    // Храним фамилию как ключ, а список номеров — как значение
    private final Map<String, List<String>> book = new HashMap<>();

    // Метод для добавления записи
    public void add(String surname, String phoneNumber) {
        // Если фамилии нет, создаем новый список. Добавляем номер.
        book.computeIfAbsent(surname, k -> new ArrayList<>()).add(phoneNumber);
    }

    // Метод для поиска номеров по фамилии
    public List<String> get(String surname) {
        // Возвращаем список номеров или пустой список, если фамилия не найдена
        return book.getOrDefault(surname, new ArrayList<>());
    }
}

package university;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Создаем коллекцию студентов
        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student("Иван", "А-1", 1, List.of(4, 5, 3, 4)));
        studentList.add(new Student("Ольга", "Б-2", 2, List.of(2, 2, 3, 2))); // балл < 3 (будет удалена)
        studentList.add(new Student("Пётр", "В-3", 1, List.of(3, 3, 3, 4)));
        studentList.add(new Student("Анна", "Г-4", 3, List.of(5, 5, 5, 5)));

        System.out.println("--- Исходный список ---");
        for (Student s : studentList) System.out.println(s);

        // 1. Метод удаления студентов со средним баллом < 3
        studentList.removeIf(student -> student.getAverageGrade() < 3.0);

        // 2. Метод перевода на следующий курс для оставшихся
        for (Student student : studentList) {
            student.moveToNextCourse();
        }

        System.out.println("\n--- После фильтрации и перевода ---");
        for (Student s : studentList) System.out.println(s);

        // Преобразуем в Set для демонстрации printStudents
        Set<Student> studentSet = new HashSet<>(studentList);

        System.out.println();
        // Проверяем, кто перевелся на 2-й курс
        printStudents(studentSet, 2);
    }

    // Метод вывода студентов определенного курса
    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты на " + course + " курсе:");
        boolean found = false;
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println("- " + student.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("(нет студентов)");
        }
    }
}

import exceptions.MyArraySizeException;
import exceptions.MyArrayDataException;
import services.ArrayProcessor;

public class Main {
    public static void main(String[] args) {
        ArrayProcessor processor = new ArrayProcessor();

        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] invalidSizeArray = {
                {"1", "2"},
                {"3", "4"}
        };

        String[][] invalidDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "eight"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = processor.processArray(validArray);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            int result = processor.processArray(invalidSizeArray);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            int result = processor.processArray(invalidDataArray);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        // Генерация ArrayIndexOutOfBoundsException
        String[] array = new String[2];
        try {
            System.out.println(array[3]); // Попытка доступа к несуществующему индексу
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение: " + e.getMessage());
        }
    }
}

package exceptions;

// Исключение для обработки некорректных данных в ячейках массива.
public class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}

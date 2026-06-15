package exceptions;

// Исключение для обработки неверного размера массива.
public class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

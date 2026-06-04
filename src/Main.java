import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //1. Создайте метод printThreeWords(), который при вызове должен отпечатать в столбец три слова: Orange, Banana, Apple
        printThreeWords();


        //2. Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b, и инициализируйте их любыми
        // значениями, которыми захотите. Далее метод должен просуммировать эти переменные, и если их сумма больше или равна 0,
        // то вывести в консоль сообщение “Сумма положительная”, в противном случае - “Сумма отрицательная”;
        checkSumSign();


        //3. Создайте метод printColor() в теле которого задайте int переменную value и инициализируйте ее любым значением.
        //Если value меньше 0 (0 включительно), то в консоль метод должен вывести сообщение “Красный”, если лежит в пределах
        // от 0 (0 исключительно) до 100 (100 включительно), то “Желтый”, если больше 100 (100 исключительно) - “Зеленый”;
        printColor();


        //4. Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b, и инициализируйте их любыми
        // значениями, которыми захотите. Если a больше или равно b, то необходимо вывести в консоль сообщение “a >= b”,
        // в противном случае “a < b”;
        compareNumbers();


        //5. Напишите метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10 до 20
        // (включительно), если да – вернуть true, в противном случае – false.
        int[] numbers = scannerNumbers();
        boolean result = isSumInRange(numbers[0], numbers[1]);
        System.out.println("Результат проверки: " + result);


        //6. Напишите метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
        // положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
        checkNumberSign(scannerNumber());


        //7. Напишите метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число
        // отрицательное, и вернуть false если положительное. Замечание: ноль считаем положительным числом.
        System.out.println(isNegative(scannerNumber()));


        //8. Напишите метод, которому в качестве аргументов передается строка и число, метод должен отпечатать в консоль
        // указанную строку, указанное количество раз;
        readAndPrintString();


        //9. Напишите метод, который определяет, является ли год високосным, и возвращает boolean (високосный - true,
        // не високосный - false). Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
        int year = readYearFromConsole();
        boolean isLeap = isLeapYear(year);
        System.out.println("Год " + year + " високосный? " + isLeap);


        //10. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        // С помощью цикла и условия заменить 0 на 1, 1 на 0;

        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("До:    " + Arrays.toString(arr));

        invertArray(arr);
        System.out.println("После: " + Arrays.toString(arr));


        //11. Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 ... 100;
        int[] numbers1 = new int[100];
        fillArray(numbers1);
        System.out.println(Arrays.toString(numbers1));


        //12. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
        int[] numbers2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Массив до:    " + Arrays.toString(numbers2));
        getMultipliedArray(numbers2);


        //13. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью
        // цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно).
        // Определить элементы одной из диагоналей можно по следующему принципу: индексы таких элементов равны, то есть
        // [0][0], [1][1], [2][2], ..., [n][n];
        int size = 5;
        int[][] myMatrix = createDiagonalMatrix(size);
        printMatrix(myMatrix);


        //14. Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив
        // типа int длиной len, каждая ячейка которого равна initialValue.
        Scanner scanner = new Scanner(System.in);

        int len = readInt(scanner, "Введите длину массива: ");
        int initialValue = readInt(scanner, "Введите значение для заполнения: ");

        int[] myArr = createAndFillArray(len, initialValue);

        System.out.println("Результат: " + Arrays.toString(myArr));

        scanner.close();

    }


    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первое число: ");
        int a = scanner.nextInt();

        System.out.println("Введите второе число: ");
        int b = scanner.nextInt();

        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите любое число");
        int value = scanner.nextInt();

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первое число: ");
        int a = scanner.nextInt();
        System.out.println("Введите второе число: ");
        int b = scanner.nextInt();

        if (a >= b) {
            System.out.println("Число " + a + " >= " + b);
        } else {
            System.out.println("Число " + a + " < " + b);
        }
    }

    public static int[] scannerNumbers() {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[2];
        System.out.println("Введите первое число: ");
        numbers[0] = scanner.nextInt();
        System.out.println("Введите второе число: ");
        numbers[1] = scanner.nextInt();
        return numbers;
    }

    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static int scannerNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите любое число: ");
        return scanner.nextInt();
    }

    public static void checkNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }

    public static void readAndPrintString() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строку: ");
        String text = scanner.nextLine();

        System.out.println("Введите количество повторений: ");
        int count = scanner.nextInt();

        printStringMultipleTimes(text, count);
    }

    public static void printStringMultipleTimes(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    public static int readYearFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int year;
        while (true) {
            System.out.print("Введите год: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year > 0) {
                    break;
                } else {
                    System.out.println("Ошибка! Год должен быть больше 0.");
                }
            } else {
                System.out.println("Ошибка! Введите корректное целое число.");
                scanner.next();
            }
        }

        return year;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] == 0) {
                array[i] = 1;
            } else if (array[i] == 1) {
                array[i] = 0;
            }
        }
    }

    public static void fillArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }

    public static void getMultipliedArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        System.out.println("Массив после: " + Arrays.toString(array));
    }

    public static int[][] createDiagonalMatrix(int size) {
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;                // Главная диагональ
            matrix[i][size - 1 - i] = 1;    // Побочная диагональ
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int readInt(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    public static int[] createAndFillArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

}

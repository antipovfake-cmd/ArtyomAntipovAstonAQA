package main;

public class FactorialCalculator {
    public long compute(int n) {
        if (n < 0) throw new IllegalArgumentException("Число не должно быть отрицательным");
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}


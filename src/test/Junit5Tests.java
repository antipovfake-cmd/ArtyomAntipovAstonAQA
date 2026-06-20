package test;

import main.Calculator;
import main.FactorialCalculator;
import main.NumberComparator;
import main.TriangleAreaCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Junit5Tests {
    private final FactorialCalculator factorial = new FactorialCalculator();
    private final TriangleAreaCalculator triangle = new TriangleAreaCalculator();
    private final Calculator calculator = new Calculator();
    private final NumberComparator comparator = new NumberComparator();

    @Test
    void testFactorial() {
        assertEquals(1, factorial.compute(0));
        assertEquals(120, factorial.compute(5));
        assertThrows(IllegalArgumentException.class, () -> factorial.compute(-1));
    }

    @Test
    void testTriangleArea() {
        assertEquals(6.0, triangle.calculateArea(3, 4, 5), 0.001);
        assertThrows(IllegalArgumentException.class, () -> triangle.calculateArea(1, 2, 3));
    }

    @Test
    void testCalculator() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(1, calculator.subtract(3, 2));
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(2.5, calculator.divide(5, 2));
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
    }

    @Test
    void testComparator() {
        assertEquals(0, comparator.compare(5, 5));
        assertTrue(comparator.compare(3, 5) < 0);
        assertTrue(comparator.compare(7, 5) > 0);
    }
}

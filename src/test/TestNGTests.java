package test;

import org.testng.annotations.Test;
import org.testng.Assert;

import main.Calculator;
import main.FactorialCalculator;
import main.NumberComparator;
import main.TriangleAreaCalculator;


public class TestNGTests {
    private final FactorialCalculator factorial = new FactorialCalculator();
    private final TriangleAreaCalculator triangle = new TriangleAreaCalculator();
    private final Calculator calculator = new Calculator();
    private final NumberComparator comparator = new NumberComparator();

    @Test
    public void testFactorial() {
        Assert.assertEquals(factorial.compute(0), 1);
        Assert.assertEquals(factorial.compute(5), 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialException() {
        factorial.compute(-1);
    }

    @Test
    public void testTriangleArea() {
        Assert.assertEquals(triangle.calculateArea(3, 4, 5), 6.0, 0.001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaException() {
        triangle.calculateArea(1, 2, 3);
    }

    @Test
    public void testCalculator() {
        Assert.assertEquals(calculator.add(2, 3), 5);
        Assert.assertEquals(calculator.subtract(3, 2), 1);
        Assert.assertEquals(calculator.multiply(2, 3), 6);
        Assert.assertEquals(calculator.divide(5, 2), 2.5);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testCalculatorException() {
        calculator.divide(5, 0);
    }

    @Test
    public void testComparator() {
        Assert.assertEquals(comparator.compare(5, 5), 0);
        Assert.assertTrue(comparator.compare(3, 5) < 0);
        Assert.assertTrue(comparator.compare(7, 5) > 0);
    }
}

package by.aston.lesson10.testng;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.expectThrows;
import static org.testng.Assert.assertEquals;

public class FactorialTest {
    private Factorial factorial;

    @BeforeMethod
    public void setUp() {
        factorial = new Factorial();
    }

    @Test
    public void testFactorialOfZero() {
        assertEquals(1, factorial.calculateFactorial(0));
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        assertEquals(120, factorial.calculateFactorial(5));
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> {
            factorial.calculateFactorial(-5);
        });
        assertEquals("Факториала от целых отрицательных чисел не существует", exception.getMessage());
    }
}
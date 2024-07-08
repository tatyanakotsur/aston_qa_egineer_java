package test.java.by.aston.lesson10.junit5;

import main.java.by.aston.lesson10.junit5.Factorial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {
    private Factorial factorial;

    @BeforeEach
    void setUp() {
        factorial = new Factorial();
    }

    @Test
    void testFactorialOfZero() {
        assertEquals(1, factorial.calculateFactorial(0));
    }

    @Test
    void testFactorialOfPositiveNumber() {
        assertEquals(120, factorial.calculateFactorial(5));
    }

    @Test
    void testFactorialOfNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            factorial.calculateFactorial(-5);
        });
    }
}
package main.java.by.aston.lesson10.junit5;

public class Factorial {

    public long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториала от целых отрицательных чисел не существует");
        }
        if (n == 0) {
            return 1;
        }
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}



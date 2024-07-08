package main.java.by.aston.lesson10.junit5;

import java.util.Scanner;

public class Lesson10Junit5Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число для вычисления факториала: ");
        int number = scanner.nextInt();

        try {
            long result = new Factorial().calculateFactorial(number);
            System.out.println("Факториал числа " + number + ": " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}

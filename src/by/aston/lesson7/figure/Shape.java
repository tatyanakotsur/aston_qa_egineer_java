package by.aston.lesson7.figure;

public interface Shape {

    default void printPerimeter() {
        System.out.println("Perimeter: " + calculatePerimeter());
    }

    default void printArea() {
        System.out.println("Area: " + calculateArea());
    }

    double calculatePerimeter();

    double calculateArea();
}

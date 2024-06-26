package by.aston.lesson7;

import by.aston.lesson7.animal.Animal;
import by.aston.lesson7.animal.Bowl;
import by.aston.lesson7.animal.Cat;
import by.aston.lesson7.animal.Dog;
import by.aston.lesson7.figure.Circle;
import by.aston.lesson7.figure.Rectangle;
import by.aston.lesson7.figure.Triangle;

public class Lesson7Main {
    public static void main(String[] args) {

        Animal catVaska = new Cat("Vaska");
        Animal catMurzik = new Cat("Murzik");
        Animal catBarsik = new Cat("Barsik");
        Animal dogBobik = new Dog("Bobik");
        Animal dogTuzik = new Dog("Tuzik");

        System.out.println("Количество животных: " + Animal.getAnimalCount());
        System.out.println("Количество котов: " + Cat.getCatCount());
        System.out.println("Количество собак: " + Dog.getDogCount());
        System.out.println();

        Animal[] animals = {catVaska, catMurzik, catBarsik, dogBobik, dogTuzik};

        for (Animal animal : animals) {
            animal.run(250);
        }
        System.out.println();

        for (Animal animal : animals) {
            animal.run(150);
        }
        System.out.println();

        catVaska.run(200);
        catMurzik.run(210);
        catBarsik.run(150);
        dogBobik.run(400);
        dogTuzik.run(550);
        System.out.println();

        for (Animal animal : animals) {
            animal.swim(10);
        }
        System.out.println();

        catVaska.swim(2);
        catMurzik.swim(3);
        catBarsik.swim(5);
        dogBobik.swim(15);
        dogTuzik.swim(8);
        System.out.println();

        Cat[] cats = {(Cat) catVaska, (Cat) catMurzik, (Cat) catBarsik};

        Bowl bowl = new Bowl(20);
        bowl.addFood(5);
        System.out.println();

        for (Cat cat : cats) {
            cat.eat(bowl, 10);
        }
        System.out.println();

        for (Cat cat : cats) {
            System.out.println(cat.getName() + " сытость: " + (cat.isSatiety() ? "сыт" : "голоден"));
        }


        Circle circle = new Circle(5, "Red", "Black");
        System.out.println("\nCircle ");
        circle.printPerimeter();
        circle.printArea();
        System.out.println("Fill Color: " + circle.getFillColor());
        System.out.println("Border Color: " + circle.getBorderColor());

        Rectangle rectangle = new Rectangle(4, 6, "Blue", "Green");
        System.out.println("\nRectangle ");
        rectangle.printPerimeter();
        rectangle.printArea();
        System.out.println("Fill Color: " + rectangle.getFillColor());
        System.out.println("Border Color: " + rectangle.getBorderColor());

        Triangle triangle = new Triangle(3, 4, 5, "Yellow", "Purple");
        System.out.println("\nTriangle ");
        triangle.printPerimeter();
        triangle.printArea();
        System.out.println("Fill Color: " + triangle.getFillColor());
        System.out.println("Border Color: " + triangle.getBorderColor());
    }
}

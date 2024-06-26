package by.aston.lesson7.figure;

public class Rectangle extends Figure implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        super(fillColor, borderColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

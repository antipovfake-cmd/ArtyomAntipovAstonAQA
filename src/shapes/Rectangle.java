package shapes;

public class Rectangle implements Shape {
    public double width;
    public double height;
    public String fillColor;
    public String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public String getName() {
        return "Прямоугольник";
    }

    @Override
    public double getPerimeter() {
        return Shape.super.getPerimeter(width, width, height, height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

}

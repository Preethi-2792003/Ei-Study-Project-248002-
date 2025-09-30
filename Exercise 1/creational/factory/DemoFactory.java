interface Shape {
    void draw();
}
class Circle implements Shape {
    public void draw() { System.out.println("Drawing a Circle."); }
}
class Square implements Shape {
    public void draw() { System.out.println("Drawing a Square."); }
}
class ShapeFactory {
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("circle")) return new Circle();
        if (type.equalsIgnoreCase("square")) return new Square();
        return null;
    }
}
public class DemoFactory {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape s1 = factory.getShape("circle");
        s1.draw();
        Shape s2 = factory.getShape("square");
        s2.draw();
    }
}
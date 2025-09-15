

interface Shape {
    double area();
    double perimeter();
}

// File: Circle.java
class Circle implements Shape {
    private double radius;
    public Circle(double radius) { this.radius = radius; }

    
    public double area() { return Math.PI * radius * radius; }

    
    public double perimeter() { return 2 * Math.PI * radius; }
}


public class One {
    public static void main(String[] args) {
        Shape c = new Circle(3.0);
        System.out.printf("Circle (r=3.0) area=%.4f perimeter=%.4f%n", c.area(), c.perimeter());
}
}
package java_learn.solid;

/**
 * Open/Closed Principle (Принцип открытости/закрытости)
 * Классы должны быть открыты для расширения, но закрыты для модификации.
 * Это значит, что поведение класса можно расширить, не изменяя его исходный код.
 */
public class OpenClosePrinciple {
    interface Shape {
        double calculateArea();
    }

    static class Circle implements Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }

    static class Rectangle implements Shape {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double calculateArea() {
            return width * height;
        }
    }

    static class AreaCalculator {
        public double calculateArea(Shape shape) {
            return shape.calculateArea();
        }
    }
}

package java_learn.solid;

/**
 * Liskov Substitution Principle (Принцип подстановки Барбары Лисков)
 * Объекты должны быть заменяемыми на экземпляры их подклассов без изменения правильности программы.
 * Подкласс должен расширять родительский класс, не нарушая его поведение.
 */
public class LiskovSubstitutionPrinciple {
    abstract static class Bird {
        public abstract void move();
    }

    static class FlyingBird extends Bird {
        @Override
        public void move() {
            fly();
        }

        private void fly() {
            // реализация полета
        }
    }

    static class Penguin extends Bird {
        @Override
        public void move() {
            walk();
        }

        private void walk() {
            // реализация ходьбы
        }
    }
}

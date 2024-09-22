package java_learn.solid;

/**
 * Interface Segregation Principle (Принцип разделения интерфейсов)
 * Клиенты не должны зависеть от интерфейсов, которые они не используют.
 * Лучше создать несколько специализированных интерфейсов, чем один универсальный.
 */
public class InterfaceSegregationPrinciple {
    interface Workable {
        void work();
    }

    interface Eatable {
        void eat();
    }

    static class Developer implements Workable, Eatable {
        public void work() {
            // разработка
        }

        public void eat() {
            // еда
        }
    }

    static class Robot implements Workable {
        public void work() {
            // работа
        }
    }

}

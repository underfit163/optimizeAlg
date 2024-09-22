package java_learn.solid;

import java.util.List;

/**
 * Dependency Inversion Principle (Принцип инверсии зависимостей)
 * Модули верхнего уровня не должны зависеть от модулей нижнего уровня. Оба типа модулей должны зависеть от абстракций.
 * Абстракции не должны зависеть от деталей. Детали должны зависеть от абстракций.
 */
public class DependencyInversionPrinciple {
    interface Developer {
        void develop();
    }

    static class BackendDeveloper implements Developer {
        public void develop() {
            writeJavaCode();
        }

        private void writeJavaCode() {
            // написание кода на Java
        }
    }

    static class FrontendDeveloper implements Developer {
        public void develop() {
            writeJavaScriptCode();
        }

        private void writeJavaScriptCode() {
            // написание кода на JavaScript
        }
    }

    static class Project {
        private final List<Developer> developers;

        public Project(List<Developer> developers) {
            this.developers = developers;
        }

        public void implement() {
            for (Developer developer : developers) {
                developer.develop();
            }
        }
    }

}

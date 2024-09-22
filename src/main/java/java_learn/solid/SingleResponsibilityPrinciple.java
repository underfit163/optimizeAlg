package java_learn.solid;

/**
 * Single Responsibility Principle (Принцип единственной ответственности)
 * Каждый класс должен отвечать только за одну задачу.
 * Если класс имеет много обязанностей,
 * это может привести к его изменению при изменении любой из этих обязанностей, что затрудняет поддержку.
 */
public class SingleResponsibilityPrinciple {

    public static class Employee {
        private String name;
        private int id;

        // методы работы с сотрудником
    }

    public static class EmployeePayCalculator {
        public void calculatePay(Employee employee) {
            // расчет зарплаты
        }
    }

    public static class EmployeeRepository {
        public void saveToDatabase(Employee employee) {
            // сохранение данных в базу
        }
    }
}

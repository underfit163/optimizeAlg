package lab6;

import java.util.ArrayList;
import java.util.List;

/*
 2. Имеется центр обработки звонков с тремя уровнями сотрудников: оператор, менеджер и директор.
 Входящий телефонный звонок адресуется свободному оператору. Если оператор не может обработать звонок,
 он автоматически перенаправляется менеджеру. Если менеджер занят, то звонок перенаправляется директору.
 Разработайте классы и структуры данных для этой задачи.
 Реализуйте метод dispatchCall(), который перенаправляет звонок первому свободному сотруднику.
 */
public class Main {
    //очередь звонков реализовать
    public static void main(String[] args) throws InterruptedException {
        Employee director = new Director(false);
        Employee manager = new Manager(false, director);

        List<Employee> operators = new ArrayList<>();
        int countOperators = 3;
        System.out.println("Число операторов на смене: " + countOperators);
        for (int i = 0; i < countOperators; i++) {
            operators.add(new Operator(false, manager));
        }
        CallCenter callCenter = new CallCenter(operators);

        for (int i = 0; i < 8; i++) {
            System.out.println("Поступает звонок:");
            callCenter.call("Звонок " + (i + 1));
            System.out.println();
        }
    }
}

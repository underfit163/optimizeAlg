package lab6;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CallCenter {
    private final List<Employee> operators;
    private final Queue<String> queueCall = new LinkedList<>();

    public CallCenter(List<Employee> operators) {
        this.operators = operators;
    }

    public List<Employee> getOperators() {
        return operators;
    }

    public void call(String call) throws InterruptedException {
        queueCall.add(call);
        Employee employee = operators.stream()
                .filter(x -> !x.isBusy())
                .findFirst()
                .orElse(operators.get(0));
        System.out.print("Очередь звонков до обработки: ");
        queueCall.forEach(x-> System.out.print(x+ " "));
        System.out.println();
        System.out.println("Номер оператор к которому поступил звонок: " + (operators.indexOf(employee) + 1));
        if (employee.dispatchCall()) {
            System.out.println(queueCall.poll() + " забрали в обработку из очереди.");
        }
        System.out.print("Очередь звонков после обработки: ");
        queueCall.forEach(x-> System.out.print(x+ " "));
        System.out.println();
    }
}

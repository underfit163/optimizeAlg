package lab6;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class CallCenter {
    private final List<Employee> operators;
    private final ArrayBlockingQueue<String> queueCall = new ArrayBlockingQueue<>(10);

    public CallCenter(List<Employee> operators) {
        this.operators = operators;
    }

    public List<Employee> getOperators() {
        return operators;
    }

    public void call(String call) throws InterruptedException {
        queueCall.put(call);
        Employee employee = operators.stream()
                .filter(x -> !x.isBusy())
                .findFirst()
                .orElse(operators.get(0));
        System.out.print("Очередь звонков до обработки: ");
        queueCall.forEach(x-> System.out.print(x+ " "));
        System.out.println();
        System.out.println("Номер оператор к которому поступил звонок: " + (operators.indexOf(employee) + 1));
        if (employee.dispatchCall()) {
            queueCall.take();
        }
        System.out.print("Очередь звонков после обработки: ");
        queueCall.forEach(x-> System.out.print(x+ " "));
        System.out.println();
    }
}

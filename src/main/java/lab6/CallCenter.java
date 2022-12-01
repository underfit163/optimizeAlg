package lab6;

import java.util.List;
import java.util.Random;

public class CallCenter {
    private List<Employee> operators;

    public CallCenter(List<Employee> operators) {
        this.operators = operators;
    }

    public List<Employee> getOperators() {
        return operators;
    }

    public void call() {
        Random random = new Random();
        int numOperator = random.nextInt(operators.size());
        System.out.println("Номер оператор к которому пришел звонок: " + (numOperator+1));
        Employee operator = operators.get(numOperator);
        operator.dispatchCall();
        operator.setBusy(true);
    }
}

package lab6;

public class Operator implements Employee {
    private final Employee employee;
    private boolean isBusy;

    public Operator(boolean isBusy, Employee employee) {
        this.isBusy = isBusy;
        this.employee = employee;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public boolean dispatchCall() {
        if (isBusy) {
            System.out.println("Извините, оператор не может обработать ваш звонок, перенаправляю вас к менеджеру...");
            return employee.dispatchCall();
        } else {
            System.out.println("Здравствуйте, я оператор, готов ответить на ваши вопросы...");
            setBusy(true);
            return true;
        }
    }
}

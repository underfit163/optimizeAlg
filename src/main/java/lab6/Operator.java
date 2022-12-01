package lab6;

public class Operator implements Employee {
    private Employee employee;
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
    public void dispatchCall() {
        if (isBusy) {
            System.out.println("Извините, оператор не может обработать ваш звонок, перенаправляю вас к менеджеру...");
            employee.dispatchCall();
        } else {
            System.out.println("Здравствуйте, я оператор, готов ответить на ваши вопросы...");
        }
    }
}

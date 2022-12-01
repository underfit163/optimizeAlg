package lab6;

public class Manager implements Employee {
    private Employee employee;
    private boolean isBusy;

    public Manager(boolean isBusy, Employee employee) {
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
            System.out.println("Извините, менеджер не может вам ответить на данный момент, перенаправляю вас к директору...");
            employee.dispatchCall();
        } else {
            System.out.println("Здравствуйте, я менеджер, готов ответить на ваши вопросы...");
            setBusy(true);
        }
    }
}

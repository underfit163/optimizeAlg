package lab6;

public class Director implements Employee {
    private boolean isBusy;

    public Director(boolean isBusy) {
        this.isBusy = isBusy;
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
            System.out.println("Извините, директор не может вам ответить на данный момент.");
        } else {
            System.out.println("Здравствуйте, я директор, готов ответить на ваши вопросы...");
        }
    }
}

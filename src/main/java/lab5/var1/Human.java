package lab5.var1;

public class Human {
    private boolean blueEyes;

    private int blueManCount;

    public Human(boolean blueEyes) {
        this.blueEyes = blueEyes;
    }

    public boolean isBlueEyes() {
        return blueEyes;
    }

    public void setBlueEyes(boolean blueEyes) {
        this.blueEyes = blueEyes;
    }

    public int getBlueManCount() {
        return blueManCount;
    }

    public void setBlueManCount(int blueManCount) {
        this.blueManCount = blueManCount;
    }

    @Override
    public String toString() {
        return "Human{" +
                "blueEyes=" + blueEyes +
                '}';
    }
}

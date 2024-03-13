package exam.task1;

public class Box {
    int h;
    int w;

    public Box(int h, int w) {
        this.h = h;
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "Box{" +
                "h=" + h +
                ", w=" + w +
                '}';
    }
}

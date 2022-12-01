package lab5.var2;

import java.util.Random;

public class Bottle {
    private int bottleNumber;
    private int pillsCount;
    private double weightEachPill;

    public Bottle(int bottleNumber, double weightEachPill) {
        this.bottleNumber = bottleNumber;
        this.pillsCount = new Random().nextInt((100 - 20) + 1) + 1;
        this.weightEachPill = weightEachPill;
    }

    public int getBottleNumber() {
        return bottleNumber;
    }

    public void setBottleNumber(int bottleNumber) {
        this.bottleNumber = bottleNumber;
    }

    public double getWeightEachPill() {
        return weightEachPill;
    }

    public void setWeightEachPill(double weightEachPill) {
        this.weightEachPill = weightEachPill;
    }
}

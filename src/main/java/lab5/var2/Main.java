package lab5.var2;

import java.util.ArrayList;
import java.util.List;

//1. Есть 20 баночек с таблетками.
// В 19 баночках лежат таблетки весом 1 г, а в одной - весом 1,1 г.
// Даны весы, показывающие точный вес.
// Как за одно взвешивание найти банку с тяжелыми таблетками?
public class Main {
    public static void main(String[] args) {
        List<Bottle> bottles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            bottles.add(new Bottle(i + 1, 1.0));
        }
        replaceBottleWithHeavierPills(bottles, 11);
        System.out.println(findBottleWithHeavyPill(bottles));
    }

    public static int findBottleWithHeavyPill(List<Bottle> bottles) {
        double standardWeight = 0.0;
        double actualWeight = 0.0;
        for (Bottle bottle : bottles) {
            standardWeight += bottle.getBottleNumber() * 1.0;
            actualWeight += bottle.getBottleNumber() * bottle.getWeightEachPill();
        }
        System.out.println(standardWeight);
        System.out.println(actualWeight);
        double difference = Math.round((actualWeight - standardWeight) / 0.1);//количество испорченных таблеток = лишний вес / 0,1
        int bottleNumber = (int) difference;
        if (bottleNumber > 0 || bottleNumber < bottles.size()) {
            return bottleNumber;
        }
        return 0;
    }

    public static void replaceBottleWithHeavierPills(List<Bottle> bottles, int bottleNumber) {
        if (bottleNumber > 0 && bottleNumber <= bottles.size()) {
            Bottle bottleToReplacePills = bottles.get(bottleNumber - 1);
            bottleToReplacePills.setWeightEachPill(1.1);
        }
    }
}

package lab5;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Island {
    private int day;

    private List<Human> people;

    public Island(List<Human> people) {
        this.people = people;
    }

    public int outIsland() {
        //people.stream().sorted(Comparator.comparing(Human::isBlueEyes).reversed()).forEach(x-> System.out.println(x.isBlueEyes()));
        int minBlueCount = 1;
        Human seeHuman;
        for (int i = 0; i < people.size(); i++) {
            Human human = people.get(i);
            for (int j = 0; j < people.size(); j++) {
                if (i != j) {
                    seeHuman = people.get(j);
                    if (seeHuman.isBlueEyes()) {
                        human.setBlueManCount(human.getBlueManCount() + 1);
                    }
                }
            }
            if (human.getBlueManCount() == 0) {
                return minBlueCount;
            } else if (i == 0) {
                minBlueCount = human.getBlueManCount();
            } else if (minBlueCount < human.getBlueManCount())
                return minBlueCount + 1;
            else if (minBlueCount > human.getBlueManCount())
                return human.getBlueManCount() + 1;
        }
        return 1;
    }
}

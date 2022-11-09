package lab5;

import java.util.List;

public class Island {
    private int day;

    private List<Human> people;

    public Island(List<Human> people) {
        this.people = people;
    }

    public int outIsland() {
        Human seeHuman;
        for (int i = 0; i < people.size(); i++) {
            Human human = people.get(i);
            for (int j = 0; j < people.size(); j++) {
                if (i != j) {
                    seeHuman = people.get(i);
                    if (seeHuman.isBlueEyes()) {
                        human.getWithBlueEyes().add(seeHuman);
                    }
                    if (human.getWithBlueEyes().size() == 0) {
                        return 1;
                    }
                }
            }

        }
        return -1;
    }
}

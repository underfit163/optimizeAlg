package lab5;

import java.util.ArrayList;
import java.util.List;

public class Human {
    private boolean blueEyes;
    private boolean onIsland;

    private List<Human> withBlueEyes;

    public Human(boolean blueEyes) {
        this.blueEyes = blueEyes;
        this.onIsland = true;
        this.withBlueEyes = new ArrayList<>();
    }

    public boolean isBlueEyes() {
        return blueEyes;
    }

    public void setBlueEyes(boolean blueEyes) {
        this.blueEyes = blueEyes;
    }

    public boolean isOnIsland() {
        return onIsland;
    }

    public void setOnIsland(boolean onIsland) {
        this.onIsland = onIsland;
    }

    public List<Human> getWithBlueEyes() {
        return withBlueEyes;
    }

    public void setWithBlueEyes(List<Human> withBlueEyes) {
        this.withBlueEyes = withBlueEyes;
    }
}

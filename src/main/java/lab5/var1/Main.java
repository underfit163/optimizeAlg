package lab5.var1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
На остров приезжает гонец со странным приказом: все голубоглазые люди должны как можно скорее покинуть остров.
Самолет улетает каждый вечер в 20:00.
Каждый человек может видеть цвет глаз других, но не знает цвет собственных
(и никто не имеет права сказать человеку, какой у него цвет глаз).
Жители острова не знают, сколько на нем живет голубоглазых;
известно лишь то, что есть минимум один.
Сколько дней потребуется, чтобы все голубоглазые уехали?
 */
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Human> people = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Human human = new Human(random.nextBoolean());
            people.add(human);
            System.out.println(human);
        }
        Island island = new Island(people);
        System.out.println(island.outIsland() + " дней потребуется, чтобы синеглазые улетели с острова");
    }
}

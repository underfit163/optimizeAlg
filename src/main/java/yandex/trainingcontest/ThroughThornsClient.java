package yandex.trainingcontest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class ThroughThornsClient {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
             FileWriter fileWriter = new FileWriter("output.txt")) {
            int count = Integer.parseInt(bufferedReader.readLine());
            Map<Integer, Set<RocketEvent>> rocketEvents = new TreeMap<>();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < count; i++) {
                String[] input = bufferedReader.readLine().split(" ");

                char status = input[4].charAt(0);
                if (status == 'B') continue;

                int day = Integer.parseInt(input[0]);
                int hour = Integer.parseInt(input[1]);
                int minute = Integer.parseInt(input[2]);
                int id = Integer.parseInt(input[3]);


                int time = day * 24 * 60 + hour * 60 + minute;

                RocketEvent event = new RocketEvent(time, status);
                if (rocketEvents.containsKey(id)) {
                    rocketEvents.get(id).add(event);
                } else {
                    Set<RocketEvent> events = new TreeSet<>(Comparator.comparingInt(x -> x.time));
                    events.add(event);
                    rocketEvents.put(id, events);
                }
            }

            for (Set<RocketEvent> rocketEventSet : rocketEvents.values()) {
                int sumTime = 0;

                for (RocketEvent rocketEvent : rocketEventSet) {
                    if (rocketEvent.status == 'A') sumTime -= rocketEvent.time;
                    else sumTime += rocketEvent.time;
                }
                result.append(sumTime).append(" ");
            }


            fileWriter.write(result.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static class RocketEvent {
        int time;
        char status;

        public RocketEvent(int time, char status) {
            this.time = time;
            this.status = status;
        }
    }
}

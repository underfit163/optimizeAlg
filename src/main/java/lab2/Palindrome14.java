package lab2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Palindrome14 {
    public boolean test14(String s) {
        s = s.toLowerCase().trim().replaceAll(" ", "");
        StringBuilder stringBuilder = new StringBuilder(s);
        Map<Character, Integer> charMap = new HashMap<>();
        char ch;
        for (int i = 0; i < stringBuilder.length(); i++) {
            ch = stringBuilder.charAt(i);
            if (charMap.containsKey(ch)) {
                charMap.put(ch, charMap.get(ch) + 1);
            } else {
                charMap.put(ch, 1);
            }
        }

        charMap.forEach((x, y) -> System.out.println(x + " " + y));

        AtomicInteger countOdd = new AtomicInteger();
        charMap.values().forEach(val -> {
            if (val % 2 == 1) countOdd.getAndIncrement();
        });
        return countOdd.get() <= 1;
    }

    public boolean test14v2(String s) {
        s = s.toLowerCase().trim().replaceAll(" ", "");
        StringBuilder stringBuilder = new StringBuilder(s);
        int chId = 0;
        for (int i = 0; i < stringBuilder.length(); i++) {
            chId ^= stringBuilder.codePointAt(i);
        }
        return stringBuilder.indexOf("" + (char) chId) != -1 || chId == 0;
    }
}

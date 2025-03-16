package java_year_2025;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class First {
    public static void main(String[] args) {
        checkResult("cba", 'c');
        checkResult("abc", 'a');
        checkResult("abca", 'b');
        checkResult("abcab", 'c');
        checkResult("abcabcd", 'd');
    }


    public static Character getFirstUnique(String st) {
        if (Objects.isNull(st) || st.isEmpty()) return ' ';
        Map<Character, Integer> countChars = new LinkedHashMap<>();

        for (int i = 0; i < st.length(); i++) {
            countChars.put(st.charAt(i), countChars.getOrDefault(st.charAt(i), 0) + 1);
        }

        for (Map.Entry<Character, Integer> c : countChars.entrySet()) {
            if (c.getValue() == 1) return c.getKey();
        }

        return ' ';
    }


    private static void checkResult(String input, Character expectedResult) {
        Character actualCharacter = getFirstUnique(input);
        if (Objects.equals(expectedResult, actualCharacter)) {
            System.out.println(actualCharacter + " OK");
        } else {
            System.out.println("Check failed! Input '" + input + "'; Result'" + actualCharacter + "'; Expected '" + expectedResult + "'!");
        }
    }
}

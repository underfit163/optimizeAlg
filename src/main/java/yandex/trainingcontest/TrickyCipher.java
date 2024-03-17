package yandex.trainingcontest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/*
Известная компания Тындекс в очередной раз проводит набор стажёров.
Заботясь о персональных данных соискателей, компания придумала хитрый алгоритм шифрования:

Подсчитывается количество различных символов в ФИО (регистр важен, А и а — разные символы).
Берётся сумма цифр в дне и месяце рождения, умноженная на
6
4
.
Для первой (по позиции в слове) буквы фамилии определяется её номер в алфавите (в
1
-индексации), умноженный на
2
5
6
 (регистр буквы не важен).
Полученные числа суммируются.
Результат переводится в
1
6
-чную систему счисления (в верхнем регистре).
У результата сохраняются только
3
 младших разряда (если значимых разрядов меньше, то шифр дополняется до
3
-х разрядов ведущими нулями).
Ваша задача — помочь вычислить для каждого кандидата его шифр.
 */
public class TrickyCipher {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input1.txt"));
             FileWriter fileWriter = new FileWriter("output1.txt")) {
            int count = Integer.parseInt(bufferedReader.readLine());
            String[][] people = new String[count][6];
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < count; i++) {
                people[i] = bufferedReader.readLine().split(",");
                result.append(calcCipher(people[i]));
                if (i != count - 1)
                    result.append(" ");
            }

            fileWriter.write(result.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String calcCipher(String[] man) {
        long countUniq = Arrays.stream(Arrays.copyOf(man, 3))
                .flatMapToInt(CharSequence::chars)
                .distinct()
                .count();

        long sumDigit = 0;
        for (int i = 3; i < 5; i++) {
            int temp = Integer.parseInt(man[i]);
            while (temp > 0) {
                int t = temp % 10;
                sumDigit += t;
                temp /= 10;
            }
        }

        int firstChar = man[0].charAt(0) - 'A' + 1;

        String result = Integer.toHexString((int) (countUniq + sumDigit * 64 + firstChar * 256));

        return result.substring(result.length() - 3).toUpperCase();
    }
}

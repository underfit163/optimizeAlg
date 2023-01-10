package exam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//Задача про мальчика 32/2 = 11, 36/3 = 12, когда то правильно, когда то нет, 37/3 = 12, 47/3 = 12
//Даны 2 числа n и k, нужно посчитать в скольки примерах от 1 до n, при попытке делить на k = [1..9]
//количество ответов мальчика совпадет с правильными
public class BoyTask {
    public static void main(String[] args) {
        int n = 412;
        int k = 2;
        System.out.println("Количество совпадающих ответов: " + calk(n, k));
    }

    public static int calk(int n, int k) {
        int answer;
        int count = 0;
        for (int i = k; i <= n; i++) {
            if (i % k == 0) {
                answer = i / k;
                int boyAnswer = boyAnswer(i, k);
                if (answer == boyAnswer) {
                    System.out.println("Ответы мальчика, которые совпадают: " +  i + "/" + k + "=" + boyAnswer);
                    count++;
                }
            }
        }
        return count;
    }

    public static int boyAnswer(int i, int k) {
        int digit;
        int t = 1;
        int value = 0;
        while (i > 0) {
            digit = (i % 10) / k;
            value += digit * t;

            i /= 10;
            t *= 10;
        }
        return value;
    }
}

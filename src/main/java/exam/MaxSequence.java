package exam;

import java.util.ArrayList;
import java.util.List;

public class MaxSequence {
    //поиска самой длинной возрастающей подпоследовательности данного массива
    public static void findLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        //LIS[i] хранит самую длинную возрастающую подпоследовательность подмассива
        //arr[0…i], оканчивающийся на arr[i]
        List<List<Integer>> LIS = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            LIS.add(new ArrayList<>());
        }

        //LIS[0] обозначает самую длинную возрастающую подпоследовательность, заканчивающуюся на arr[0]
        LIS.get(0).add(arr[0]);

        //начинаем со второго элемента массива
        for (int i = 1; i < arr.length; i++) {
            //делаем для каждого элемента подмассива arr[0…i-1]
            for (int j = 0; j < i; j++) {
                //найти самую длинную возрастающую подпоследовательность, заканчивающуюся на arr[j]
                //где arr[j] меньше, чем текущий элемент arr[i]
                if (arr[j] < arr[i] && LIS.get(j).size() > LIS.get(i).size()) {
                    LIS.set(i, new ArrayList<>(LIS.get(j)));
                }
            }
            //включить arr[i] в LIS[i]
            LIS.get(i).add(arr[i]);
        }
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (LIS.get(j).size() < LIS.get(i).size()) {
                j = i;
            }
        }
        System.out.print(LIS.get(j));
    }

    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        findLIS(arr);
    }
}

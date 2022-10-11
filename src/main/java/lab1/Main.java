package lab1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = new int[10_000_000];
        int[] arr2 = new int[10_000_000];
        Random random = new Random();
        System.out.println("Start");
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt();
        }
        System.out.println("Arr1 has been initialized");
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = random.nextInt();
        }
        System.out.println("Arr2 has been initialized");
        long t1 = System.currentTimeMillis();
        int[] result1 = getNewMas(arr1, arr2);
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1) + ", " + result1.length);
        t1 = System.currentTimeMillis();
        int[] result2 = getNewMasMySet(arr1, arr2);
        t2 = System.currentTimeMillis();
        System.out.println((t2 - t1) + ", " + result2.length);


//        int[] arr2 = {0, 1, 2, 3, 4, 13, 13, 14, 26, 39, 1, 15, 13};
//        int[] arr1 = {1, 2, 3, 45, 6, 7, 8, 8, 9, 9, 5, 2, 5, 5, 6, 7, 8};
//        MySet<Integer> mySet = new MySet<>(arr2.length);
//        for (int i = 0; i < arr2.length; i++) {
//            mySet.addVal(arr2[i]);
//        }
//        for (int i = 0; i < arr2.length; i++) {
//            System.out.print(mySet.getVal(i) + " | ");
//        }
//        System.out.println();
//        for (int i = 0; i < arr2.length; i++) {
//            System.out.print(mySet.getIndex(i) + " | ");
//        }
//        System.out.println();
//        System.out.println(mySet.contains(39));
    }

    //Задание: Меняем реализацию на example.MySet<T> (класс) реализовать максимально эффективно
    public static int[] getNewMas(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length) {
            return getNewMas(arr2, arr1);
        }
        Set<Integer> set1 = new HashSet<>(arr1.length);//Замена на example.MySet

        int[] a = new int[arr2.length];

        for (int j : arr1) {
            set1.add(j);
        }
        int k = 0;
        for (int i : arr2) {
            if (set1.contains(i)) {
                a[k] = i;
                k++;
            }
        }
        return Arrays.copyOf(a, k);
    }

    //Задание: Меняем реализацию на example.MySet<T> (класс) реализовать максимально эффективно
    public static int[] getNewMasMySet(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length) {
            return getNewMasMySet(arr2, arr1);
        }
        MySet<Integer> mySet = new MySet<>(arr1.length);

        int[] a = new int[arr2.length];

        for (int j : arr1) {
            mySet.addVal(j);
        }
        int k = 0;
        for (int i : arr2) {
            if (mySet.contains(i)) {
                a[k] = i;
                k++;
            }
        }
        return Arrays.copyOf(a, k);
    }
}

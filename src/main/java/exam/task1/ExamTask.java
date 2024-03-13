package exam.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExamTask {
    public static void main(String[] args) {
        List<Box> list = new ArrayList<>();
        list.add(new Box(60, 20));
        list.add(new Box(50, 40));
        list.add(new Box(55, 10));
        list.add(new Box(40, 30));
        list.add(new Box(5, 5));
        System.out.println("Ответ: " + task(list));
    }

    public static int task(List<Box> lists) {
        List<Box> boxes = lists.stream().sorted((Comparator.comparingInt(Box::getW))).collect(Collectors.toList());

        //LIS[i] хранит самую длинную возрастающую подпоследовательность подмассива
        //arr[0…i], оканчивающийся на arr[i]
        List<List<Box>> LIS = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            LIS.add(new ArrayList<>());
        }

        //LIS[0] обозначает самую длинную возрастающую подпоследовательность, заканчивающуюся на arr[0]
        LIS.get(0).add(boxes.get(0));

        //начинаем со второго элемента массива
        for (int i = 1; i < boxes.size(); i++) {
            //делаем для каждого элемента подмассива arr[0…i-1]
            for (int j = 0; j < i; j++) {
                //найти самую длинную возрастающую подпоследовательность, заканчивающуюся на arr[j]
                //где arr[j] меньше, чем текущий элемент arr[i]
                if (boxes.get(j).getH() < boxes.get(i).getH() && LIS.get(j).size() > LIS.get(i).size()) {
                    LIS.set(i, new ArrayList<>(LIS.get(j)));
                }
            }
            //включить arr[i] в LIS[i]
            LIS.get(i).add(boxes.get(i));
        }
        int j = 0;
        for (int i = 0; i < boxes.size(); i++) {
            if (LIS.get(j).size() < LIS.get(i).size()) {
                j = i;
            }
        }
        System.out.print(LIS.get(j));
        return LIS.get(j).size();
    }
}

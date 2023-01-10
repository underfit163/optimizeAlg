package exam;

public class QuickSort {
    public void quickSort(int[] arr, int start, int end) {
        if (arr.length == 0) {
            return;//завершить выполнение, если длина массива равна 0
        }
        if (start >= end) {
            return;//завершить выполнение если уже нечего делить
        }

        // выбрать опорный элемент
        int mid = start + (end - start) / 2;
        int midEl = arr[mid];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = start;
        int j = end;
        while (i <= j) {
            while (arr[i] < midEl) {
                i++;
            }
            while (arr[j] > midEl) {
                j--;
            }
            if(i <= j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (start < j)
            quickSort(arr, start, j);
        if (i < end)
            quickSort(arr, i, end);
    }
}

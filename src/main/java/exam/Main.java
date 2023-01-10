package exam;

import java.util.Arrays;

import static exam.BalanceTree.isBalanced;

public class Main {
    public static void main(String[] args) {
//        Task task = new Task();
//        task.reshuffleString("abc");

//        int[] arr = {4, 2, 7, 1, -4, 2, 9};
//        QuickSort quickSort = new QuickSort();
//        quickSort.quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = {4, 2, 7, 1, 4, 2, 9};
//        PackNumbers packNumbers = new PackNumbers();
//        System.out.println("Упакованные числа: " + packNumbers.pack(arr));

        Node root = new Node(10);
        root.setLeft(new Node(5));
        root.setRight(new Node(30));
        root.getRight().setLeft(new Node(15));
        root.getRight().setRight(new Node(20));

        if (isBalanced(root) > 0)
            System.out.println("Balanced");
        else
            System.out.println("Not Balanced");
    }
}

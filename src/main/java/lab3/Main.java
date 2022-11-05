package lab3;

import java.util.*;

/*9. Бинарное дерево поиска было создано обходом массива слева направо и вставкой каждого элемента.
Для заданного бинарного дерева поиска с разными элементами выведите все возможные массивы,
которые могли привести к созданию этого дерева.
        Пример:
        Ввод:
        Корень 2, левый подузел 1, правый подузел 3
        Вывод: { 2, 1, 3 }, { 2, 3, 1 }*/
public class Main {

    private static int count;
    private static List<List<Integer>> arrs;
    public static void main(String[] args) {
        arrs = new ArrayList<>();
        BinaryTree bt = new BinaryTree();

        bt.addNode(6);
        bt.addNode(4);
        bt.addNode(8);
        bt.addNode(3);
        bt.addNode(3);
        bt.addNode(5);
        bt.addNode(7);
        bt.addNode(9);
        bt.addNode(2);
        bt.addNode(10);

        Map<Integer, List<Integer>> map = new HashMap<>();
        bt.preOrderTraversal(bt.getRoot(), 0, map);

        map.forEach((x, y) -> System.out.println(x + ": " + Arrays.toString(y.toArray())));
        System.out.println("Массивы, которые могли привести к созданию этого дерева: ");
        recurseAddVals(new ArrayList<>(), 0, map);
    }



    public static void recurseAddVals(List<Integer> arr, int i, Map<Integer, List<Integer>> map) {
        if (i == map.size()) {
            List<Integer> arrCopy = new ArrayList<>(arr);
            arrs.add(arrCopy);
            count++;
            System.out.println(count + " " + Arrays.toString(arr.toArray()));

            return;
        }
        List<List<Integer>> list = getPermutation(map.get(i));
        for (List<Integer> integers : list) {
            arr.addAll(integers);
            recurseAddVals(arr, i + 1, map);
            arr.removeAll(integers);
        }
    }

    static List<List<Integer>> getPermutation(List<Integer> ints) {
        if (ints.size() == 1) {
            List<List<Integer>> list = new ArrayList<>();
            list.add(ints);
            return list;
        } else {
            List<List<Integer>> list = new ArrayList<>();
            for (Integer i : ints) {
                List<Integer> subList = new ArrayList<>(ints);
                subList.remove(i);
                List<List<Integer>> subListNew = getPermutation(subList);
                for (List<Integer> _list : subListNew) {
                    ArrayList<Integer> local = new ArrayList<>();
                    local.add(i);
                    local.addAll(_list);
                    list.add(local);
                }
            }
            return list;
        }
    }
}

package java_year_2025;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println(searchNearNumber(new int[]{2, 5, 6, 7, 8, 9}, 4));
        System.out.println(Main.getFirst(List.of(1, 2, 3)));

        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        List<? extends Number> nums = ints;
        //nums.add(3);
        List<? extends Double> numbers = List.of(1.4);
        System.out.println(numbers instanceof List<? extends Number>);

        System.out.println("-----------START----------");

        int[][] picture1 = new int[][]{
                {2, 2, 2, 1},
                {1, 2, 2, 1},
                {1, 3, 1, 1},
                {1, 1, 1, 3}
        };

        System.out.println(strokesRequired(picture1)); // expected 4

        int[][] picture2 = new int[][]{
                {1, 1, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 1, 1, 3, 1}
        };

        System.out.println(strokesRequired(picture2)); // expected 5
        System.out.println("-----------FINISH----------");

        //Выведите первое не повторяющиеся имя с минимальным значением
        Map<String, Integer> map = Map.of("Катя", 3, "Аня", 4, "Даша", 1);
        System.out.println(getNameWithMinValue(map));

        System.out.println(getFirstUniqueValue(new int[]{9, 4, 9, 9, 6, 7, 4, 5}));
        // select name, count(pay.person_id) from persons p left join payments pay on p.id = pay.person_id
        // group by p.id
        // having count(*) > 25

        // select user_group, sum(salary) from users
        // where user_id > 100
        // group by user_group
        // having sum(salary) > 2000

        System.out.println(findDuplicateIndex(-1, -2, 0, 0, -7, -2));
        System.out.println(findDuplicateIndexForObjects(-1, -2, -2, -4, -7, -2));
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("yuLia");
        list.add(" ");
        list.add("borjA");
        list.add("Ceba");
        list.add("");
        list.add("aleNa");
        list.add("daRek");
        list.add("FranK");
        list.add(null);
        list.add("yulia");
        System.out.println(transform(list));

        List<Node> treeList = List.of(new Node(1L, null),
                new Node(2L, 1L),
                new Node(3L, 1L));

        Map<Long, List<Node>> parent = treeList.stream().filter(el -> el.parentId != null)
                .collect(Collectors.groupingBy(n -> n.parentId));

        treeList.forEach(el -> el.children = parent.getOrDefault(el.id, new ArrayList<>()));
        System.out.println(treeList);
        System.out.println(execute(new int[] {6, 3, 1, 6, 5, 1, 1}));

        Map<Character, Long> countChars = "rrrre".chars().boxed()
                .collect(Collectors.groupingBy(n -> (char) (int) n, Collectors.counting()));
        System.out.println(countChars);

        Integer[] array = new Integer[] {1, 2, 3, 2, 1, 1, 5};
        System.out.println(Arrays.stream(array).collect(Collectors.toSet()));

        MaxStack<Integer> maxStack = new MaxStack<>();
        maxStack.push(1);
        maxStack.push(6);
        maxStack.push(5);
        maxStack.push(7);
        maxStack.push(2);
        System.out.println(maxStack.max());
        maxStack.pop();
        maxStack.pop();
        System.out.println(maxStack.max());
        System.out.println(maxStack.pop());
        System.out.println(maxStack.max());
        maxStack.pop();
        System.out.println(maxStack.max());
        maxStack.pop();
        //maxStack.pop();
        System.out.println(maxStack.max());

        List<CompletableFuture> futures = new ArrayList<>();
        futures.add(new CompletableFuture<>());
        futures.add(new CompletableFuture<>());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
    }

    protected static int execute(int[] nums) {
        return Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream().max(Map.Entry.comparingByValue())
                .orElseThrow(()-> new IllegalArgumentException("No elements")).getKey();
    }

    protected static class Node {
        Long id; // заполнен
        Long parentId; // заполнен если есть parent
        List<Node> children; // пустой

        public Node(Long id, Long parentId) {
            this.id = id;
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return "Node{" + "id=" + id +
                   ", parentId=" + parentId +
                   ", children=" + children +
                   '}';
        }
    }

    // todo: заполнить children у всех элементов в листе


    protected static String transform(List<String> strings) {
        return strings.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isBlank())
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .distinct()
                .sorted()
                .collect(Collectors.joining(" - "));
    }

    protected static int findDuplicateIndex(int... numbers) {
        if (numbers == null || numbers.length == 0) return -1;

        int max = Arrays.stream(numbers).max().getAsInt();
        int min = Arrays.stream(numbers).min().getAsInt();

        int[] countArray = new int[max - min + 1];
        for (int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            if (countArray[current + Math.abs(min)] > 0) {
                return i;
            } else {
                countArray[current + Math.abs(min)] += 1;
            }
        }
        throw new RuntimeException("Duplicate not found!");
    }

    public static <T> Map<T, List<Integer>> findDuplicateIndexForObjects(T... objects) {
        if (objects == null || objects.length == 0) return Map.of();

        Map<T, List<Integer>> doubles = new HashMap<>();
        for (int i = 0; i < objects.length; i++) {
            List<Integer> indexes = doubles.getOrDefault(objects[i], new ArrayList<>());
            indexes.add(i);
            doubles.put(objects[i], indexes);
        }

        return doubles.entrySet().stream()
                .filter(el -> el.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //Найти первый не повторяющийся элемент в массиве целых чисел
    protected static int getFirstUniqueValue(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        Map<Integer, Integer> countElMap = new LinkedHashMap<>();

        for (int j : arr) {
            countElMap.put(j, countElMap.getOrDefault(j, 0) + 1);
        }

        for (var el : countElMap.entrySet()) {
            if (el.getValue() == 1) return el.getKey();
        }
        return -1;
    }

    private static String getNameWithMinValue(Map<String, Integer> map) {
        return map.entrySet().stream().min(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElseThrow();
    }

    private static int strokesRequired(int[][] arr) {
        if (arr == null || arr.length == 0) return 0;

        Queue<int[]> pI = new LinkedList<>();

        List<int[]> directions = List.of(new int[]{0, 1}, new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, -1});
        int m = arr.length;
        int n = arr[0].length;

        int strokesCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != -1) {
                    pI.offer(new int[]{i, j});
                    while (!pI.isEmpty()) {
                        int[] curr = pI.poll();
                        int x = curr[0];
                        int y = curr[1];

                        for (int[] d : directions) {
                            int nx = x + d[0];
                            int ny = y + d[1];

                            if (nx < 0 || ny < 0 || nx >= m || ny >= n || arr[nx][ny] < 0 || arr[nx][ny] != arr[x][y])
                                continue;

                            pI.offer(new int[]{nx, ny});
                        }
                        arr[x][y] = -1;
                    }
                    strokesCount++;
                }
            }
        }

        return strokesCount;
    }

    protected static int searchNearNumber(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int minDiffPosition = 0;
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            int varMinDiff = Math.abs(arr[i] - target);
            if (varMinDiff == 0) return arr[i];

            if (varMinDiff < minDiff) {
                minDiffPosition = i;
                minDiff = varMinDiff;
            }
        }

        return arr[minDiffPosition];
    }

//    public static <T> T getFirst(List<? super T> list) {
//        return list.get(0);
//    }

    public static <T extends Object /*& Collection<? super T>*/> T getFirst(List<? extends T> list) {
        return list.get(0);
    }

    public static <T> void putLast(List<? super T> list, T t) {
        list.add(t);
    }

//    public static <T> void putLast(List<? extends T> list, T t) {
//        list.add(t);
//    }
}

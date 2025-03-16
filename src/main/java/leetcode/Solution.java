package leetcode;

import org.apache.commons.math3.util.Pair;

import java.math.BigInteger;
import java.util.*;
import java.util.List;
import java.util.function.Function;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.canConstruct("fihjjjjei", "hjibagacbhadfaefdjaeaebgi"));
        //System.out.println(Arrays.toString(solution.sortedSquares(new int[]{-4, -1, 0, 3, 10})));
        //solution.merge(new int[] {1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
        //solution.merge(new int[] {}, 0, new int[]{1}, 1);
        //System.out.println(solution.removeDuplicates(new int[]{1,1}));
        //System.out.println(solution.checkIfExist(new int[]{10, 2, 5, 3}));
        //System.out.println(solution.validMountainArray(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        //System.out.println(solution.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        //System.out.println(solution.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
        //System.out.println(Arrays.toString(solution.plusOne(new int[]{9})));
        //System.out.println(solution.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
        //System.out.println(solution.generate(5));
        //System.out.println(solution.longestCommonPrefix(new String[]{"a"}));
        //System.out.println(solution.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
        //System.out.println(solution.findMinArrowShots(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}));
        //System.out.println(solution.findDuplicate(new int[]{3, 2, 3, 1}));
        //System.out.println(solution.isIsomorphic("abc", "egg"));
        //System.out.println(solution.minRemoveToMakeValid("a)b(c)d"));
        //System.out.println(solution.getRow(3));
        //System.out.println(solution.reverseWordsV2("the sky is blue"));
        //System.out.println(solution.minMoves2(new int[] {1,2,3}));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        //root.right.left = new TreeNode(8);
        //root.right.right = new TreeNode(6);
        //root.left.left.left = new TreeNode(7);
        //root.left.left.left.right = new TreeNode(5);
        //System.out.println(solution.levelOrder(root));
        //System.out.println(solution.preorderTraversal(root));
        //System.out.println(solution.hasPathSum(root, 8));

        //int[] inorder = {9, 3, 15, 20, 7};
        //int[] postorder = {9, 15, 7, 20, 3};

        //TreeNode root1 = solution.buildTree(inorder, postorder);
        //System.out.println(root1);

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);
        //[1,2,3,null,null,4,5]

        //String stringTree = solution.serialize(root1);
        //System.out.println(stringTree);
        //TreeNode root2 = solution.deserialize(stringTree);
        //TreeNode root2 = solution.deserialize(solution.serialize(root1));
        //System.out.println(solution.serialize(root2));
        //System.out.println(solution.isValid("()"));
        //System.out.println(Arrays.toString(solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));

        //System.out.println(solution.getRowRecursive(3));
        //Function<Integer, Integer> function = solution.memoize(solution::fibonacci);
        //System.out.println(function.apply(6));
        //System.out.println(solution.generateTrees(3))
        //System.out.println(Arrays.toString(solution.sortArray(new int[]{5, 1, 1, 2, 0, 0})));
        //System.out.println(solution.generateParenthesisIterative(3));
        //System.out.println(solution.largestRectangleAreaStack(new int[] {2,1,5,6,2,3}));
        //System.out.println(solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        //System.out.println(solution.permute(new int[]{1, 2, 3}));
        //System.out.println(solution.letterCombinations(""));
        System.out.println(solution.getSkyline(new int[][]{
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8}
        }));
    }

    public List<List<Integer>> getSkylineDivideAndConquer(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return new ArrayList<>();
        }
        return mergeSkylines(buildings, 0, buildings.length - 1);
    }

    // Рекурсивное разбиение массива зданий
    private List<List<Integer>> mergeSkylines(int[][] buildings, int left, int right) {
        List<List<Integer>> skyline = new ArrayList<>();
        // Базовый случай: одно здание
        if (left == right) {
            skyline.add(List.of(buildings[left][0], buildings[left][2]));
            skyline.add(List.of(buildings[left][1], 0));
            return skyline;
        }

        int mid = left + (right - left) / 2;
        List<List<Integer>> leftSkyline = mergeSkylines(buildings, left, mid);
        List<List<Integer>> rightSkyline = mergeSkylines(buildings, mid + 1, right);
        return merge(leftSkyline, rightSkyline);
    }

    // Функция слияния двух силуэтов
    private List<List<Integer>> merge(List<List<Integer>> leftSkyline, List<List<Integer>> rightSkyline) {
        List<List<Integer>> mergedSkyline = new ArrayList<>();
        int i = 0, j = 0;
        int currentH = 0, leftH = 0, rightH = 0;

        while (i < leftSkyline.size() && j < rightSkyline.size()) {
            int x;

            // Выбираем меньшую x-координату
            if (leftSkyline.get(i).get(0) < rightSkyline.get(j).get(0)) {
                x = leftSkyline.get(i).get(0);
                leftH = leftSkyline.get(i).get(1);
                i++;
            } else if (rightSkyline.get(j).get(0) < leftSkyline.get(i).get(0)) {
                x = rightSkyline.get(j).get(0);
                rightH = rightSkyline.get(j).get(1);
                j++;
            } else {
                // Если координаты равны, обновляем обе высоты
                x = leftSkyline.get(i).get(0);
                leftH = leftSkyline.get(i).get(1);
                rightH = rightSkyline.get(j).get(1);
                i++;
                j++;
            }
            // Текущая высота – максимум из двух силуэтов
            int maxH = Math.max(leftH, rightH);

            // Добавляем точку, если высота изменилась
            if (mergedSkyline.isEmpty() || currentH != maxH) {
                mergedSkyline.add(List.of(x, maxH));
                currentH = maxH;
            }
        }

        while (i < leftSkyline.size()) {
            mergedSkyline.add(leftSkyline.get(i++));
        }

        while (j < rightSkyline.size()) {
            mergedSkyline.add(rightSkyline.get(j++));
        }
        return mergedSkyline;
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        final List<List<Integer>> result = new ArrayList<>();

        List<int[]> events = new ArrayList<>();

        for (int[] building : buildings) {
            events.add(new int[]{building[0], -building[2]});
            events.add(new int[]{building[1], building[2]});
        }

        events.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // TreeMap для хранения активных высот.
        // Ключ – высота, значение – количество таких высот.
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();

        // Инициализируем базовой высотой 0.
        heightMap.put(0, 1);

        int prevMax = 0;

        for (int[] event : events) {
            int x = event[0];
            int h = event[1];

            if (h < 0) {
                int height = -h;
                heightMap.put(height, heightMap.getOrDefault(height, 0) + 1);
            } else {
                int count = heightMap.get(h);
                if (count == 1) {
                    heightMap.remove(h);
                } else {
                    heightMap.put(h, count - 1);
                }
            }
            // Текущий максимум – последняя (наибольшая) высота в TreeMap.
            int currentMax = heightMap.lastKey();

            // Если максимум изменился, значит, это ключевая точка силуэта.
            if (currentMax != prevMax) {
                result.add(Arrays.asList(x, currentMax));
                prevMax = currentMax;
            }
        }
        return result;
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return new ArrayList<>();
        // создаем массив букв
        Map<Character, List<Character>> charsForDigits = new HashMap<>();
        charsForDigits.put('2', List.of('a', 'b', 'c'));
        charsForDigits.put('3', List.of('d', 'e', 'f'));
        charsForDigits.put('4', List.of('g', 'h', 'i'));
        charsForDigits.put('5', List.of('j', 'k', 'l'));
        charsForDigits.put('6', List.of('m', 'n', 'o'));
        charsForDigits.put('7', List.of('p', 'q', 'r', 's'));
        charsForDigits.put('8', List.of('t', 'u', 'v'));
        charsForDigits.put('9', List.of('w', 'x', 'y', 'z'));

        List<String> result = new ArrayList<>();
        backtrackingLetterCombinations(result, charsForDigits, digits, new StringBuilder(), 0);
        return result;
    }

    private void backtrackingLetterCombinations(List<String> result, Map<Character, List<Character>> charsForDigits, String digits, StringBuilder preResult, int start) {
        if (preResult.length() == digits.length()) {
            result.add(preResult.toString());
            return;
        }

        for (var c : charsForDigits.get(digits.charAt(start))) {
            preResult.append(c);
            backtrackingLetterCombinations(result, charsForDigits, digits, preResult, start + 1);
            preResult.deleteCharAt(preResult.length() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackingPermute(result, new ArrayList<>(), nums, used);
        return result;
    }

    private void backtrackingPermute(List<List<Integer>> result, List<Integer> combine, int[] nums, boolean[] used) {
        if (combine.size() == nums.length) {
            result.add(new ArrayList<>(combine));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            combine.add(nums[i]);
            used[i] = true;
            backtrackingPermute(result, combine, nums, used);
            used[i] = false;
            combine.remove(combine.size() - 1);
        }
    }

    public int largestRectangleArea(int[] heights) {
        return helpLargestRectangleArea(heights, 0, heights.length - 1);
    }

    private int helpLargestRectangleArea(int[] heights, int start, int end) {
        if (start > end) return 0;

        int minI = start;
        for (int i = start; i <= end; i++) {
            if (heights[minI] > heights[i]) minI = i;
        }
        int currentMaxArea = heights[minI] * (end - start + 1);

        int leftMaxArea = helpLargestRectangleArea(heights, start, minI - 1);
        int rightMaxArea = helpLargestRectangleArea(heights, minI + 1, end);

        return Math.max(currentMaxArea, Math.max(leftMaxArea, rightMaxArea));
    }


    public int largestRectangleAreaStack(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        // Проходим по всем столбцам, добавляя фиктивный столбец с высотой 0 в конце
        for (int i = 0; i <= n; i++) {
            // Для последнего прохода устанавливаем h = 0, чтобы очистить стек
            int h = (i == n ? 0 : heights[i]);

            // Если текущая высота меньше, чем у столбца на вершине стека,
            // то вычисляем площади для столбцов, которые больше текущей высоты.
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                // Если стек пуст, значит столбец с данной высотой был самым маленьким до i,
                // и ширина прямоугольника равна i
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    private static class State {
        String current; // Текущая строка
        int open;       // Количество открытых скобок
        int close;      // Количество закрытых скобок

        State(String current, int open, int close) {
            this.current = current;
            this.open = open;
            this.close = close;
        }
    }

    public List<String> generateParenthesisIterative(int n) {
        List<String> result = new ArrayList<>();
        Stack<State> stack = new Stack<>();

        // Инициализируем стек с начальным состоянием
        stack.push(new State("", 0, 0));

        while (!stack.isEmpty()) {
            State state = stack.pop();

            // Если текущая строка имеет длину 2 * n, добавляем её в результат
            if (state.current.length() == n * 2) {
                result.add(state.current);
                continue;
            }

            // Если можем добавить закрывающую скобку
            if (state.close < state.open) {
                stack.push(new State(state.current + ")", state.open, state.close + 1));
            }

            // Если можем добавить открывающую скобку
            if (state.open < n) {
                stack.push(new State(state.current + "(", state.open + 1, state.close));
            }
        }

        return result;
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        helpGenerateParenthesis(result, 0, 0, n, new StringBuilder());
        return result;
    }

    private void helpGenerateParenthesis(List<String> result, int open, int close, int n, StringBuilder stringBuilder) {
        if (stringBuilder.length() == n * 2) {
            result.add(stringBuilder.toString());
            return;
        }
        // учитываем что скобок должно быть поровну
        if (open < n) {
            stringBuilder.append("(");
            helpGenerateParenthesis(result, open + 1, close, n, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (open > close) {
            stringBuilder.append(")");
            helpGenerateParenthesis(result, open, close + 1, n, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (checkTreeNodes(p, q)) return false;

        Queue<TreeNode> pQueue = new LinkedList<>();
        Queue<TreeNode> qQueue = new LinkedList<>();

        pQueue.offer(p);
        qQueue.offer(q);

        while (!pQueue.isEmpty()) {
            TreeNode pEl = pQueue.poll();
            TreeNode qEl = qQueue.poll();

            if (checkTreeNodes(p, q)) return false;

            if (pEl != null && qEl != null) {
                if (checkTreeNodes(pEl.left, qEl.left)) return false;
                if (pEl.left != null) {
                    pQueue.offer(pEl.left);
                    qQueue.offer(qEl.left);
                }

                if (checkTreeNodes(pEl.right, qEl.right)) return false;
                if (pEl.right != null) {
                    pQueue.offer(pEl.right);
                    qQueue.offer(qEl.right);
                }
            }
        }

        return true;
    }

    private boolean checkTreeNodes(TreeNode p, TreeNode q) {
        if (p == null && q == null) return false;
        if (p == null || q == null) return true;

        return p.val != q.val;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackingCombine(res, new ArrayList<>(), 1, n, k);
        return res;
    }

    private void backtrackingCombine(List<List<Integer>> res, ArrayList<Integer> current, int start, int n, int k) {
        if (current.size() == k) {
            res.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            backtrackingCombine(res, current, i + 1, n, k);
            current.remove(current.size() - 1);
        }
    }


    public void solveSudoku(char[][] board) {
        helpSolveSudoku(board, 0, 0);
    }

    private boolean helpSolveSudoku(char[][] board, int row, int col) {
        // Условие завершения: дошли до конца доски
        if (row == board.length) {
            return true;
        }

        // Переход к следующей строке/ячейке
        int nextRow = (col == (board.length - 1)) ? row + 1 : row;
        int nextCol = (col + 1) % board[0].length;

        // Если ячейка уже заполнена, перейти к следующей
        if (board[row][col] != '.') {
            return helpSolveSudoku(board, nextRow, nextCol);
        }

        // Пробуем значения от '1' до '9'
        for (char val = '1'; val <= '9'; val++) {
            if (isValueOnBoardValid(board, row, col, val)) {
                board[row][col] = val; // Устанавливаем значение
                if (helpSolveSudoku(board, nextRow, nextCol)) {
                    return true; // Если решение найдено, возвращаемся
                }
                board[row][col] = '.'; // Откат (backtracking)
            }
        }

        return false; // Нет решения для текущей конфигурации
    }

    private boolean isValueOnBoardValid(char[][] board, int row, int col, char val) {
        // Проверка строки
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }

        // Проверка столбца
        for (int i = 0; i < board[0].length; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }

        // Проверка 3x3 квадрата
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == val) {
                    return false;
                }
            }
        }

        return true;
    }

    private int totalNQueens(int n) {
        int[] queensCol = new int[n];
        return backtrackTotalQueens(queensCol, 0);
    }

    private int backtrackTotalQueens(int[] queens, int row) {
        if (queens.length == row) {
            return 1;
        }

        int count = 0;
        for (int col = 0; col < queens.length; col++) {
            if (isValid(queens, row, col)) {
                queens[row] = col;
                count += backtrackTotalQueens(queens, row + 1);
            }
        }
        return count;
    }

    private boolean isValid(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col ||
                queens[i] - i == col - row ||
                queens[i] + i == row + col) {
                return false;
            }
        }
        return true;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        return helpSearchMatrix(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    private boolean helpSearchMatrix(int[][] matrix, int target, int startI, int endI, int startJ, int endJ) {
        if (startI > endI || startJ > endJ) return false;

        int midJ = startJ + (endJ - startJ) / 2;

        int bounderI = startI;
        while (bounderI <= endI && matrix[bounderI][midJ] <= target) {
            if (matrix[bounderI][midJ] == target) return true;
            bounderI++;
        }
        // Разделяем на подматрицы и ищем в верхней правой и нижней левой подматрицах
        return helpSearchMatrix(matrix, target, startI, bounderI - 1, midJ + 1, endJ) || // Верхняя правая подматрица
               helpSearchMatrix(matrix, target, bounderI, endI, startJ, midJ - 1); // Нижняя левая подматрица
    }

    protected boolean isValidBST(TreeNode root) {
        return helpIsValidBST(root, null, null);
    }

    protected boolean helpIsValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        if (min != null && root.val <= min || max != null && root.val >= max) return false;

        return helpIsValidBST(root.left, min, root.val) && helpIsValidBST(root.right, root.val, max);
    }


    protected int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int[] aux = new int[nums.length];
        for (int size = 1; size < nums.length; size *= 2) {
            for (int leftStart = 0; leftStart < nums.length; leftStart += 2 * size) {
                int mid = Math.min(leftStart + size, nums.length);
                int rightEnd = Math.min(leftStart + 2 * size, nums.length);
                merge(nums, aux, leftStart, mid, rightEnd);
            }
        }
        return nums;
    }

    private void merge(int[] nums, int[] aux, int leftStart, int mid, int rightEnd) {
        System.arraycopy(nums, leftStart, aux, leftStart, rightEnd - leftStart);

        int i = leftStart, j = mid, k = leftStart;
        while (i < mid && j < rightEnd) {
            if (aux[i] <= aux[j]) {
                nums[k++] = aux[i++];
            } else {
                nums[k++] = aux[j++];
            }
        }

        while (i < mid) {
            nums[k++] = aux[i++];
        }

        while (j < rightEnd) {
            nums[k++] = aux[j++];
        }
    }

    protected int[] sortArrayMyVersion(int[] nums) {
        List<List<Integer>> divs = new ArrayList<>();

        for (int num : nums) {
            List<Integer> partDivs = new ArrayList<>();
            partDivs.add(num);
            divs.add(partDivs);
        }

        while (divs.size() > 1) {
            for (int i = divs.size() - 1; i > 0; i--) {
                List<Integer> part1 = divs.remove(i);
                List<Integer> part2 = divs.remove(i - 1);

                List<Integer> newPart = new ArrayList<>();
                int left = 0;
                int right = 0;

                while (left < part1.size() && right < part2.size()) {
                    if (part1.get(left) <= part2.get(right)) {
                        newPart.add(part1.get(left));
                        left++;
                    } else {
                        newPart.add(part2.get(right));
                        right++;
                    }
                }

                while (left < part1.size()) {
                    newPart.add(part1.get(left));
                    left++;
                }

                while (right < part2.size()) {
                    newPart.add(part2.get(right));
                    right++;
                }
                divs.add(newPart);
            }
        }

        return divs.get(0).stream().mapToInt(Integer::intValue).toArray();
    }

    protected boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> littersMap = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            littersMap.put(magazine.charAt(i), littersMap.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            Character litter = ransomNote.charAt(i);
            int currentCount = littersMap.getOrDefault(litter, 0);
            if (currentCount == 0) {
                return false;
            }
            littersMap.put(litter, currentCount - 1);
        }
        return true;
    }

    protected int findMaxConsecutiveOnes(int[] nums) {
        int count1 = 0;
        int countMax1 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count1++;
            } else {
                count1 = 0;
            }
            countMax1 = Math.max(count1, countMax1);
        }
        return countMax1;
    }

    protected int findNumbers(int[] nums) {
        int countEven = 0;
        for (int num : nums) {
            int count = (int) Math.log10(num) + 1;
            if (count % 2 == 0) countEven++;
        }
        return countEven;
    }

    protected int[] sortedSquares(int[] nums) {
        int negativeI = 0;
        int positiveI = nums.length - 1;
        int[] copyNums = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (Math.abs(nums[positiveI]) <= Math.abs(nums[negativeI])) {
                copyNums[i] = (int) Math.pow(nums[negativeI], 2);
                negativeI++;
            } else {
                copyNums[i] = (int) Math.pow(nums[positiveI], 2);
                positiveI--;
            }
        }
        return copyNums;
    }

    protected void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0) {
                System.arraycopy(arr, i, arr, i + 1, arr.length - 1 - i);
                i++;
            }
        }
    }

    protected void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = nums1.length - 1;
        if (m == 0) {
            nums1 = Arrays.copyOf(nums2, n);
        } else {
            while (j >= 0 && i >= 0) {
                if (nums1[i] > nums2[j]) {
                    nums1[k] = nums1[i];
                    i--;
                } else {
                    nums1[k] = nums2[j];
                    j--;
                }
                k--;
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    protected int removeElement(int[] nums, int val) {
        int endI = nums.length;
        int i = 0;

        while (i < endI) {
            if (nums[i] == val) {
                nums[i] = nums[endI - 1];
                endI--;
            } else i++;
        }
        return endI;
    }

    protected int removeDuplicates(int[] nums) {
        int i = 1;
        int p = 1;
        int end = nums.length;
        while (i < end) {
            if (nums[i - 1] != nums[i]) {
                nums[p] = nums[i];
                p++;
            }
            i++;
        }
        return p;
    }

    protected boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int j : arr) {
            if (j % 2 == 0 && set.contains(j / 2)) return true;
            if (set.contains(j * 2)) return true;
            set.add(j);
        }
        return false;
    }

    protected boolean validMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            if (arr[l] < arr[l + 1]) {
                l++;
            } else if (arr[r - 1] > arr[r]) {
                r--;
            } else {
                break;
            }
        }
        return l != 0 && r != arr.length - 1 && arr[l] == arr[r];
    }

    protected int heightChecker(int[] heights) {
        int[] copyArr = heights.clone();
        Arrays.sort(heights);
        int countOtherIndex = 0;
        for (int i = 0; i < heights.length; i++) {
            if (copyArr[i] != heights[i]) {
                countOtherIndex++;
            }
        }
        return countOtherIndex;
    }

    protected int thirdMax(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2 && num < max1) {
                max3 = max2;
                max2 = num;
            } else if (num > max3 && num < max2) {
                max3 = num;
            }
        }
        return max3 == Integer.MIN_VALUE ? max1 : max3;
    }

    protected List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int pos = nums[i] - 1;
            if (nums[i] != nums[pos]) {
                int temp = nums[i];
                nums[i] = nums[pos];
                nums[pos] = temp;
                i--;
            }
        }
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) lst.add(i + 1);
        }
        return lst;
    }


    protected int pivotIndex(int[] nums) {
        int leftSum = 0;
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                leftSum += nums[i - 1];
            }
            if (leftSum == totalSum - (leftSum + nums[i])) {
                return i;
            }
        }
        return -1;
    }

    protected int[] plusOne(int[] digits) {
        int i = digits.length - 1;

        while (i >= 0) {
            if (digits[i] + 1 > 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                break;
            }
            i--;
        }
        if (digits[0] == 0) {
            digits = Arrays.copyOf(digits, digits.length + 1);
            System.arraycopy(digits, 0, digits, 1, digits.length - 1);
            digits[0] = 1;
        }
        return digits;
    }

    protected int[] findDiagonalOrderV1(int[][] mat) {
        int i = 0;
        int j = 0;
        int m = mat.length;
        int n = mat[0].length;
        int[] arr = new int[m * n];
        int t = 0;
        boolean direction = true;


        while (i < m && j < n) {
            arr[t++] = mat[i][j];

            int row = i + (direction ? -1 : 1);
            int col = j + (direction ? 1 : -1);

            if (row < 0 || row >= m || col < 0 || col >= n) {
                if (direction) {
                    if (j >= n - 1) {
                        i++;
                    } else {
                        j++;
                    }
                } else {
                    if (i >= m - 1) {
                        j++;
                    } else {
                        i++;
                    }
                }
                direction = !direction;
            } else {
                i = row;
                j = col;
            }
        }
        return arr;
    }

    protected int[] findDiagonalOrderV2(int[][] mat) {
        int i = 0;
        int j = 0;
        int m = mat.length;
        int n = mat[0].length;
        int[] arr = new int[m * n];
        int t = 0;
        boolean direction = true;


        while (i < m && j < n) {
            arr[t++] = mat[i][j];

            int row = i + (direction ? -1 : 1);
            int col = j + (direction ? 1 : -1);

            if (row < 0 || row >= m || col < 0 || col >= n) {
                if (direction) {
                    if (j >= n - 1) {
                        i++;
                    } else {
                        j++;
                    }
                } else {
                    if (i >= m - 1) {
                        j++;
                    } else {
                        i++;
                    }
                }
                direction = !direction;
            } else {
                i = row;
                j = col;
            }
        }
        return arr;
    }

    protected int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> resultSet = new HashSet<>();
        Set<Integer> arrSet = new HashSet<>();
        for (int el : nums1) {
            arrSet.add(el);
        }
        for (int el : nums2) {
            if (arrSet.contains(el)) {
                resultSet.add(el);
            }
        }
        int[] result = new int[resultSet.size()];
        int i = 0;
        for (int el : resultSet) {
            result[i++] = el;
        }
        return result;
    }


    protected List<Integer> spiralOrderV1(int[][] matrix) {
        List<Integer> arr = new ArrayList<>();

        int a = 0, b = 1, c = 2;

        int m = matrix.length;
        int n = matrix[0].length;

        int matrSize = m * n;

        while (arr.size() < matrSize) {
            for (int j = a; arr.size() < matrSize && j < n - a; j++)//вправо
                arr.add(matrix[a][j]);

            for (int i = b; arr.size() < matrSize && i < m - a; i++)//вниз
                arr.add(matrix[i][n - b]);

            for (int j = n - c; arr.size() < matrSize && j >= a; j--)//влево
                arr.add(matrix[m - b][j]);

            for (int i = m - c; arr.size() < matrSize && i > a; i--)//вверх
                arr.add(matrix[i][a]);

            a++;
            b++;
            c++;
        }
        return arr;
    }


    protected List<Integer> spiralOrderV2(int[][] matrix) {
        List<Integer> arr = new ArrayList<>();

        int upLimit = 1;
        int downLimit = 1;
        int lLimit = 1;
        int rLimit = 1;

        int m = matrix.length;
        int n = matrix[0].length;

        while (arr.size() < m * n) {
            for (int j = lLimit - 1; arr.size() < m * n && j < n - (rLimit - 1); j++)//вправо
                arr.add(matrix[upLimit - 1][j]);

            for (int i = upLimit; arr.size() < m * n && i < m - downLimit; i++)//вниз
                arr.add(matrix[i][n - rLimit]);

            for (int j = n - rLimit; arr.size() < m * n && j >= lLimit; j--)//влево
                arr.add(matrix[m - downLimit][j]);

            for (int i = m - downLimit; arr.size() < m * n && i >= upLimit; i--)//вверх
                arr.add(matrix[i][lLimit - 1]);

            upLimit++;
            downLimit++;
            lLimit++;
            rLimit++;
        }
        return arr;
    }

    protected List<Integer> spiralOrderV3(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        int n = matrix.length;
        int m = matrix[0].length;
        int top = 0, left = 0, bottom = n - 1, right = m - 1;
        while (top <= bottom && left <= right) {

            for (int i = left; i <= right; i++)
                ans.add(matrix[top][i]);
            top++;

            for (int i = top; i <= bottom; i++)
                ans.add(matrix[i][right]);
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    ans.add(matrix[bottom][i]);

                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    ans.add(matrix[i][left]);

                left++;
            }
        }
        return ans;
    }

    protected List<List<Integer>> generate(int numRows) {
        List<List<Integer>> resultList = new ArrayList<>();
        //1
        //11
        //121
        //1331
        for (int i = 0; i < numRows; i++) {
            List<Integer> nodeList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    nodeList.add(1);
                } else {
                    nodeList.add(resultList.get(i - 1).get(j - 1) + resultList.get(i - 1).get(j));
                }
            }
            resultList.add(nodeList);
        }
        return resultList;
    }

    protected String addBinary(String a, String b) {
        int ia = a.length() - 1;
        int ib = b.length() - 1;
        int carryOver = 0;
        StringBuilder sum = new StringBuilder();

        while (ia >= 0 || ib >= 0) {
            int cur = carryOver + (ia >= 0 ? a.charAt(ia--) - '0' : 0) +
                      (ib >= 0 ? b.charAt(ib--) - '0' : 0);
            sum.append(cur % 2);
            carryOver = cur / 2;
        }

        if (carryOver > 0) {
            sum.append(carryOver);
        }

        return sum.reverse().toString();
    }

    protected String addBinaryV2(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger sum = x.add(y);
        return sum.toString(2);
    }

    protected int pivotInteger(int n) {
        //1 2 3 4   5   6   7    8
        //1 3 6 10  15  21  28   36
        //allSum + x = sum[1, x] + sum[x, n]  <=> allSum + x = 2 * sum[1,x] <=> x = allSum - 2 * sum[1,x)
        //x = allSum - sum[1, x) - sum(x, n]

        int allSum = (1 + n) * n / 2;
        int x = -1;
        for (int i = 0; i < n; i++) {
            if ((i + 1) == (allSum - 2 * (1 + i) * i / 2)) {
                return i + 1;
            }
        }
        return x;
    }

    protected int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    protected String longestCommonPrefix(String[] strs) {
        StringBuilder prefixStr = new StringBuilder();
        int minLenStr = strs[0].length();
        for (String str : strs) {
            if (str.length() <= minLenStr) {
                prefixStr = new StringBuilder(str);
                minLenStr = str.length();
            }
        }
        for (int i = 0; i < strs.length; i++) {
            if (prefixStr.length() != 0 && !strs[i].startsWith(prefixStr.toString())) {
                prefixStr.deleteCharAt(prefixStr.length() - 1);
                i--;
            }
        }
        return prefixStr.toString();
    }

    protected int numSubarraysWithSum(int[] nums, int goal) {
        int count = 0;
        int sum = 0;

        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1); // Начальное значение суммы 0 встречается 1 раз

        for (int num : nums) {
            sum += num;
            count += sumCount.getOrDefault(sum - goal, 0); // Подсчет количества субмассивов с целевой суммой
            sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1); // Обновление счетчика сумм
        }
        return count;
    }

    protected int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int product = 1;

        int preffixProduct = 1;
        for (int i = 0; i < result.length; i++) {
            result[i] = preffixProduct;
            preffixProduct *= nums[i];
        }

        int suffixProduct = 1;
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return result;
    }

    protected int findMaxLength(int[] nums) {
        //[0, 1] countZero == countOne, maxLen -> nums.length

        //[1, 1, 1, 0, 1, 0, 0, 1]
        //[1, 2, 3, 2, 3, 2, 1, 2]

        //[0, 0, 0, 1, 1, 0]
        //[-1,-2,-3,-2,-1,-2]
        int maxLen = 0;
        int count = 0;

        Map<Integer, Integer> sumAndI = new HashMap<>();
        sumAndI.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) count--;
            else count++;

            if (sumAndI.containsKey(count)) {
                maxLen = Math.max(maxLen, i - sumAndI.get(count));
            } else sumAndI.put(count, i);
        }

        return maxLen;
    }

    protected int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            maxSum += nums[i];

        }
        return maxSum;
    }

    protected int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int l = 0;
        int r = intervals.length;
        while (l < r && newInterval[0] > intervals[l][1]) {
            result.add(intervals[l]);
            l++;
        }

        while (l < r && newInterval[1] >= intervals[l][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[l][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[l][1]);
            l++;
        }
        result.add(newInterval);

        while (l < r) {
            result.add(intervals[l]);
            l++;
        }
        return result.toArray(new int[result.size()][]);
    }

    protected int findMinArrowShots(int[][] points) {
        // [[10,16],[2,8],[1,6],[7,12]]
        // [[1,6], [2,8], [7,12], [10,16]]
        // [[2,6],[10,12]]

        // [[1,17], [2,8], [7,12], [10,16]]
        Arrays.sort(points, Comparator.comparingInt(x -> x[0]));
        int union = points[0][1];
        int result = 1;
        for (int i = 1; i < points.length; i++) {
            if (union >= points[i][0]) {
                union = Math.min(union, points[i][1]);
            } else {
                union = points[i][1];
                result++;
            }
        }
        return result;
    }

    protected int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do { // we are sure that at least one duplicate is there
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // find the head of loop
        fast = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    protected List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            if (nums[val - 1] < 0) {
                result.add(val);
            } else {
                nums[val - 1] *= -1;
            }
        }
        return result;
    }

    protected int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int[] result = new int[2];
        while (i < j) {
            if ((target - numbers[i]) == numbers[j]) {
                result[0] = i + 1;
                result[1] = j + 1;
                break;
            } else if ((numbers[j] + numbers[i]) > target) {
                j--;
            } else if ((numbers[j] + numbers[i]) < target) {
                i++;
            }
        }
        return result;
    }

    protected int numSubarrayProductLessThanK(int[] nums, int k) {
        //[10]
        //count = 1
        //[10, 5]
        //count = 1 + 2 = 3
        //[10, 5, 2]
        //count = 3 + 2 = 5
        //[10, 5, 2, 6]
        //count = 5 + 3 = 8
        if (k <= 1) return 0;
        int count = 0;
        int product = 1;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k) {
                product /= nums[left++];
            }
            count += right - left + 1;
        }
        return count;
    }

    protected int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Поместим каждое положительное число в его "правильное" место в массиве
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // Переставляем nums[i] на его правильное место
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        // Найдем первый элемент, не удовлетворяющий условию nums[i] = i + 1
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // Если все числа от 1 до n присутствуют в массиве, то наименьшее недостающее число - n + 1
        return n + 1;
    }

    protected int maxSubarrayLength(int[] nums, int k) {
        //[1,2,3,1,2,3,1,2]
        Map<Integer, Integer> countElement = new HashMap<>();
        int maxLen = 0;
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            int num = nums[end];
            countElement.put(num, countElement.getOrDefault(num, 0) + 1);
            while (start <= end && countElement.get(num) > k) {
                int startNum = nums[start];
                countElement.put(startNum, countElement.get(startNum) - 1);
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }

    protected long countSubarrays(int[] nums, int k) {
        //[1, 3, 2, 3, 3, 2, 3]
        //[1, 3, 4, 6]
        long ans = 0;
        int left = 0, right = 0, count = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], max);
        }

        while (right < nums.length || left > right) {
            if (nums[right] == max) {
                count++;
            }
            while (count >= k) {
                if (nums[left] == max) {
                    count--;
                }
                left++;
                ans += nums.length - right;
            }
            right++;
        }
        return ans;
    }

    protected int maxDepth(String s) {
        int max = 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            max = Math.max(count, max);
        }
        return max;
    }

    protected int lengthOfLastWord(String s) {
        int len = 0;
        int k = s.length() - 1;
        while (k >= 0 && s.charAt(k) == ' ') {
            k--;
        }
        while (k >= 0 && s.charAt(k) != ' ') {
            len++;
            k--;
        }

        return len;
    }

//    protected boolean isIsomorphic(String s, String t) {
//        Map<Character, List<Integer>> sMap = new HashMap<>();
//        Map<Character, List<Integer>> tMap = new HashMap<>();
//        //abc
//        //egg
//        for (int i = 0; i < s.length(); i++) {
//            sMap.computeIfAbsent(s.charAt(i), x -> new ArrayList<>()).add(i);
//        }
//        for (int i = 0; i < t.length(); i++) {
//            tMap.computeIfAbsent(t.charAt(i), x -> new ArrayList<>()).add(i);
//        }
//        System.out.println(sMap.values());
//        System.out.println(tMap.values());
//        return sMap.values().containsAll(tMap.values());
//        //return sMap.values().equals(tMap.values());
//    }

    protected boolean isIsomorphic(String s, String t) {

        HashMap<Character, Character> mapS2T = new HashMap<>();
        HashMap<Character, Character> mapT2S = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check if there's a mapping for charS in mapS2T and if it maps to the same character in t
            if (mapS2T.containsKey(charS)) {
                if (mapS2T.get(charS) != charT) {
                    return false;
                }
            } else { // If no mapping exists, check if charT is already mapped to some other character in mapT2S
                if (mapT2S.containsKey(charT)) {
                    return false;
                }

                // Create new mapping since it's valid
                mapS2T.put(charS, charT);
                mapT2S.put(charT, charS);
            }
        }

        return true;
    }

    protected String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        int open = 0;

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') open++;
            else if (sb.charAt(i) == ')') {
                if (open == 0) {
                    sb.deleteCharAt(i);
                    i--;
                } else open--;
            }
        }

        for (int i = sb.length() - 1; i >= 0; i--) {
            if (open > 0 && sb.charAt(i) == '(') {
                sb.deleteCharAt(i);
                open--;
            }
        }
        return sb.toString();
    }

    protected int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int sum = 0;
        int minSize = Integer.MAX_VALUE;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum >= target) {
                minSize = Math.min(end - start + 1, minSize);
                sum -= nums[start++];
            }
        }
        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }

    protected List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        int step = 1;
        while (step <= rowIndex) {
            list.add(1);
            for (int j = list.size() - 2; j > 0; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }
            step++;
        }
        return list;
    }

    protected String reverseWords(String s) {
        s = s.trim().replaceAll(" +", " ");

        String[] words = s.split(" ");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            stringBuilder.append(words[i]);
            if (i > 0) stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    protected String reverseWordsV2(String s) {
        s = s.trim();

        Deque<String> words = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (word.length() > 0) {
                    words.push(word.toString());
                    word.setLength(0);
                }
            } else {
                word.append(c);
            }
        }
        words.push(word.toString());

        return String.join(" ", words);
    }

    protected String reverseWordsOther(String s) {
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = new StringBuilder(words[i])
                    .reverse()
                    .toString();
        }
        return String.join(" ", words);
    }

    protected void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt++] = nums[i];
            }
        }

        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    protected int minMoves2(int[] nums) {
        // [1, 2, 3]
        // 1 + 2 + 3 / 3 = 2
        // [1, 10 , 2 , 9]
        // 1 + 10 + 2 + 9 = 22
        // 22 / 4 = 5.5 5 6
        // 5: 4 5 3 4 = 16
        // 6: 5 4 4 3 = 16

        // 1 + 10 + 2 + 9 + 5 = 27
        // 27 / 5 = 5.4 5 6
        // 5: 4 5 3 4 0 = 16
        // 6: 5 4 4 3 1 = 17

        Arrays.sort(nums);

        int midEl;
        if (nums.length % 2 == 0) {
            midEl = nums[nums.length / 2];
        } else midEl = (nums[nums.length / 2] + nums[(nums.length + 1) / 2]) / 2;

        int res = 0;
        for (int el : nums) {
            res += Math.abs(el - midEl);
        }

        return res;
    }

    protected List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursiveTreePreorderTraversal(root, result);
        return result;
    }

    private void recursiveTreePreorderTraversal(TreeNode root, List<Integer> arr) {
        if (root == null) return;

        arr.add(root.val);

        if (root.left != null) recursiveTreePreorderTraversal(root.left, arr);
        if (root.right != null) recursiveTreePreorderTraversal(root.right, arr);
    }

    protected List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursiveTreeInorderTraversal(root, result);
        return result;
    }

    private void recursiveTreeInorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;

        if (root.left != null) recursiveTreeInorderTraversal(root.left, result);
        result.add(root.val);
        if (root.right != null) recursiveTreeInorderTraversal(root.right, result);
    }

    protected List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursiveTreePostorderTraversal(root, result);
        return result;
    }

    private void recursiveTreePostorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;

        if (root.left != null) recursiveTreePostorderTraversal(root.left, result);
        if (root.right != null) recursiveTreePostorderTraversal(root.right, result);

        result.add(root.val);
    }

    protected List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> bfsOrder = new ArrayList<>();
        if (root == null) return bfsOrder;

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int levelSize = nodes.size();

            List<Integer> listLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode el = nodes.poll();
                listLevel.add(el.val);

                if (el.left != null) nodes.add(el.left);
                if (el.right != null) nodes.add(el.right);
            }
            bfsOrder.add(listLevel);
        }
        return bfsOrder;
    }

    protected int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    protected boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) return node1 == node2;

        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node2.left, node1.right);
    }

    protected boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // Если узел - лист и targetSum совпадает со значением узла
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }

        // Рекурсивный вызов для левого и правого поддерева
        return hasPathSum(root.left, targetSum - root.val) ||
               hasPathSum(root.right, targetSum - root.val);
    }

    private int postIndex;

    protected TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderIndexes = new HashMap<>();
        postIndex = postorder.length - 1;

        for (int i = 0; i < postorder.length; i++) {
            inorderIndexes.put(inorder[i], i);
        }

        return buildTreeRecursive(inorderIndexes, postorder, 0, inorder.length - 1);
    }


    private TreeNode buildTreeRecursive(Map<Integer, Integer> inorderIndexes, int[] postorder, int start, int end) {
        if (start > end) {
            return null;
        }

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int inorderIndex = inorderIndexes.get(rootVal);


        root.right = buildTreeRecursive(inorderIndexes, postorder, inorderIndex + 1, end);
        root.left = buildTreeRecursive(inorderIndexes, postorder, start, inorderIndex - 1);

        return root;
    }

    private int rootIndex;

    protected TreeNode preorderbuildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIndexes = new HashMap<>();
        rootIndex = 0;

        for (int i = 0; i < inorder.length; i++) {
            inorderIndexes.put(inorder[i], i);
        }

        return recursivePreorderBuildTree(inorderIndexes, preorder, 0, inorder.length - 1);
    }

    private TreeNode recursivePreorderBuildTree(Map<Integer, Integer> inorderIndexes, int[] preorder, int start, int end) {
        if (start > end) return null;

        int rootVal = preorder[rootIndex++];
        TreeNode root = new TreeNode(rootVal);

        int inorderRootIndex = inorderIndexes.get(rootVal);

        root.left = recursivePreorderBuildTree(inorderIndexes, preorder, start, inorderRootIndex - 1);
        root.right = recursivePreorderBuildTree(inorderIndexes, preorder, inorderRootIndex + 1, end);

        return root;
    }

    protected Node connect(Node root) {
        if (root == null || root.left == null) return root;

        root.left.next = root.right;

        if (root.next != null) root.right.next = root.next.left;

        connect(root.left);
        connect(root.right);

        return root;
    }

    protected Node connect2(Node root) {
        if (root == null) return root;

        if (root.left != null) root.left.next = (root.right != null) ? root.right : getNext(root.next);
        if (root.right != null) root.right.next = getNext(root.next);

        connect(root.right);
        connect(root.left);

        return root;
    }

    private Node getNext(Node node) {
        while (node != null) {
            if (node.left != null) return node.left;
            if (node.right != null) return node.right;
            node = node.next;
        }
        return null;
    }

    // Encodes a tree to a single string.
    protected String serialize(TreeNode root) {
        StringBuilder s = new StringBuilder();
        serializeRecursive(root, s);
        return s.toString().trim();
    }

    private void serializeRecursive(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            stringBuilder.append("null ");
            return;
        }

        stringBuilder.append(root.val).append(" ");
        serializeRecursive(root.left, stringBuilder);
        serializeRecursive(root.right, stringBuilder);
    }

    // Decodes your encoded data to tree.
    protected TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] stringRoot = data.split(" ");
        Queue<String> queue = new LinkedList<>(Arrays.asList(stringRoot));
        return deserializeRecursive(queue);
    }

    private TreeNode deserializeRecursive(Queue<String> queue) {
        String el = queue.poll();
        if (el == null || Objects.equals(el, "null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(el));
        root.left = deserializeRecursive(queue);
        root.right = deserializeRecursive(queue);

        return root;
    }

    static class MyCircularQueue {
        private int[] arr;
        private int start;
        private int end;
        private int size;
        private int capacity;

        protected MyCircularQueue(int k) {
            arr = new int[k];
            start = 0;
            end = -1;
            size = 0; // переменная для хранения текущего размера очереди
            capacity = k; // вместимость очереди
        }

        protected boolean enQueue(int value) {
            if (isFull()) return false; // если очередь заполнена, вставка невозможна
            end = (end + 1) % capacity; // циклическое обновление индекса
            arr[end] = value;
            size++; // увеличиваем размер
            return true;
        }

        protected boolean deQueue() {
            if (isEmpty()) return false; // если очередь пуста, удаление невозможно
            start = (start + 1) % capacity; // циклическое обновление индекса
            size--; // уменьшаем размер
            return true;
        }

        protected int Front() {
            if (isEmpty()) return -1; // если очередь пуста, вернуть -1
            return arr[start];
        }

        protected int Rear() {
            if (isEmpty()) return -1; // если очередь пуста, вернуть -1
            return arr[end]; // корректное получение последнего элемента
        }

        protected boolean isEmpty() {
            return size == 0; // очередь пуста, если нет элементов
        }

        protected boolean isFull() {
            return size == capacity; // очередь заполнена, если количество элементов равно вместимости
        }
    }

    private Queue<PointIsland> queueIsland;
    private Set<PointIsland> visited = new HashSet<>();

    protected int numIslands(char[][] grid) {
        int countIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited.contains(new PointIsland(i, j))) {
                    helpSearchIslands(grid, i, j);
                    countIslands++;
                }
            }
        }
        return countIslands;
    }


    private void helpSearchIslands(char[][] grid, int i, int j) {
        queueIsland = new LinkedList<>();
        PointIsland pointIsland = new PointIsland(i, j);
        queueIsland.add(pointIsland);
        visited.add(pointIsland);

        while (!queueIsland.isEmpty()) {
            pointIsland = queueIsland.poll();

            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    if (Math.abs(k) + Math.abs(l) == 1) {
                        int newX = pointIsland.x + k;
                        int newY = pointIsland.y + l;
                        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                            if (grid[newX][newY] == '1') {
                                PointIsland neighbordPointIsland = new PointIsland(newX, newY);
                                if (!visited.contains(neighbordPointIsland)) {
                                    queueIsland.offer(neighbordPointIsland);
                                    visited.add(new PointIsland(neighbordPointIsland.x, neighbordPointIsland.y));
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    private class PointIsland {
        private int x;
        private int y;

        protected int getX() {
            return x;
        }

        protected int getY() {
            return y;
        }

        protected PointIsland(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PointIsland that = (PointIsland) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    protected int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Направления для движения (вверх, вниз, влево, вправо)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Обход всей сетки
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Если встретили "1" - новый остров
                if (grid[i][j] == '1') {
                    numIslands++;
                    // Запускаем BFS для поиска всех частей острова
                    bfs(grid, i, j, directions);
                }
            }
        }
        return numIslands;
    }

    private void bfs(char[][] grid, int i, int j, int[][] directions) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = '0'; // Помечаем как посещенную

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];

            // Проверка всех 4 направлений
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // Проверка границ и того, что это часть острова (т.е. '1')
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '1') {
                    queue.offer(new int[]{newX, newY});
                    grid[newX][newY] = '0'; // Помечаем как посещенную
                }
            }
        }
    }

    protected int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        Queue<String> combines = new LinkedList<>();

        combines.offer("0000");
        int step = 0;
        while (!combines.isEmpty()) {
            int size = combines.size(); // Чтобы обрабатывать все элементы на текущем уровне одновременно
            for (int k = 0; k < size; k++) {
                String comb = combines.poll();

                // Если текущая комбинация находится в deadends
                if (visited.contains(comb)) continue;
                else visited.add(comb);

                // Если комбинация совпала с целевой
                if (comb.equals(target)) return step;

                // Перебираем все 4 колеса замка
                for (int i = 0; i < 4; i++) {
                    for (int d = -1; d <= 1; d += 2) {
                        char[] newComb = comb.toCharArray();
                        // Увеличиваем и уменьшаем значение каждого колеса
                        newComb[i] = (char) (((newComb[i] - '0' + d + 10) % 10) + '0');
                        String newCombStr = new String(newComb);

                        // Проверяем, что новые комбинации не в deadends и еще не были посещены
                        if (!visited.contains(newCombStr)) {
                            combines.offer(newCombStr);
                        }

                    }
                }
            }
            step++;
        }
        return -1;
    }


    protected int numSquares(int n) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> squares = new LinkedList<>();

        squares.offer(n);
        visited.add(n);

        int step = 0;
        while (!squares.isEmpty()) {
            int size = squares.size();
            step++;

            for (int i = 0; i < size; i++) {
                int sq = squares.poll();

                for (int j = 1; j * j <= sq; j++) {
                    int nextSq = sq - j * j;

                    if (nextSq == 0) return step;

                    if (!visited.contains(nextSq)) {
                        squares.offer(nextSq);
                        visited.add(nextSq);
                    }
                }
            }
        }
        return step;
    }


    static class MinStack {
        List<Integer> stack;
        List<Integer> minStack;

        protected MinStack() {
            this.stack = new ArrayList<>();
            this.minStack = new ArrayList<>();
        }

        protected void push(int val) {
            //5  5
            //3  3
            //4
            //1  1
            //2
            //-1 -1
            if (minStack.isEmpty() || val <= minStack.get(minStack.size() - 1)) minStack.add(val);
            stack.add(val);
        }

        protected void pop() {
            int removeVal = stack.remove(stack.size() - 1);
            if (minStack.get(minStack.size() - 1) == removeVal) minStack.remove(minStack.size() - 1);
        }

        protected int top() {
            return stack.get(stack.size() - 1);
        }

        protected int getMin() {
            return minStack.get(minStack.size() - 1);
        }
    }

    protected boolean isValid(String s) {
        char[] bracket = s.toCharArray();
        Stack<Character> openBrackets = new Stack<>();
        for (char c : bracket) {
            if (isOpenBracket(c)) openBrackets.push(c);
            else if (openBrackets.isEmpty() || openBrackets.pop() != getOpenBracketForClose(c)) {
                return false;
            }
        }
        return openBrackets.isEmpty();
    }

    private Character getOpenBracketForClose(char c) {
        char result = ' ';
        switch (c) {
            case ']':
                result = '[';
                break;
            case '}':
                result = '{';
                break;
            case ')':
                result = '(';
        }
        return result;
    }

    private boolean isOpenBracket(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    protected boolean isValidV2(String s) {
        Map<Character, Character> BRACKETS_MAP = new HashMap<>();
        BRACKETS_MAP.put(')', '(');
        BRACKETS_MAP.put('}', '{');
        BRACKETS_MAP.put(']', '[');

        Stack<Character> openBrackets = new Stack<>();

        for (char c : s.toCharArray()) {
            if (BRACKETS_MAP.containsValue(c)) {
                // Если это открывающая скобка, кладём в стек
                openBrackets.push(c);
            } else {
                // Если это закрывающая скобка
                if (openBrackets.isEmpty() || openBrackets.pop() != BRACKETS_MAP.get(c)) {
                    return false;
                }
            }
        }
        return openBrackets.isEmpty();
    }


    protected int[] dailyTemperatures(int[] temperatures) {
        //Stack<Integer> prefIndexes = new Stack<>();
        Deque<Integer> prefIndexes = new ArrayDeque<>();

        int[] waitDays = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!prefIndexes.isEmpty() && temperatures[i] > temperatures[prefIndexes.peek()]) {
                int waitDay = prefIndexes.pop();
                waitDays[waitDay] = i - waitDay;
            }
            prefIndexes.push(i);
        }
        return waitDays;
    }

    protected int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (isOperator(token)) {
                int op1 = stack.pop();
                int op2 = stack.pop();
                int sum = applyOperator(token, op1, op2);
                stack.push(sum);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    protected boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    protected int applyOperator(String s, int b, int a) {
        switch (s) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid");
        }
    }


    protected int numIslandsDfs(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Направления для движения (вверх, вниз, влево, вправо)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Обход всей сетки
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Если встретили "1" - новый остров
                if (grid[i][j] == '1') {
                    numIslands++;
                    // Запускаем BFS для поиска всех частей острова
                    dfs(grid, i, j, directions);
                }
            }
        }
        return numIslands;
    }

    private void dfs(char[][] grid, int i, int j, int[][] directions) {
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{i, j});
        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int x = cell[0];
            int y = cell[1];

            grid[x][y] = '0'; // Помечаем как посещенную

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '1') {
                    stack.push(new int[]{newX, newY});
                }
            }
        }
    }

    static class GraphNode {
        protected int val;
        protected List<GraphNode> neighbors;

        protected GraphNode() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        protected GraphNode(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        protected GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    protected GraphNode cloneGraph(GraphNode node) {
        if (node == null) return null;

        // Map для хранения уже клонированных узлов
        Map<GraphNode, GraphNode> clones = new HashMap<>();

        // Запускаем рекурсивный DFS
        return cloneGraphDFS(node, clones);
    }

    private GraphNode cloneGraphDFS(GraphNode node, Map<GraphNode, GraphNode> clones) {
        // Если узел уже был клонирован, просто возвращаем его
        if (clones.containsKey(node)) {
            return clones.get(node);
        }
        // Создаем клон узла
        GraphNode cloneNode = new GraphNode(node.val);

        //Сохраняем его в Map
        clones.put(node, cloneNode);

        // Клонируем всех соседей
        for (var neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraphDFS(neighbor, clones));
        }
        return clones.get(node);
    }

    protected int findTargetSumWays(int[] nums, int target) {
        // Карта для хранения результатов подзадач (индекс, target) -> количество способов
        Map<String, Integer> memo = new HashMap<>();
        return dfsFindTargetSum(nums, 0, target, memo);
    }

    private int dfsFindTargetSum(int[] nums, int i, int target, Map<String, Integer> memo) {
        // Условие выхода: если прошли все элементы массива
        if (i == nums.length) {
            return target == 0 ? 1 : 0;
        }

        // Ключ для хранения в памяти (сочетание индекса и текущего target)
        String key = i + "," + target;

        // Если результат уже существует в карте, возвращаем его
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Рекурсивно проверяем оба варианта: + и -
        int count = dfsFindTargetSum(nums, i + 1, target - nums[i], memo) +
                    dfsFindTargetSum(nums, i + 1, target + nums[i], memo);

        // Сохраняем результат для текущего состояния в карту
        memo.put(key, count);

        return count;
    }


    protected List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            // Спускаемся по левому поддереву, сохраняя все узлы в стеке
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();

            result.add(current.val);

            current = current.right;
        }

        return result;
    }

    protected String decodeString(String s) {
        StringBuilder currentString = new StringBuilder();
        Deque<String> stackString = new ArrayDeque<>();
        Deque<Integer> stackNumber = new ArrayDeque<>();
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                StringBuilder number = new StringBuilder();
                while (Character.isDigit(s.charAt(i))) {
                    number.append(s.charAt(i));
                    i++;
                }
                stackNumber.push(Integer.parseInt(number.toString()));
            } else if (ch == '[') {
                stackString.push(currentString.toString());
                currentString = new StringBuilder();
                i++;
            } else if (ch == ']') {
                // Завершаем текущую строку и повторяем ее необходимое количество раз
                StringBuilder temp = new StringBuilder(stackString.pop());
                int repeatTimes = stackNumber.pop();
                currentString = temp.append(String.valueOf(currentString).repeat(repeatTimes));
                i++;
            } else {
                currentString.append(ch);
                i++;
            }
        }
        return currentString.toString();
    }

    int firstColor;

    protected int[][] floodFill(int[][] image, int sr, int sc, int color) {
        firstColor = image[sr][sc];
        if (firstColor == color) return image;
        dfsFloodFill(image, sr, sc, color);
        return image;
    }

    private void dfsFloodFill(int[][] image, int sr, int sc, int color) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != firstColor) return;

        image[sr][sc] = color;

        dfsFloodFill(image, sr + 1, sc, color);
        dfsFloodFill(image, sr - 1, sc, color);
        dfsFloodFill(image, sr, sc + 1, color);
        dfsFloodFill(image, sr, sc - 1, color);
    }

    protected int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return new int[0][0];
        }

        int m = mat.length;
        int n = mat[0].length;
        int[][] result = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();

        // Инициализация и заполнение очереди
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Направления для соседних ячеек
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Выполнение BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                // Проверка границ и обновление расстояния
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && result[nx][ny] > result[x][y] + 1) {
                    result[nx][ny] = result[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return result;
    }


    protected boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visitRooms = new HashSet<>();

        Queue<Integer> keys = new LinkedList<>();

        keys.offer(0);

        while (!keys.isEmpty()) {
            int key = keys.poll();

            if (visitRooms.add(key)) {
                for (var keyInRoom : rooms.get(key)) {
                    if (!visitRooms.contains(keyInRoom)) {
                        keys.offer(keyInRoom);
                    }
                }
            }
        }
        return visitRooms.size() == rooms.size();
    }


    protected boolean canVisitAllRoomsDfs(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        dfs(rooms, 0, visited);
        return visited.size() == rooms.size();
    }

    private void dfs(List<List<Integer>> rooms, int room, Set<Integer> visited) {
        if (visited.add(room)) {
            for (int key : rooms.get(room)) {
                dfs(rooms, key, visited);
            }
        }
    }

    protected ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode s = head.next;

        head.next = swapPairs(s.next);
        s.next = head;

        return s;
    }

    Map<List<Integer>, Integer> visitedMap;

    protected List<Integer> getRowRecursive(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        visitedMap = new HashMap<>();
        for (int i = 0; i <= rowIndex; i++) {
            row.add(recursivePascalTriangle(rowIndex, i));
        }
        return row;
    }

    private int recursivePascalTriangle(int rowIndex, int colIndex) {
        // Базовые случаи для первого и последнего элементов строки
        if (colIndex == 0 || colIndex == rowIndex) {
            return 1;
        }

        // Используем List<Integer> как ключ
        List<Integer> visit = Arrays.asList(rowIndex, colIndex);

        // Проверяем наличие ключа в мапе
        if (visitedMap.containsKey(visit)) return visitedMap.get(visit);

        // Вычисляем значение и сохраняем в мапе
        int el = recursivePascalTriangle(rowIndex - 1, colIndex - 1) + recursivePascalTriangle(rowIndex - 1, colIndex);
        visitedMap.put(visit, el);

        return el;
    }

    protected ListNode reverseList(ListNode head) {
        // Базовый случай: если head пуст или последний элемент, возвращаем его
        if (head == null || head.next == null) {
            return head;
        }

        // Рекурсивный вызов для оставшейся части списка
        ListNode reversedHead = reverseList(head.next);

        // Меняем направления ссылок: head.next указывает на текущий head
        head.next.next = head;

        // Отключаем связь с предыдущим элементом
        head.next = null;

        // Возвращаем новый "голова" списка
        return reversedHead;
    }

    protected TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;

        if (root.val == val) return root;
        TreeNode node = null;
        if (root.val > val) node = searchBST(root.left, val);
        if (root.val < val) node = searchBST(root.right, val);

        return node;
    }

    // Универсальная функция-декоратор для мемоизации
    protected <T, R> Function<T, R> memoize(Function<T, R> function) {
        // Кэш для хранения результатов
        Map<T, R> cache = new HashMap<>();

        // Возвращаем обёрнутую функцию с мемоизацией
        return input -> {
            if (cache.containsKey(input)) {
                return cache.get(input); // Возвращаем закэшированный результат
            }
            R result = function.apply(input); // Вызываем оригинальную функцию
            cache.put(input, result); // Сохраняем результат в кэш
            return result;
        };
    }

    // Обычная рекурсивная функция для вычисления чисел Фибоначчи
    protected int fibonacci(int n) {
        if (n < 2) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    Map<Integer, Integer> memo = new HashMap<>();

    protected int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        if (memo.containsKey(n)) return memo.get(n);

        int el = fib(n - 1) + fib(n - 2);

        memo.put(n, el);

        return el;
    }

    Map<Integer, Integer> memoSteps = new HashMap<>();

    protected int climbStairs(int n) {
        if (memoSteps.containsKey(n)) return memoSteps.get(n);

        if (n == 0) {
            return 1;
        } else if (n < 0) return 0;


        int count = climbStairs(n - 1) + climbStairs(n - 2);

        memoSteps.put(n, count);

        return count;
    }

    protected double myPow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;

        long nl = n;
        if (n < 0) {
            x = 1 / x;
            nl = -nl;
        }

        return helpMyPow(x, nl, 1);
    }

    private double helpMyPow(double x, long n, double result) {
        if (n == 0) return result;

        if (n % 2 == 0) {
            return helpMyPow(x * x, n / 2, result);  // Если n чётное
        } else {
            return helpMyPow(x, n - 1, result * x);  // Если n нечётное
        }
    }

    protected ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode listMerge = new ListNode(0); // Создаем заголовок для нового списка
        helpMergeList(list1, list2, listMerge);
        return listMerge.next; // Возвращаем результат, пропуская заголовок
    }

    private void helpMergeList(ListNode list1, ListNode list2, ListNode list3) {
        // Если один из списков пуст, соединяем оставшийся список
        if (list1 == null) {
            list3.next = list2;
            return;
        }
        if (list2 == null) {
            list3.next = list1;
            return;
        }

        // Сравниваем значения и рекурсивно продолжаем с оставшимися элементами
        if (list1.val <= list2.val) {
            list3.next = list1; // Присоединяем list1 к результату
            helpMergeList(list1.next, list2, list3.next); // Рекурсивный вызов
        } else {
            list3.next = list2; // Присоединяем list2 к результату
            helpMergeList(list1, list2.next, list3.next); // Рекурсивный вызов
        }
    }

    protected int kthGrammar(int n, int k) {
        return helpKthGrammar(n, k, 0);
    }

    private int helpKthGrammar(int n, int k, int currentVal) {
        if (n == 1) return currentVal;

        int halfCol = (int) Math.pow(2, n - 1) / 2;

        if (k > halfCol) {
            int nextRootVal = currentVal == 0 ? 1 : 0;
            return helpKthGrammar(n - 1, k - halfCol, nextRootVal);
        } else {
            int nextRootVal = currentVal == 0 ? 0 : 1;
            return helpKthGrammar(n - 1, k, nextRootVal);
        }
    }

    protected int recursion(int n, int k) {
        // First row will only have one symbol '0'.
        if (n == 1) {
            return 0;
        }

        int totalElements = (int) Math.pow(2, (n - 1));
        int halfElements = totalElements / 2;

        // If the target is present in the right half, we switch to the respective left half symbol.
        if (k > halfElements) {
            return 1 - recursion(n, k - halfElements);
        }

        // Otherwise, we switch to the previous row.
        return recursion(n - 1, k);
    }

    protected int kthGrammarV2(int n, int k) {
        return recursion(n, k);
    }

    protected List<TreeNode> generateTrees(int n) {
        Map<Pair<Integer, Integer>, List<TreeNode>> memo = new HashMap<>();
        return helpGenerateTrees(1, n, memo);
    }

    private List<TreeNode> helpGenerateTrees(int start, int end, Map<Pair<Integer, Integer>, List<TreeNode>> memo) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        Pair<Integer, Integer> pair = new Pair<>(start, end);
        if (memo.containsKey(pair)) {
            return memo.get(pair);
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubTree = helpGenerateTrees(start, i - 1, memo);
            List<TreeNode> rightSubTree = helpGenerateTrees(i + 1, end, memo);

            for (TreeNode left : leftSubTree) {
                for (TreeNode right : rightSubTree) {
                    TreeNode root = new TreeNode(i, left, right);
                    res.add(root);
                }
            }
        }

        memo.put(pair, res);
        return res;
    }
}
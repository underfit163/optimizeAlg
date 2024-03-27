package leetcode;

import java.math.BigInteger;
import java.util.*;

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
        System.out.println(solution.findDuplicate(new int[]{3, 2, 3, 1}));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
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

    public int findMaxConsecutiveOnes(int[] nums) {
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

    public int findNumbers(int[] nums) {
        int countEven = 0;
        for (int num : nums) {
            int count = (int) Math.log10(num) + 1;
            if (count % 2 == 0) countEven++;
        }
        return countEven;
    }

    public int[] sortedSquares(int[] nums) {
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

    public void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0) {
                System.arraycopy(arr, i, arr, i + 1, arr.length - 1 - i);
                i++;
            }
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
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

    public int removeElement(int[] nums, int val) {
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

    public int removeDuplicates(int[] nums) {
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
        return end;
    }

    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int j : arr) {
            if (j % 2 == 0 && set.contains(j / 2)) return true;
            if (set.contains(j * 2)) return true;
            set.add(j);
        }
        return false;
    }

    public boolean validMountainArray(int[] arr) {
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

    public int heightChecker(int[] heights) {
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

    public int thirdMax(int[] nums) {
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

    public List<Integer> findDisappearedNumbers(int[] nums) {
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


    public int pivotIndex(int[] nums) {
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

    public int[] plusOne(int[] digits) {
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

    public int[] findDiagonalOrderV1(int[][] mat) {
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

    public int[] findDiagonalOrderV2(int[][] mat) {
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

    public int[] intersection(int[] nums1, int[] nums2) {
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


    public List<Integer> spiralOrderV1(int[][] matrix) {
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


    public List<Integer> spiralOrderV2(int[][] matrix) {
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

    public List<Integer> spiralOrderV3(int[][] matrix) {
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

    public List<List<Integer>> generate(int numRows) {
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

    public String addBinary(String a, String b) {
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

    public String addBinaryV2(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger sum = x.add(y);
        return sum.toString(2);
    }

    public int pivotInteger(int n) {
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

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public String longestCommonPrefix(String[] strs) {
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

    public int numSubarraysWithSum(int[] nums, int goal) {
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

    public int[] productExceptSelf(int[] nums) {
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

    public int findMaxLength(int[] nums) {
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

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            maxSum += nums[i];

        }
        return maxSum;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
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

    public int findMinArrowShots(int[][] points) {
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

    public int findDuplicate(int[] nums) {
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

    public List<Integer> findDuplicates(int[] nums) {
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

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int[] result = new int[2];
        while (i < j) {
            if((target - numbers[i]) == numbers[j]) {
                result[0] = i + 1;
                result[1] = j + 1;
                break;
            }
            else if((numbers[j] + numbers[i]) > target) {
                j--;
            } else if((numbers[j] + numbers[i]) < target) {
                i++;
            }
        }
        return result;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
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

    public int firstMissingPositive(int[] nums) {
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
}
package lab7;

public class Magic {
    public int magicIndexV1(int[] array) {
        return magicIndexV1(array, 0, array.length-1);
    }

    public int magicIndexV1(int[] array, int start, int end) {
        if(start < 0 || start > end || end>= array.length) {
            return -1;
        }

        int midIndex = (start + end) / 2;
        int midValue = array[midIndex];

        if (midIndex == midValue) {
            return midIndex;
        } else if(array[midIndex] > midIndex) {
            int leftIndex = Math.min(midIndex - 1, midValue);
            return magicIndexV1(array, start, leftIndex);
        } else {
            int rightIndex = Math.max(midIndex + 1, midValue);
            return magicIndexV1(array, rightIndex, end);
        }
    }

    public int magicIndexV2(int[] array) {
        return magicIndexV2(array, 0, array.length - 1);
    }

    public int magicIndexV2(int[] array, int start, int end) {
        if(start < 0 || start > end || end>= array.length) {
            return -1;
        }

        int midIndex = (start + end) / 2;
        int midValue = array[midIndex];

        if (midValue == midIndex) {
            return midIndex;
        }

        int leftIndex = Math.min(midIndex - 1, midValue);
        int left = magicIndexV2(array, start, leftIndex);
        if (left >= 0) {
            return left;
        }

        int rightIndex = Math.max(midIndex + 1, midValue);
        return magicIndexV2(array, rightIndex, end);
    }
}

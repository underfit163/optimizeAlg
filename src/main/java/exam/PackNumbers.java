package exam;

public class PackNumbers {
    int[] numSize;
    int[] numShift;

    public long pack(int[] arr) {
        numSize = new int[arr.length];
        numShift = new int[arr.length];
        int shift = 0;
        long value = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                int size = 1;
                int num = arr[i];
                while ((num = num >> 1) > 0) size++;
                numSize[i] = size;
                numShift[i] = shift;
                shift += size;
            }
            value += (long) arr[i] << numShift[i];
        }

        System.out.println("Распаковка:");
        for (int i = 0; i < numSize.length; i++) {
            long mask = (1L << numSize[i]) - 1;
            System.out.println(((value >> numShift[i]) & mask));
        }
        return value;
    }
}

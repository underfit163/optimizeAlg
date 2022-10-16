package lab2;

import java.util.Arrays;

public class ArrayStack31 {
    int[] buffer = new int[3];

    public void push(int stackNum, int value) {
        buffer = Arrays.copyOf(buffer, buffer.length + 1);
        buffer[stackNum - 1] += 1;
        System.arraycopy(buffer, sumLen(stackNum) + 3 - 1, buffer, sumLen(stackNum) + 3, buffer.length - 3 - sumLen(stackNum));
        /*находим индекс верхнего элемента массива*/
        int index = sumLen(stackNum) + 3 - 1;
        buffer[index] = value;
    }

    private int sumLen(int stackNum) {
        int lenStack = 0;
        for (int i = 0; i < stackNum; i++) {
            lenStack += buffer[i];
        }
        return lenStack;
    }

    public int pop(int stackNum) throws Exception {
        if (buffer[stackNum - 1] == 0) {
            throw new Exception("Попытка использовать пустой стек");
        }
        int index = sumLen(stackNum) + 3 - 1;
        int value = buffer[index];

        buffer[stackNum - 1] -= 1;
        System.arraycopy(buffer, index + 1, buffer, index, buffer.length - (index + 1));
        buffer = Arrays.copyOf(buffer, buffer.length - 1);

        return value;
    }

    public int peek(int stackNum) {
        int index = sumLen(stackNum) + 3 - 1;
        return buffer[index];
    }

    public boolean isEmpty(int stackNum) {
        return buffer[stackNum - 1] == 0;
    }

    public void printStack() {
        System.out.println("array: " + Arrays.toString(buffer));
    }
}

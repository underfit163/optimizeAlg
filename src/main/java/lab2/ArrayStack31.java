package lab2;

import java.util.Arrays;

public class ArrayStack31 {
    int[] stackSize = {0, 0, 0};
    int[] buffer = new int[0];

    public void push(int stackNum, int value) {
        buffer = Arrays.copyOf(buffer, buffer.length + 1);
        stackSize[stackNum - 1] += 1;
        System.arraycopy(buffer, sumLen(stackNum) - 1, buffer, sumLen(stackNum), buffer.length - sumLen(stackNum));
        /*находим индекс верхнего элемента массива*/
        int index = sumLen(stackNum) - 1;
        buffer[index] = value;
    }

    private int sumLen(int stackNum) {
        int lenStack = 0;
        for (int i = 0; i < stackNum; i++) {
            lenStack += stackSize[i];
        }
        return lenStack;
    }

    public int pop(int stackNum) throws Exception {
        if (stackSize[stackNum - 1] == 0) {
            throw new Exception("Попытка использовать пустой стек");
        }
        int index = sumLen(stackNum) - 1;
        int value = buffer[index];

        stackSize[stackNum - 1] -= 1;
        System.arraycopy(buffer, index + 1, buffer, index, buffer.length - (index + 1));
        buffer = Arrays.copyOf(buffer, buffer.length - 1);

        return value;
    }

    public int peek(int stackNum) {
        int index = sumLen(stackNum) - 1;
        return buffer[index];
    }

    public boolean isEmpty(int stackNum) {
        return stackSize[stackNum - 1] == 0;
    }

    public void printStack() {
        System.out.println("stackSize: " + Arrays.toString(stackSize));
        System.out.println("array: " + Arrays.toString(buffer));
    }
}

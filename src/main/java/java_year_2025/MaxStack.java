package java_year_2025;

import java.util.*;

public class MaxStack<T extends Comparable<T>> extends Stack<T> {
    private final List<T> arrMax;

    MaxStack() {
        arrMax = new ArrayList<>();
    }

    public T push(T item) {
        if (arrMax.isEmpty() || arrMax.get(arrMax.size() - 1).compareTo(item) <= 0) arrMax.add(item);
        return super.push(item);
    }

    public synchronized T pop() {
        if (!arrMax.isEmpty() && super.peek() == arrMax.get(arrMax.size() - 1)) arrMax.remove(arrMax.size() - 1);
        return super.pop();
    }

    public synchronized T peek() {
        return super.peek();
    }

    public T max() {
        if (arrMax.isEmpty()) throw new EmptyStackException();
        return arrMax.get(arrMax.size() - 1);
    }
}

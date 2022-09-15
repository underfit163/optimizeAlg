import java.util.Arrays;

public class MySet<T> {
    private final T[] values;
    private final int[] indexes;
    private int i = 0;

    public MySet(int n) {
        values = (T[]) new Object[n];
        indexes = new int[n];
        Arrays.fill(indexes, -1);
    }

    public T getVal(int i) {
        return values[i];
    }

    public int getIndex(int i) {
        return indexes[i];
    }

    public void addVal(T val) {
        int idx = Math.abs((Integer) val) % values.length;
        addNoRecursive(val, idx);
    }

    private void addRecursive(T val, int idx) {
        if (values[idx] == null) {
            values[idx] = val;
        } else if (!values[idx].equals(val)) {
            if (indexes[idx] == -1) {
                while (values[i] != null) {
                    i += 1;
                }
                values[i] = val;
                indexes[idx] = i;
            } else {
                addRecursive(val, indexes[idx]);
            }
        }
    }

    private void addNoRecursive(T val, int idx) {
        if (values[idx] == null) {
            values[idx] = val;
        } else if (!values[idx].equals(val)) {
            if (indexes[idx] == -1) {
                while (values[i] != null) {
                    i += 1;
                }
                values[i] = val;
                indexes[idx] = i;
            } else {
                while (indexes[idx] != -1) {
                    idx = indexes[idx];
                    if (values[idx].equals(val)) return;
                }
                while (values[i] != null) {
                    i += 1;
                }
                values[i] = val;
                indexes[idx] = i;
            }
        }
    }

    public boolean contains(T val) {
        int idx = Math.abs((Integer) val) % values.length;
        return containsNoRecursive(val, idx);
    }

    private boolean containsRecursive(T val, int idx) {
        if (values[idx] != null) {
            if (values[idx].equals(val)) {
                return true;
            } else if (indexes[idx] != -1) {
                return containsRecursive(val, indexes[idx]);
            }
        }
        return false;
    }

    private boolean containsNoRecursive(T val, int idx) {
        if (values[idx] != null) {
            if (values[idx].equals(val)) {
                return true;
            } else {
                while (indexes[idx] != -1) {
                    idx = indexes[idx];
                    if (values[idx].equals(val)) return true;
                }
            }
        }
        return false;
    }
}

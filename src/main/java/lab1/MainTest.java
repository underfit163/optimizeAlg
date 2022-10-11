package lab1;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Warmup(iterations = 2)
@Measurement(iterations = 8)
public class MainTest {

    public static final int SIZE = 10_000_000;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MainTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    private final int[] arr1 = new int[SIZE];
    private final int[] arr2 = new int[SIZE];

    @Setup
    public void setup() {
        Random random = new Random();
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt();
        }
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = random.nextInt();
        }
    }

    @Benchmark
    public void JavaHashSet() {
        findIntersection(arr1, arr2);
    }

    @Benchmark
    public void MyHashSet() {
        findIntersectionMySet(arr1, arr2);
    }


    public int[] findIntersection(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length) {
            return findIntersection(arr2, arr1);
        }
        Set<Integer> set = new HashSet<>();
        int[] result = new int[arr1.length];
        for (int k : arr1) {
            set.add(k);
        }
        int j = 0;
        for (int k : arr2) {
            if (set.contains(k)) {
                result[j++] = k;
            }
        }
        return Arrays.copyOf(result, j);
    }

    public int[] findIntersectionMySet(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length) {
            return findIntersectionMySet(arr2, arr1);
        }
        MySet<Integer> set = new MySet<>(arr1.length);
        int[] result = new int[arr1.length];
        for (int k : arr1) {
            set.addVal(k);
        }
        int j = 0;
        for (int k : arr2) {
            if (set.contains(k)) {
                result[j++] = k;
            }
        }
        return Arrays.copyOf(result, j);
    }
}
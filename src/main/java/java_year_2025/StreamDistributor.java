package java_year_2025;

import java.util.concurrent.*;

public class StreamDistributor {
    // Очереди для каждого из потоков обработки
    private static final BlockingQueue<Double> queueOne = new LinkedBlockingQueue<>();
    private static final BlockingQueue<Double> queueEven = new LinkedBlockingQueue<>();
    private static final BlockingQueue<Double> queueFractional = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        // Создаем пул потоков для генерации и обработки
        ExecutorService executor = Executors.newCachedThreadPool();

        // Поток-производитель: генерирует числа бесконечно
        executor.submit(() -> {
            double num = 0;
            while (true) {
                double generated = generateNumber(num);
                distributeNumber(generated);
                num++;
                // Небольшая задержка для наглядности (необязательно)
                Thread.sleep(50);
            }
        });
        // Поток 1: обрабатывает только число 1
        executor.submit(() -> processQueue("Поток 1 (только 1)", queueOne));

        // Поток 2: обрабатывает только чётные целые числа
        executor.submit(() -> processQueue("Поток 2 (чётные)", queueEven));

        // Поток 3: обрабатывает только дробные числа (с ненулевой дробной частью)
        executor.submit(() -> processQueue("Поток 3 (дробные)", queueFractional));
    }

    /**
     * Генерирует число. В данном примере мы для демонстрации чередуем целые и дробные числа.
     * Можно заменить на любую другую логику генерации.
     */
    private static double generateNumber(double base) {
        // Например, каждое третье число будет дробным
        if (((int) base) % 3 == 0) {
            return base + 0.5;
        } else {
            return base;
        }
    }

    /**
     * Распределяет число по нужной очереди согласно фильтрам:
     * - Если число == 1, отправляем в очередь потока 1.
     * - Если число целое и чётное, в очередь потока 2.
     * - Если число имеет дробную часть, в очередь потока 3.
     */
    private static void distributeNumber(double number) throws InterruptedException {
        if (number == 1.0) {
            queueOne.put(number);
        } else if (isInteger(number) && ((long) number) % 2 == 0) {
            queueEven.put(number);
        } else if (!isInteger(number)) {
            queueFractional.put(number);
        }
        // Если число не подходит ни под один из фильтров (например, целое нечетное, отличное от 1),
        // его можно либо игнорировать, либо обработать по умолчанию.
    }

    /**
     * Проверяет, является ли число целым.
     */
    private static boolean isInteger(double number) {
        return number == Math.floor(number);
    }

    /**
     * Метод для обработки очереди – просто выводит полученные числа.
     */
    private static void processQueue(String threadName, BlockingQueue<Double> queue) {
        try {
            while (true) {
                Double number = queue.take();
                System.out.println(threadName + " обработал число: " + number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(threadName + " прерван.");
        }
    }
}

import java.util.*;

public class Main
{
    private static class Column {
        private String name;
        private long max;
        private int size;
        private int shift;

        private Column(String name) {
            this.name = name;
        }
    }

    private static Column[] columns = new Column[] { new Column("a"), new Column("b"), new Column("c"), new Column("d") };

    public static void main(String[] args) {
        Random r = new Random();
        List<Map<String, Long>> items = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            Map<String, Long> item = new HashMap<>();
            item.put("a", (long) r.nextInt(10));
            item.put("b", (long) r.nextInt(100));
            item.put("c", (long) r.nextInt(1000));
            item.put("d", (long) r.nextInt(10000));
            items.add(item);

        }

        for (Map<String, Long> item : items) {
            for (Column column : columns) {
                column.max = Math.max(column.max, item.get(column.name));
            }
        }

        int shift = 0;
        for (Column column : columns) {
            long value = column.max;
            int size = 1;
            while ((value = value >> 1) > 0) size++;
            column.size = size;
            column.shift = shift;
            shift += size;
            System.out.println(column.name + ": " + column.max + ", " + column.size + ", " + column.shift);
        }

        for (Map<String, Long> item : items) {
            long value = 0;
            for (Column column : columns) {
                value += item.get(column.name) << column.shift;
            }
            item.put("e", value);
        }

        System.out.println();

        for (Map<String, Long> item : items) {
            for (Column column : columns) {
                int mask = (1 << column.size) - 1;
                long value = item.get("e");
                System.out.println(column.name + ": " + item.get(column.name) + " " +
                        ((value >> column.shift) & mask));
            }
        }
    }
}
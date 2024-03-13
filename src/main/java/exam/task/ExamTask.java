package exam.task;

public class ExamTask {
    public static void main(String[] args) {
        System.out.println(task(new String[]{"abc", "ab", "bc", "c"}));
    }

    public static int task(String[] arr) {
        Node root = new Node();
        for (String s : arr) {
            StringBuilder sb = new StringBuilder(s);
            sb.reverse();
            Node node = root;
            for (int j = 0; j < sb.length(); j++) {
                node = addNode(node, sb.charAt(j));
            }
            node.setWord(true);
        }
        int count = 0;
        for (String s : arr) {
            StringBuilder sb = new StringBuilder(s);
            sb.reverse();
            Node node = root;
            for (int j = 0; j < sb.length(); j++) {
                node = node.getChildren().get(sb.charAt(j));
            }
            if(!node.getChildren().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static Node addNode(Node current, char value) {
        boolean flag = current.getChildren().containsKey(value);
        if (!flag) {
            Node node = new Node();
            current.getChildren().put(value, node);
            return node;
        } else return current.getChildren().get(value);
    }
}

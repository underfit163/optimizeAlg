package exam.task;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Map<Character, Node> children = new HashMap<>();
    private boolean word;

    public Node() {
    }

    public Node(Map<Character, Node> children, boolean word) {
        this.children = children;
        this.word = word;
    }

    public Map<Character, Node> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, Node> children) {
        this.children = children;
    }

    public boolean isWord() {
        return word;
    }

    public void setWord(boolean word) {
        this.word = word;
    }
}

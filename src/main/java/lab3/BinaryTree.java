package lab3;

import java.util.*;

public class BinaryTree {
    private Node root;

    //Вставка элементов
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.getValue()) {
            current.setLeft(addRecursive(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(addRecursive(current.getRight(), value));
        }
        return current;
    }

    public void addNode(int value) {
        root = addRecursive(root, value);
    }

    public void preOrderTraversal(Node node, int level, Map<Integer, List<Integer>> treeLevelEl) {
        if (node != null) {
            addLevelMap(level, node.getValue(), treeLevelEl);

            preOrderTraversal(node.getLeft(), level + 1, treeLevelEl);
            preOrderTraversal(node.getRight(), level + 1, treeLevelEl);
        }
    }

    private void addLevelMap(int level, int value, Map<Integer, List<Integer>> treeLevelEl) {
        List<Integer> list;
        if (treeLevelEl.containsKey(level)) {
            list = treeLevelEl.get(level);
        } else {
            list = new ArrayList<>();
        }
        list.add(value);
        treeLevelEl.put(level, list);
    }

    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        int k = 1;
        int level = 1;
        Map<Integer, List<Integer>> treeLevelEl = new HashMap<>();
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            //System.out.print(" " + node.getValue());
            if (node.getLeft() != null) {
                nodes.add(node.getLeft());
                addLevelMap(level, node.getLeft().getValue(), treeLevelEl);
            }

            if (node.getRight() != null) {
                nodes.add(node.getRight());
                addLevelMap(level, node.getRight().getValue(), treeLevelEl);
            }
            if (k == (Math.pow(2, level) - 1)) {
                level++;
            }
            k++;
        }
    }

    public Node getRoot() {
        return root;
    }

    public List<List<Integer>> bstSequences(Node tree) {
        List<Node> list = new ArrayList<>();
        list.add(tree);
        return bstSequences(list);
    }

    public List<List<Integer>> bstSequences(List<Node> roots) {
        List<List<Integer>> output = new ArrayList<>();
        for(var i : roots) {
            List<Node> choices = new ArrayList<>(roots);
            Node root = choices.remove(choices.indexOf(i));
            if(root.getLeft() != null) choices.add(root.getLeft());
            if(root.getRight() != null)  choices.add(root.getRight());

            if(!choices.isEmpty()) {
                for(var s : bstSequences(choices)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(root.getValue());
                    list.addAll(s);
                    output.add(list);
                }
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(root.getValue());
                output.add(list); // end recursion
            }
        }
        return output;
    }
}

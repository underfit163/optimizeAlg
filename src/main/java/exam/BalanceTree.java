package exam;

public class BalanceTree {
    Node root;
//Проверяет балансировку, а так же находит высоту дерева
    public static int isBalanced(Node root) {
        if (root == null)
            return 0;
        int lh = isBalanced(root.getLeft());
        if (lh == -1)
            return -1;
        int rh = isBalanced(root.getRight());
        if (rh == -1)
            return -1;

        if (Math.abs(lh - rh) > 1)
            return -1;
        else
            return Math.max(lh, rh) + 1;
    }
}

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node root;

    public BinaryTree() {}

    private void rebalance() {
        if (root != null) {
            if (root.getLeftChild() != null)
                root.setLeftChild(rebalance(root.getLeftChild()));
            if (root.getRightChild() != null)
                root.setRightChild(rebalance(root.getRightChild()));
        }
    }

    private Node rebalance(Node node) {
        if (node.getRightChild() != null
                && node.getRightChild().getColor().equals(Color.RED)
                && node.getLeftChild() != null
                && node.getLeftChild().getColor().equals(Color.BLACK))
            node = rightSwap(node);
        if (node.getLeftChild() != null
                && node.getLeftChild().getColor().equals(Color.RED)
                && node.getLeftChild().getLeftChild() != null
                && node.getLeftChild().getLeftChild().getColor().equals(Color.RED))
            node = leftSwap(node);
        if (node.getLeftChild() != null
                && node.getLeftChild().getColor().equals(Color.RED)
                && node.getRightChild() != null
                && node.getRightChild().getColor().equals(Color.RED))
            colorSwap(node);
        if (root.getColor().equals(Color.RED))
            root.setColor(Color.BLACK);
        if (node.getLeftChild() != null)
            node.setLeftChild(rebalance(node.getLeftChild()));
        if (node.getRightChild() != null)
            node.setRightChild(rebalance(node.getRightChild()));
        return node;
    }

    private Node leftSwap(Node node) {
//        System.out.println("Левый поворот");
        Node y = node;
        Node x = y.getLeftChild() != null ? y.getLeftChild() : null;
        Node between = x.getRightChild() != null ? x.getRightChild() : null;
        x.setRightChild(y);
        y.setLeftChild(between);
        x.setColor(Color.BLACK);
        y.setColor(Color.RED);
        return x;
    }

    private Node rightSwap(Node node) {
//        System.out.println("Правый поворот");
        Node x = node;
        Node y = x.getRightChild() != null ? x.getRightChild() : null;
        Node between = y.getLeftChild() != null ? y.getLeftChild() : null;
        y.setLeftChild(x);
        x.setRightChild(between);
        y.setColor(Color.BLACK);
        x.setColor(Color.RED);
        return y;
    }

    private void colorSwap(Node node) {
//        System.out.println("Смена цвета");
        node.getLeftChild().setColor(Color.BLACK);
        node.getRightChild().setColor(Color.BLACK);
        node.setColor(Color.RED);
    }

    @Override
    public void add(T value) {
        TreeNode newTreeNode = new TreeNode(value);
        newTreeNode.setColor(Color.RED);
        if (root == null) {
            root = newTreeNode;
            root.setColor(Color.BLACK);
        }
        else if (value.compareTo((T) root.getValue()) > 0) {
            if (root.getRightChild() == null)
                root.setRightChild(newTreeNode);
            else
                add(root.getRightChild(), newTreeNode);
        }
        else if (value.compareTo((T) root.getValue()) < 0){
            if (root.getLeftChild() == null)
                root.setLeftChild(newTreeNode);
            else
                add(root.getLeftChild(), newTreeNode);
        }
        rebalance();
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

    private void add(Node treeNode, TreeNode newTreeNode) {
        if (newTreeNode.getValue().compareTo(treeNode.getValue()) > 0)
            if (treeNode.getRightChild() == null)
                treeNode.setRightChild(newTreeNode);
            else
                add(treeNode.getRightChild(), newTreeNode);
        else if (newTreeNode.getValue().compareTo(treeNode.getValue()) < 0)
            if (treeNode.getLeftChild() == null)
                treeNode.setLeftChild(newTreeNode);
            else
                add(treeNode.getLeftChild(), newTreeNode);
    }

    @Override
    public void printTree() {
        if (root == null) System.out.println("В дереве пусто");
        else printTree(root);
    }

    private void printTree(Node node) {
        if (node != null) {
            System.out.println(node);
            if (node.getLeftChild() != null) printTree(node.getLeftChild());
            if (node.getRightChild() != null) printTree(node.getRightChild());
        }
    }

    public Node get(T value) {
        if (root == null) return null;
        else return get(root, value);
    }

    private Node get(Node node, T value) {
        if (node.getValue().equals(value))
            return node;
        if (node.getLeftChild() != null)
            return get(node.getLeftChild(), value);
        if (node.getRightChild() != null)
            return get(node.getRightChild(), value);
        return null;
    }

}

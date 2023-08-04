public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node root;

    public BinaryTree() {}


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

    }
}

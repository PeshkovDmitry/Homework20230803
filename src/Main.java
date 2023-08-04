public class Main {
    public static void main(String[] args) {

        BinaryTree<Integer> myTree = new BinaryTree<>();

        myTree.add(5);
        myTree.add(9);
        myTree.add(8);
        myTree.add(7);
//        myTree.add(6);

        myTree.printTree();
        myTree.rebalance(myTree.get(9));
        myTree.printTree();





    }
}
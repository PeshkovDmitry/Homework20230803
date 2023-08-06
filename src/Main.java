import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Tree<Integer> myTree = new BinaryTree<>();

        for (int i = 0; i < 20; i++) {
            myTree.add(new Random().nextInt(20));
        }


//        myTree.add(15);
//        myTree.add(9);
//        myTree.add(8);
//        myTree.add(17);
//        myTree.add(6);



        BinaryTreePrinter printer = new BinaryTreePrinter();
        printer.print(myTree);

        myTree.printTree();




    }
}
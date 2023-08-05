import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePrinter<T extends Comparable<T>> implements Printer{



    public BinaryTreePrinter(Tree tree) {
        List<Pair> list = getFullTreeAsList(tree);

        System.out.println(list);
    }

    /**
     * Метод, возвращающий дерево в виде последовательного списка
     * Последовательность повторяет просмотр по ширине
     * При этом отсутствующие узлы также добавляются в виде пустого
     * экземпляра класса Pair
     * @param tree
     * @return
     */

    private List<Pair> getFullTreeAsList(Tree tree) {
        List<Pair> list = new ArrayList<>();
        Deque<Node<T>> deque = new LinkedList<>();
        deque.add(tree.getRoot());
        int valueCount = 1;
        boolean run = true;
        int depth = depth(tree.getRoot(),0);
        while (deque.size() > 0 && run) {
            Node<T> currentNode = deque.pollFirst();
            Pair pair = new Pair();
            if (currentNode != null) {
                valueCount++;
                pair = new Pair(currentNode.getValue().toString(), currentNode.getColor());
                deque.add(currentNode.getLeftChild());
                deque.add(currentNode.getRightChild());
            }
            else {
                deque.add(null);
                deque.add(null);
            }
            list.add(pair);
            if (Math.pow(2, depth) == list.size() + 1)
                run = false;
        }
        return list;
    }

    /**
     * Метод для получения глубины дерева
     * @param node
     * @param currentDepth
     * @return
     */
    private int depth(Node<T> node, int currentDepth) {
        if (node == null)
            return currentDepth;
        if (node.getLeftChild() == null && node.getRightChild() == null)
            return currentDepth + 1;
        return currentDepth + 1 +
                Math.max(
                    depth(node.getLeftChild(), currentDepth),
                    depth(node.getRightChild(), currentDepth)
                );
    }

    @Override
    public void print(Tree tree) {

    }

    private class Pair {

        private String text;

        private Color color;

        public Pair() {}

        public Pair(String text, Color color) {
            this.text = text;
            this.color = color;
        }

        public String getText() {
            return text;
        }

        public Color getColor() {
            return color;
        }

        public boolean isEmpty() {
            return text == null && color == null;
        }

        @Override
        public String toString() {
            String out = "";
            if (color == Color.RED)
                out = ANSI_RED + text + ANSI_RESET;
            else if (color == Color.BLACK)
                out = ANSI_BLACK + text + ANSI_RESET;
            else
                out = text;
            return out;
        }

        private String ANSI_RESET = "\u001B[0m";
        private String ANSI_BLACK = "\u001B[30m";
        private String ANSI_RED = "\u001B[31m";

    }

}

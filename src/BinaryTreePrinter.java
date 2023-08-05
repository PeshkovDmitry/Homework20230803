import java.util.*;

public class BinaryTreePrinter<T extends Comparable<T>> implements Printer{

    @Override
    public void print(Tree tree) {
        List<String> list = getFullTreeAsList(tree);
        int maxValueLength = 0;
        for (String s: list) {
            if (s.replace(ANSI_RED,"").replace(ANSI_RESET,"").length() > maxValueLength) {
                maxValueLength = s.replace(ANSI_RED,"").replace(ANSI_RESET,"").length();
            }
        }
        maxValueLength += 2;
        int level = 0;
        int nextNewLine = 0;
        int depth = depth(tree.getRoot(),0);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(getFormattedString(list.get(i),(int) (maxValueLength * Math.pow(2, depth - level - 1))));
            if (i == nextNewLine) {
                System.out.println();
                level++;
                nextNewLine += Math.pow(2, level);
            }
        }
    }

    /**
     * Метод, возвращающий дерево в виде последовательного списка значений узлов.
     * Последовательность повторяет просмотр по ширине.
     * При этом отсутствующие узлы также добавляются в виде пустой строки.
     * @param tree
     * @return
     */

    private List<String> getFullTreeAsList(Tree tree) {
        List<String> list = new ArrayList<>();
        Deque<Node<T>> deque = new LinkedList<>();
        deque.add(tree.getRoot());
        int depth = depth(tree.getRoot(),0);
        boolean run = true;
        while (deque.size() > 0 && run) {
            Node<T> currentNode = deque.pollFirst();
            String text = "";
            if (currentNode != null) {
                deque.add(currentNode.getLeftChild());
                deque.add(currentNode.getRightChild());
                if (currentNode.getColor() == Color.RED) {
                    text = ANSI_RED + currentNode.getValue().toString() + ANSI_RESET;
                }
                else {
                    text = currentNode.getValue().toString();
                }
            }
            else {
                deque.add(null);
                deque.add(null);
            }
            list.add(text);
            run = (Math.pow(2, depth) != list.size() + 1);
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
        if (node == null) {
            return currentDepth;
        }
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return currentDepth + 1;
        }
        return currentDepth + 1 +
                Math.max(
                    depth(node.getLeftChild(), currentDepth),
                    depth(node.getRightChild(), currentDepth)
                );
    }

    private String getFormattedString(String text, int fieldsize) {
        StringBuilder sb = new StringBuilder();
        if (text == "") {
            for (int i = 0; i < fieldsize; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        String txt = text
                .replace(ANSI_RED,"")
                .replace(ANSI_RESET,"");
        if (fieldsize == txt.length() + 2) {
            return " " + text + " ";
        }
        int spaceBefore = (fieldsize - txt.length()) / 2;
        int spaceAfter = fieldsize - txt.length() - spaceBefore;
        int spaceBefore1 = spaceBefore / 2;
        int spaceBefore2 = spaceBefore - spaceBefore1 - 1;
        int spaceAfter1 = spaceAfter / 2;
        int spaceAfter2 = spaceAfter - spaceAfter1 - 1;
        for (int i = 0; i < spaceBefore1; i++) {
            sb.append(" ");
        }
        sb.append("┌");
        for (int i = 0; i < spaceBefore2; i++) {
            sb.append("-");
        }
        sb.append(text);
        for (int i = 0; i < spaceAfter1; i++) {
            sb.append("-");
        }
        sb.append("┐");
        for (int i = 0; i < spaceAfter2; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_RED = "\u001B[31m";

}

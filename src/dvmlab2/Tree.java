package dvmlab2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Darya
 */
public class Tree {
    Node top;
    
    List<Node> allNodes;
    List<Integer> records;
    
    public Tree(int [][] sourceMatrix) {
        records = new ArrayList<>();
        allNodes = new ArrayList<>();
        top = createNode(sourceMatrix);
    }
    
    private Node createNode(int[][] matrix) {
        //осуществлять преобразования с матрицей и ее конечную оценку
        Node node = new Node(matrix, MatrixUtils.matrixEstimate(matrix));
        allNodes.add(node);
        return node;
    }
    
    public void startAlgorithm(Node top) {
        //расчет начинается с головного элемента
        top.processNode();
        int[][] matrix = top.getMatrix();
        int row = top.iRow;
        int col = top.jCol;
        //если оценка вышла больше зафиксированного рекорда,закрываем ветку
        System.out.println(top.getEstimate());
        
        if (matrix.length > 1) {
            //берем элемент {row,col}
            top.left = createNode(MatrixUtils.matrixResize(row, col, matrix));
            top.left.processNode();
            top.left.isReduced = true;
            startAlgorithm(top.left);
            //исключаем элемент {row,col}
            top.right = createNode(MatrixUtils.matrixMarkElement(row, col, matrix));
            top.right.processNode();
            top.right.isReduced = false;
            startAlgorithm(top.right);
        }
        else {
            top.isRecord = true;
            records.add(top.getEstimate()); //фиксируем рекорд
            System.out.println("COMPLETE");
        }
    }
    
    //процедура возращает узел с минимальной оценкой
    Node findNextNodeWithMinEstimate() {
       Iterator<Node> iterator = allNodes.iterator();
       Node current;
       Node min = top;
       while(iterator.hasNext()) {
           current = iterator.next();
           if (current.IsOpen() && current.getEstimate() < min.getEstimate()) {
               min = current;
           }
       }
        /*return Collections.min(allNodes, (Node o1, Node o2) -> 
                o1.getEstimate() - o2.getEstimate());*/
       return min;
    }
}

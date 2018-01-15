package dvmlab2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Darya
 */
public class Tree {
    /*Node top;
    
    List<Node> allNodes;
    List<Integer> records;
    
    public Tree(int [][] sourceMatrix) {
        records = new ArrayList<>();
        allNodes = new ArrayList<>();
        top = createNode(sourceMatrix);
        top.parentEstimate = 0;
    }
    
    private Node createNode(int[][] matrix) {
        //осуществлять преобразования с матрицей и ее конечную оценку
        Node node = new Node(matrix, MatrixUtils.matrixEstimate(matrix));
        allNodes.add(node);
        return node;
    }
    String format1 = "(%d,%d)";
    
    int R = Integer.MAX_VALUE;
    
    public void startAlgorithm(Node top) {
        //расчет начинается с головного элемента
        top.processNode();
        /*if (Math.abs(top.getEstimate() - Integer.MIN_VALUE) < 1) {
            top = null; //пути нет
            return;
        }*/
        /*int[][] matrix = top.getMatrix();
        int row = top.iRow;
        int col = top.jCol;
        if (matrix.length > 1 /*&& row !=-1 && col != -1*//*) {*/
            //берем элемент {row,col}
            /*top.left = createNode(MatrixUtils.matrixResize(row, col, matrix));
            top.left.parentEstimate = top.getEstimate();
            top.left.processNode();
            
            top.left.isReduced = true;
            startAlgorithm(top.left);
            //исключаем элемент {row,col}
            top.right = createNode(MatrixUtils.matrixMarkElement(row, col, matrix));
            top.right.parentEstimate = top.getEstimate();
            top.right.processNode();
            top.right.isReduced = false;
            startAlgorithm(top.right);
        }*/
        //последнии стадии алгоритма
       /* else  {
            if (top.getEstimate() >= R) {
                top = null;
                return;
            }
            else {
                R = top.getEstimate();
                top.isRecord = true;
                records.add(top.getEstimate()); //фиксируем рекорд
                //printMatrix(matrix);
                System.out.printf("Рекорд %d\n", top.getEstimate());
            }
        }
    }*/
    
    //процедура возращает узел с минимальной оценкой
    /*Node findNextNodeWithMinEstimate() {
       Iterator<Node> iterator = allNodes.iterator();
       Node current;
       Node min = top;
       while(iterator.hasNext()) {
           current = iterator.next();
           if (current.IsOpen() && current.getEstimate() < min.getEstimate()) {
               min = current;
           }
       }*/
        /*return Collections.min(allNodes, (Node o1, Node o2) -> 
                o1.getEstimate() - o2.getEstimate());*/
       /*return min;
    }
    private void printMatrix(int[][] matrix) {
        for(int i=0; i < matrix.length;i++){
            for(int j=0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }*/
}

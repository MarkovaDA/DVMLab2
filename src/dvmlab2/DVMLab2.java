
package dvmlab2;

/**
 * Задача коммивояжера
 * @author Darya
 */
public class DVMLab2 {

    public static void main(String[] args) {
        // TODO code application logic here
        int[][] matrix = {
            /*{-1,5,3,2,4,5,2,7}, 
            {7,-1,1,6,7,8,9,6}, 
            {3,2,-1,3,2,4,5,7},
            {4,6,2,-1,1,3,6,2},
            {4,5,1,2,-1,4,2,3},
            {3,8,9,6,4,-1,1,5},
            {1,6,6,4,2,1,-1,0},
            {7,8,7,2,8,3,0,-1}*/
            {-1,5,3,2,4},
            {2,-1,4,3,7},
            {3,0,-1,5,1},
            {7,8,6,-1,3},
            {1,5,3,4,-1}
        };
        //int value = MatrixUtils.matrixEstimate(matrix);
        //int[][] matrixRes = MatrixUtils.matrixResize(2, 3, matrix);
        //int[][] newMatrix = MatrixUtils.reduction(matrixRes);  
        //int elemValue = MatrixUtils.elemEstimate(3, 0, newMatrix);
        //matrix = MatrixUtils.reduction(matrix);
        Tree tree = new Tree(matrix);
        tree.startAlgorithm(tree.top);
    }
}

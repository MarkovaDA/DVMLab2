/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvmlab2;

/**
 *
 * @author Darya
 */
public class MatrixUtils {
    static int min(int[] arr) {
        int min = arr[0];
        for(int i=1; i < arr.length; i++) {
            if (arr[i] < min)
                min=arr[i];
        }
        return min;
    }  
    //min элемент в каждой строке
    public static int[] minInRows(int[][] matrix) {
        int size = matrix.length;
        int[] res = new int[size];
        for(int i=0; i < size;i++) {
            res[i] = min(matrix[i]);
        }
        return res;
    }
    //min элемент в каждом столбце
    public static int[] minInCols(int[][] matrix) {
        int size = matrix.length;
        int[] res = new int[size];
        int min, j = 0;//,j = 0;
        for(j=0; j < size; j++) {
            int i = 0;
            min = matrix[i][j];
            for(i=0; i < size; i++) {
                if (matrix[i][j] < min)
                    min = matrix[i][j];
            }
            res[j] = min;
        }
        return res;
    }
    //приведение матрицы (по строкам и столбцам)
    public static int[][] reduction(int[][] matrix) {
        int[] inRows = minInRows(matrix);
        int size = matrix.length;
        
        for(int i=0; i < size; i++) {
            for(int j=0; j < size; j++){
                matrix[i][j] -= inRows[i];
            }
        }
        int[] inCols = minInCols(matrix);
        
        for(int i=0; i < size; i++) {
            for(int j=0; j < size; j++){
                matrix[i][j] -= inCols[i];
            }
        }
        return matrix;    
    }
    
    //расчет оценки для элемента матрицы (min-элемент в строке + min-элемент в столбце)
    static int elemEstimate(int row, int col, int[][] matrix) {
        int i;
        int size = matrix.length;
        int minCol = Integer.MAX_VALUE;
        int minRow = Integer.MAX_VALUE;
        
        for(i=0; i < size; i++) {
            if (i != row && matrix[i][col] < minRow)
                minRow = matrix[i][col];
            if (i != col && matrix[row][i] < minCol)
                minCol = matrix[row][i];
        }
        return minCol + minRow;
    }
    
    //выбор нуля с наибольшей оценкой
    /*
    *Returns: индекс строки и столбца
    */
    public static int[] findZero(int[][] matrix) {
        int minZeroEstimate = Integer.MAX_VALUE;
        int minRow = -1;
        int minCol = -1;
        
        int size = matrix.length;
        
        for(int i=0; i < size; i++) {
            for(int j=0; j < size; j++) {
                if (matrix[i][j] == 0 && matrix[i][j] < minZeroEstimate) {
                    minZeroEstimate = matrix[i][j];
                    minRow = i;
                    minCol = j;
                }
            }
        }
        int[] indexes = new int[]{minRow, minCol};
        return indexes;
    }
    
    //изъятие элемента из матрицы - "схлопывание матрицы"
    public static int[][] matrixResize(int row, int col, int[][] matrix) {
        int size = matrix.length;
        int[][] newMatrix = new int[size - 1][size - 1];
        
        for(int i = 0; i < size; i++) {
            int rowIndexSummand = i < row ? 0 : -1; 
            for(int j = 0; j < size; j++) {
                if(j < col && i != row) {
                    newMatrix[i + rowIndexSummand][j] = matrix[i][j];
                } else if(j > col && i != row) {
                    newMatrix[i + rowIndexSummand][j - 1] = matrix[i][j];
                }
            }
        }
        return newMatrix;
    }
    
    //расчет оценки матрицы - сумма оценок строк и столбцов
    public static int matrixEstimate(int[][] matrix) {
        int[] minInRows = minInRows(matrix);
        int[] minInCols = minInCols(matrix);
        int size = matrix.length;
        int sum = 0;
        for(int i =0; i < size; i++) {
            sum += (minInRows[i] + minInCols[i]);
        }
        return sum;
    }
    
}

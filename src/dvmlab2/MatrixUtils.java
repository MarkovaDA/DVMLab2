package dvmlab2;

/**
 * @author Darya
 */
public class MatrixUtils {
    //!!!диагональные элементы рассматрению не подлежат
    //диагональные элементы отмечаются -1
    static int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for(int i=0; i < arr.length; i++) {
            if (arr[i] != -1 && arr[i] < min)
                min=arr[i];
        }
        return min;
    }  
    //min элемент в каждой строке
    public static int[] minInRows(int[][] matrix) {
        int size = matrix.length;
        int[] res = new int[size];
        for(int i=0; i < size; i++) {
            res[i] = min(matrix[i]);
        }
        return res;
    }
    //min элемент в каждом столбце
    public static int[] minInCols(int[][] matrix) {
        int size = matrix.length;
        int[] res = new int[size];
        int min, j;
        for(j=0; j < size; j++) {
            int i = 0;
            min = Integer.MAX_VALUE;
            for(i=0; i < size; i++) {
                if (matrix[i][j] != -1 && matrix[i][j] < min)
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
            for(int j=0; j < size; j++) {
                if (matrix[i][j] != -1)
                    matrix[i][j] -= inRows[i];
            }
        }
        
        int[] inCols = minInCols(matrix);
        
        for(int i=0; i < size; i++) {
            for(int j=0; j < size; j++) {
                if (matrix[i][j] != -1)
                    matrix[i][j] -= inCols[j];
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
            if (i != row && matrix[i][col] != -1 && matrix[i][col] < minRow)
                minRow = matrix[i][col];
            
            if (i != col && matrix[row][i] != -1 && matrix[row][i] < minCol)
                minCol = matrix[row][i];
        }
        return minCol + minRow;
    }
    
    //выбор нуля с наибольшей оценкой
    /*
    *Returns: индекс строки и столбца
    */
    public static int[] findZero(int[][] matrix) {
        int maxZeroEstimate = Integer.MIN_VALUE;
        int minRow = -1;
        int minCol = -1;
        
        int size = matrix.length;
        
        for(int i=0; i < size; i++) {
            for(int j=0; j < size; j++) {
                if (matrix[i][j] == 0 && matrix[i][j] != -1 && matrix[i][j] > maxZeroEstimate) {
                    maxZeroEstimate = matrix[i][j];
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
        return setDiagonalsUnavailable(newMatrix);        
    }
    
    private static int[][] setDiagonalsUnavailable(int[][] matrix) {
        int size = matrix.length;
        for(int i=0; i < size; i++) {
            matrix[i][i] = -1;
        }
        return matrix;
    }
    
    public static int[][] matrixMarkElement(int row, int col, int[][] matrix) {
        matrix[row][col] = -1;
        return matrix;
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

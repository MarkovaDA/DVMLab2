package dvmlab2;

/**
 * @author Darya
 */
public class MatrixUtils {
    //!!!диагональные элементы рассматрению не подлежат
    //диагональные элементы отмечаются -1
    static Integer min(Integer[] arr) {
        Integer min = -1;
        for(Integer i=0; i < arr.length; i++) {
            if (arr[i] != -1 && (arr[i] < min || min == -1))
                min=arr[i];
        }
        return min >= 0 ? min : 0;
    }  
    //min элемент в каждой строке
    public static Integer[] minInRows(Integer[][] matrix) {
        Integer size = matrix.length;
        Integer[] res = new Integer[size];
        for(Integer i=0; i < size; i++) {
            res[i] = min(matrix[i]);
        }
        return res;
    }
    //min элемент в каждом столбце
    public static Integer[] minInCols(Integer[][] matrix) {
        Integer size = matrix.length;
        Integer[] res = new Integer[size];
        Integer min, j;
        for(j=0; j < size; j++) {
            Integer i = 0;
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
    public static Integer[][] reduction(Integer[][] matrix) {
        Integer[] inRows = minInRows(matrix);
        Integer size = matrix.length;
        
        for(Integer i=0; i < size; i++) {
            for(Integer j=0; j < size; j++) {
                if (matrix[i][j] != -1)
                    matrix[i][j] -= inRows[i];
            }
        }
        
        Integer[] inCols = minInCols(matrix);
        
        for(Integer i=0; i < size; i++) {
            for(Integer j=0; j < size; j++) {
                if (matrix[i][j] != -1)
                    matrix[i][j] -= inCols[j];
            }
        }
        return matrix;    
    }
    
    //расчет оценки для элемента матрицы (min-элемент в строке + min-элемент в столбце)
    static Integer elemEstimate(Integer row, Integer col, Integer[][] matrix) {
        Integer i;
        Integer size = matrix.length;
        Integer minCol = Integer.MAX_VALUE;
        Integer minRow = Integer.MAX_VALUE;
        
        for(i=0; i < size; i++) {
            if (i != row && matrix[i][col] >= 0 && matrix[i][col] < minRow)
                minRow = matrix[i][col];
            
            if (i != col && matrix[row][i] >= 0 && matrix[row][i] < minCol)
                minCol = matrix[row][i];
        }
        return minCol + minRow;
    }
    
    //выбор нуля с наибольшей оценкой
    /*
    *Returns: индекс строки и столбца
    */
    public static Integer[] findZero(Integer[][] matrix) {
        Integer maxZeroEstimate = Integer.MIN_VALUE;
        Integer minRow = -1;
        Integer minCol = -1;
        
        Integer size = matrix.length;
        Integer estimate;
        for(Integer i=0; i < size; i++) {
            for(Integer j=0; j < size; j++) {
                if (matrix[i][j] == 0) {
                    estimate = elemEstimate(i,j, matrix);
                    if (estimate > maxZeroEstimate) {
                        maxZeroEstimate = estimate;
                        minRow = i;
                        minCol = j;
                    }
                }
                /*if (matrix[i][j] == 0 && matrix[i][j] > maxZeroEstimate) {
                    maxZeroEstimate = matrix[i][j];
                    minRow = i;
                    minCol = j;
                }*/
            }
        }
        Integer[] indexes = new Integer[]{minRow, minCol};
        return indexes;
    }
    
    //изъятие элемента из матрицы - "схлопывание матрицы"
    public static Integer[][] matrixResize(Integer row, Integer col, Integer[][] matrix) {
        Integer size = matrix.length;
        Integer[][] newMatrix = new Integer[size - 1][size - 1];
        
        for(Integer i = 0; i < size; i++) {
            Integer rowIndexSummand = i < row ? 0 : -1; 
            for(Integer j = 0; j < size; j++) {
                if(j < col && !i.equals(row)) {
                    newMatrix[i + rowIndexSummand][j] = matrix[i][j];
                } else if(j > col && !i.equals(row)) {
                    newMatrix[i + rowIndexSummand][j - 1] = matrix[i][j];
                }
            }
        }
        return setDiagonalsUnavailable(newMatrix);        
    }
    
    private static Integer[][] setDiagonalsUnavailable(Integer[][] matrix) {
        Integer size = matrix.length;
        for(Integer i=0; i < size; i++) {
            matrix[i][i] = -1;
        }
        return matrix;
    }
    
    public static Integer[][] matrixMarkElement(Integer row, Integer col, Integer[][] matrix) {
        matrix[row][col] = -1;
        return matrix;
    }
    
    //расчет оценки матрицы - сумма оценок строк и столбцов
    public static Integer matrixEstimate(Integer[][] matrix) {
        Integer[] minInRows = minInRows(matrix);
        Integer[] minInCols = minInCols(matrix);
        
        Integer size = matrix.length;
        Integer sum = 0;
        for(Integer i =0; i < size; i++) {
            sum += (minInRows[i] + minInCols[i]);
        }
        return sum;
    }
}

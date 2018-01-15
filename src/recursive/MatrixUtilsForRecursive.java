/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursive;

import static dvmlab2.MatrixUtils.minInRows;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vitaliy
 */
public class MatrixUtilsForRecursive {
    //приведение матрицы (по строкам)
    public static Integer reduction(Integer[][] matrix) {
        Integer[] inRows = minInRows(matrix);
        Integer size = matrix.length;
        Integer sum = 0;
        for(Integer i=0; i < size; i++) {
            sum += inRows[i];
            for(Integer j=0; j < size; j++) {
                if (matrix[i][j] >= 0)
                    matrix[i][j] -= inRows[i];
            }
        }
        return sum;
    }
    
    public static Integer[][] excludeRow(Integer[][] matr, Integer rowIndex) {
        for (Integer j = 0; j < matr[0].length; j++) {
            matr[rowIndex][j] = -1;
        }
        return matr;
    }
    
    public static Integer[][] excludeCol(Integer[][] matr, Integer colIndex) {
        for (Integer i = 0; i < matr.length; i++) {
            matr[i][colIndex] = -1;
        }
        return matr;
    }
    
    public static Integer[][] excludeElement(Integer[][] matr, Integer rowIndex, Integer colIndex) {
        matr[rowIndex][colIndex] = -1;
        return matr;
    }
    
    public static Integer[][] excludeByPath(Integer[][] matr, List<Integer[]> path, Integer newPoint) {
        
        if (countNotExcluded(matr) < 2) {
            return matr;
        }
        
        List<Integer> cyclesForNewPoint = new ArrayList<>();
        if (path.size() < matr.length ) {
            for (Integer i = 0; i < path.size(); i++) {
                cyclesForNewPoint = (getCycleForPoint(/*newPoint*/path.get(i)[1], path));
                for (int j = 0; j < cyclesForNewPoint.size(); j++) {
                    matr = excludeElement(matr, path.get(i)[1], cyclesForNewPoint.get(j));
                }
            }
        }
        
        /*for (int i = 0; i < cyclesForNewPoint.size(); i++) {
            matr = excludeElement(matr, newPoint, cyclesForNewPoint.get(i));
        }*/
        return matr;
    }
    
    private static List<Integer> getCycleForPoint(Integer point, List<Integer[]> path) {
        List<Integer> endPoints = new ArrayList<>();
        for (Integer i = 0; i < path.size(); i++) {
            Integer current = path.get(i)[1];
            if (current.equals(point)) {
                endPoints.add(path.get(i)[0]);
                endPoints.addAll(getCycleForPoint(path.get(i)[0], path));
            }
        }
        return endPoints;
    }
    
    public static Integer countNotExcluded(Integer[][] matr) {
        Integer count = 0;
        for (Integer i = 0; i < matr.length; i++) {
            for (Integer j = 0; j < matr[0].length; j++) {
                if (matr[i][j] >= 0) {
                    count++;
                }
            }
        }
        return count;
    }
}

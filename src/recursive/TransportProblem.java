/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursive;

import dvmlab2.MatrixUtils;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

/**
 *
 * @author Vitaliy
 */
public class TransportProblem {
    
    Integer record;
    List<Integer[]> recordPath;

    public Integer getRecord() {
        return record;
    }

    List<Integer[][]> matrixes = new ArrayList<>();
    
    public List<Integer[]> getPath(Integer [][] matr) throws Exception {
        recordPath = new ArrayList<>();
        List<Integer[]> resultPath = new ArrayList<>();
        record = -1;
        Integer currentMark = recursive.MatrixUtilsForRecursive.reduction(matr);
        processMatrix(matr, resultPath, currentMark, 0);
        return recordPath;
    }

    private boolean processMatrix(Integer[][] matr, List<Integer[]> resultPath, Integer currentMark, Integer step) throws Exception {
        //int[][] savedMatrixCopy = matrixClone(matr);
        System.out.printf("Record is %d \n", currentMark);
        
        if (currentMark > record && record > 0) {
            return false;
        }
        
        Integer newCurrentMark = currentMark + recursive.MatrixUtilsForRecursive.reduction(matr);
        
        Integer[][] savedMatrixCopy = matrixClone(matr);
        if (matrixes.size() <= step/* && matrixes.get(step) == null*/) {
            matrixes.add(step, matr);
        }
        
        if (MatrixUtilsForRecursive.countNotExcluded(matr) < 2) {
            Integer[] zero = MatrixUtils.findZero(matr);
            if (zero[0] >= 0 && zero[1] >= 0) {
                matr = MatrixUtilsForRecursive.excludeElement(matr, zero[1], zero[0]);
                resultPath.add(zero);
            }
            if ((record < 0 || record > currentMark) && resultPath.size() >= matr.length - 1) {
                record = currentMark;
                recordPath = new ArrayList<>();
                recordPath.addAll(resultPath);
            }
            return true;
        }
        
        // for left branch
        Integer[][] leftMatr = matrixes.get(step);//savedMatrixCopymatrixClone(matr);
        List<Integer[]> leftPath = new ArrayList<>();
        leftPath.addAll(resultPath);
        Integer leftMark = newCurrentMark;
        Integer[] zero = MatrixUtils.findZero(matr);
        if (zero[0] >= 0 && zero[1] >= 0) {
            leftMatr = MatrixUtilsForRecursive.excludeCol(leftMatr, zero[1]);
            leftMatr = MatrixUtilsForRecursive.excludeRow(leftMatr, zero[0]);
        }
        leftPath.add(zero);
        MatrixUtilsForRecursive.excludeByPath(leftMatr, leftPath, zero[1]);
        if (MatrixUtilsForRecursive.countNotExcluded(leftMatr) < 2) {
            zero = MatrixUtils.findZero(leftMatr);
            if (zero[0] >= 0 && zero[1] >= 0) {
            //    leftPath.add(zero);   // Raskommentyrovat', esli ne hvatat dannyh v otvete
            }
        }
        boolean leftResult = processMatrix(matrixClone(leftMatr), leftPath, leftMark, step + 1);
        
        // for right branch
        Integer[][] rightMatr = matrixes.get(step);//savedMatrixCopy.clone();
        List<Integer[]> rightPath = new ArrayList<>();
        rightPath.addAll(resultPath);
        Integer rightMark = currentMark;
        if (zero[0] >= 0 && zero[1] >= 0) {
            rightMatr = MatrixUtilsForRecursive.excludeElement(rightMatr, zero[0], zero[1]);
        }
        boolean rightResult = processMatrix(matrixClone(rightMatr), rightPath, rightMark, step + 1);
        
        // return false if both unsuccessfull
        if (!leftResult && !rightResult) {
            return false;
        }
        
        // return left if only left successfull
        if (!rightResult && leftResult) {
            //matr = leftMatr.clone();
            resultPath = leftPath;
            currentMark = leftMark;
            return true;
        }
        
        // return right if only right successfull
        if (rightResult && !leftResult) {
            //matr = rightMatr.clone();
            resultPath = rightPath;
            currentMark = rightMark;
            return true;
        }
        
        // if both successfull
        if (rightResult && leftResult) {
            if (rightMark < leftMark) {
                //matr = rightMatr.clone();
                resultPath = rightPath;
                currentMark = rightMark;
                return true;
            } else {
                //matr = leftMatr.clone();
                resultPath = leftPath;
                currentMark = leftMark;
                return true;
            }
        }
        
        // if process is here then something get wrong
        throw new Exception("I'm a oak, I can't process smth");
        
    }    
    
    private static Integer[][] matrixClone(Integer[][] matrix) {
        Integer sizeRows = matrix.length;
        Integer sizeCols = matrix[0].length;
        Integer[][] copy = new Integer[sizeRows][sizeCols];
        
        for (int i = 0; i < sizeRows; i++) {
            for (int j = 0; j < sizeCols; j++) {
                copy[i][j] = new Integer(matrix[i][j].intValue());
            }
        }
        
        return copy;
    }
}

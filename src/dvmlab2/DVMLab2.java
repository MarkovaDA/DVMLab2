
package dvmlab2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import recursive.MatrixUtilsForRecursive;
import recursive.TransportProblem;
/**
 * Задача коммивояжера
 * @author Darya
 */
public class DVMLab2 {

    public static void main(String[] args) throws Exception {        
       Integer[][] matrix = {
           /*{-1, 4, 10, 13, 4, 8},
           {2, -1, 9, 7, 6, 7},
           {8, 5, -1, 5, 5, 9},
           {5, 8, 5, -1, 7, 10},
           {6, 4, 4, 9, -1, 4},
           {5, 1, 4, 8, 3, -1}*/
           {-1,5,3,2,4,5,2,7},
           {7,-1,1,6,7,8,9,6},
           {3,2,-1,3,2,4,5,7},
           {4,6,2,-1,1,3,6,2},
           {4,5,1,2,-1,4,2,3},
           {3,8,9,6,4,-1,1,5},
           {1,6,6,4,2,1,-1,0},
           {7,8,7,2,8,3,0,-1}
       };
       TransportProblem transport = new TransportProblem();
       List<Integer[]> path = transport.getPath(matrix);
       Iterator<Integer[]> iterator = path.iterator();
       Integer[] point;
       while(iterator.hasNext()) {
           point = iterator.next();
           System.out.printf("(%d %d) \n", point[0]+1,point[1]+1);
       }
       Integer record = transport.getRecord();
       System.out.printf("Record is %d \n", record);
//matrix = MatrixUtilsForRecursive.excludeByPath(matrix, testPath, 4);
    }
}


package dvmlab2;


public class Node  {
    /*private int estimate;
    private int[][] matrix;
    private boolean open; 
    
    //возможно стоит хранить решение (x, по которому ветвим)
    public Node(int[][] matrix, int estimate) {
        this.matrix = matrix;
        this.estimate = estimate;
        this.open = true;
    }

    public boolean IsOpen() {
        return open;
    }

    public void setIsOpen(boolean isOpen) {
        this.open = isOpen;
    }

    Node left;
    Node right;
    //координаты элемента, по которому велось ветвление
    int iRow;
    int jCol;
    boolean isRecord;//достигнут ли рекорд в этом узле?
    boolean isReduced; //данная матрица - результат исключения элемента {iRow,jCol}
    int parentEstimate; //оценка матрицы в родительском узле
    
    public void processNode() {
        //вычисляем оценку матрицы
        estimate = parentEstimate + 
                MatrixUtils.matrixEstimate(matrix);
        //выполняем приведение матрицы
        matrix = MatrixUtils.reduction(matrix);
        //выбираем ноль с наибольшей оценкой
        int[] coords = MatrixUtils.findZero(matrix);
        //запоминаем его координаты
        iRow = coords[0];
        jCol = coords[1];
    }
    
    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getEstimate() {
        return estimate;
    }*/
   
    //на очередном этапе для матрицы проводится редукция
    //считается оценка
    //вычисляется элемент для ветвления
    //алгоритм запускается для дочерних элементов
    //продумать алгоритм
}

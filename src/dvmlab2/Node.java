
package dvmlab2;


public class Node implements Comparable<Node> {
    private Integer estimate;

    public Integer getEstimate() {
        return estimate;
    }

    @Override
    public int compareTo(Node o) {
        return estimate.compareTo(o.estimate);
    } 
}

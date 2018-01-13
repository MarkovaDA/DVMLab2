/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvmlab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author Vitaliy
 */

public class Tree {
    List<Node> allNodes = new ArrayList<>();
    
    Node findNextNodeWithMinEstimate() {
        return Collections.min(allNodes, (Node o1, Node o2) -> 
                o1.getEstimate() - o2.getEstimate());
    }
}

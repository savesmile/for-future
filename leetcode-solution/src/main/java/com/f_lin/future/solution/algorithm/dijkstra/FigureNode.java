package com.f_lin.future.solution.algorithm.dijkstra;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author F_Lin fjl@jinzantech.com
 * @since 2019/6/19 14:25
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class  FigureNode {
    //节点id
    private Integer id;
    //父节点
    private FigureNode parent;
    //可达节点 以及距离
    private Map<FigureNode, Integer> reachableNodes;
    //距离起始点的距离
    private Integer awayFromStart = Integer.MAX_VALUE;


    public void addReachableNodes(FigureNode key, Integer val) {
        if (reachableNodes == null) reachableNodes = new HashMap<>();
        reachableNodes.put(key, val);
    }
}

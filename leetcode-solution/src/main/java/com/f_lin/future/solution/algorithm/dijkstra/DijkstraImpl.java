package com.f_lin.future.solution.algorithm.dijkstra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 狄克斯特拉算法
 *
 * @author F_Lin fjl@jinzantech.com
 * @since 2019/6/19 14:29
 */
public class DijkstraImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(DijkstraImpl.class);
    private static List<Integer> processed = new ArrayList<>();

    public static void main(String[] args) {

        FigureNode n1 = new FigureNode();
        n1.setId(1);
        FigureNode n2 = new FigureNode();
        n2.setId(2);
        FigureNode n3 = new FigureNode();
        n3.setId(3);
        FigureNode n4 = new FigureNode();
        n4.setId(4);
        n1.addReachableNodes(n2, 6);
        n1.addReachableNodes(n3, 2);

        n2.addReachableNodes(n3, 3);
        n2.addReachableNodes(n4, 1);

        n3.addReachableNodes(n2, 3);
        n3.addReachableNodes(n4, 5);

        n4.addReachableNodes(n2, 1);
        n4.addReachableNodes(n3, 5);


        Map<Integer, FigureNode> figureNodes = new HashMap<>();
        figureNodes.put(n1.getId(), n1);
        figureNodes.put(n2.getId(), n2);
        figureNodes.put(n3.getId(), n3);
        figureNodes.put(n4.getId(), n4);
        //数据初始化完毕。。。。

        computeNode(1, 4, figureNodes.get(1));
    }


    private static void computeNode(Integer startId,
                                    Integer endId,
                                    FigureNode figureNode) {
        //起点初始化
        if (startId.equals(figureNode.getId())) {
            //起始点距离起点距离为0
            figureNode.setAwayFromStart(0);
        }
        //结束
        if (endId.equals(figureNode.getId())) {
            StringBuilder route = new StringBuilder();
            FigureNode parent = figureNode;
            while (parent != null) {
                route.insert(0, parent.getId() + " -> ");
                parent = parent.getParent();
            }
            route.delete(route.lastIndexOf(" -> "), route.length());
            LOGGER.info("最短路径查找完成：id: {},总长：{}", route, figureNode.getAwayFromStart());
            return;
        }


        Map<FigureNode, Integer> neighbors = figureNode.getReachableNodes();


        /**
         * 计算邻居节点到起点的距离，，默认 {@link Integer.MAX_VALUE} 无穷大
         * 如果当前节点被已经被处理了则不再处理 ==> 兼容无向图
         */
        for (Map.Entry<FigureNode, Integer> entry : neighbors.entrySet()) {
            //当前节点离起点的距离
            FigureNode curNode = entry.getKey();
            if (processed.contains(curNode.getId())) {
                continue;
            }
            Integer awayFromStart = entry.getValue() + figureNode.getAwayFromStart();
            if (curNode.getAwayFromStart() > awayFromStart) {
                //更新节点相关信息
                boolean parentIsNull = curNode.getParent() == null;
                LOGGER.info("节点 {} 更新，parent: {} -> {}, distance: {} -> {}",
                        curNode.getId(),
                        parentIsNull ? "null" : curNode.getParent().getId(),
                        figureNode.getId(),
                        curNode.getAwayFromStart(),
                        awayFromStart);

                curNode.setParent(figureNode);
                curNode.setAwayFromStart(awayFromStart);
            }
        }
        processed.add(figureNode.getId());

        //选择离当前节点最近的一个节点为下一路由
        FigureNode awayFromMe = findMinAwayFromMe(figureNode.getReachableNodes());
        //递归处理节点
        computeNode(startId, endId, awayFromMe);

    }


    private static FigureNode findMinAwayFromMe(Map<FigureNode, Integer> reachableNodes) {
        Integer minAwayFromStart = Integer.MAX_VALUE;
        FigureNode minAwayFromStartNode = null;
        for (Map.Entry<FigureNode, Integer> entry : reachableNodes.entrySet()) {
            FigureNode curNode = entry.getKey();
            Integer awayFromPreNode = entry.getValue();
            if (minAwayFromStart > awayFromPreNode && !processed.contains(curNode.getId())) {
                minAwayFromStart = awayFromPreNode;
                minAwayFromStartNode = curNode;
            }
        }
        return minAwayFromStartNode;
    }

}

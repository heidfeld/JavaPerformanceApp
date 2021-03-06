package com.lukasz.mgr.generators.control.dijkstra;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.lukasz.mgr.api.dijkstra.Graph;
import com.lukasz.mgr.api.dijkstra.Node;

/**
 * Created by Lukasz Karmanski
 */
public class DijkstraGraphGenerator {

    public static Graph generate(Integer nodeCount) {
        Graph graph = new Graph();
        if(nodeCount < 1) {
            return graph;
        }
        List<Node> nodes = generateNodes(nodeCount);
        for(Node node : nodes) {
            Integer randomNodesCount = getDividedNodesCount(nodes.size());
            List<Node> randomNodes = getRandomNodes(node, nodes, randomNodesCount);
            randomNodes.stream().forEach(r -> node.addDestination(r, ThreadLocalRandom.current().nextInt(20)));
            graph.addNode(node);
        }
        return graph;
    }

    private static List<Node> generateNodes(Integer nodeCount) {
        return IntStream.range(0, nodeCount)
                .mapToObj(num -> new Node("Node_" + num))
                .collect(Collectors.toList());
    }

    private static List<Node> getRandomNodes(Node node, List<Node> nodes, Integer nodeCount) {
        List<Integer> randoms = IntStream.range(0, nodeCount)
                .mapToObj(num -> ThreadLocalRandom.current().nextInt(nodes.size() - 1))
                .collect(Collectors.toList());

        return randoms.stream().map(r -> nodes.get(r)).filter(n -> !n.equals(node)).collect(Collectors.toList());

    }

    private static Integer getDividedNodesCount(Integer nodeCount) {
        if(nodeCount < 5) {
            return 2;
        } else if (nodeCount < 15) {
            return 4;
        } else if (nodeCount < 100) {
            return 14;
        } else {
            return 500;
        }
    }



}

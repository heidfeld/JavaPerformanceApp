package com.lukasz.mgr.generators;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;
import com.lukasz.mgr.api.dijkstra.Graph;
import com.lukasz.mgr.api.dijkstra.Node;
import com.lukasz.mgr.generators.control.dijkstra.DijkstraGraphGenerator;

/**
 * Created by Lukasz Karmanski
 */
public class DijkstraGeneratorTest {

    @Test
    public void shouldGenerateGraph() {
        Graph graph = DijkstraGraphGenerator.generate(10);

        List<Node> nodes = graph.getNodes().stream()
                .filter(n -> n.getAdjacentNodes().size() > 1)
                .collect(Collectors.toList());

        Assert.assertNotNull(graph);
        Assert.assertEquals(10, graph.getSize());
        Assert.assertTrue(nodes.size() > 0);
    }

    @Test
    public void shouldGenerateBidirectionalPath() {
        Graph graph = DijkstraGraphGenerator.generate(10);
        List<Node> nodes = graph.getNodes().stream()
                .filter(n -> n.getAdjacentNodes().size() > 1)
                .collect(Collectors.toList());

        Node node1 = nodes.get(0);
        Map<Node, Integer> adjacentNodes1 = node1.getAdjacentNodes();
        Map.Entry<Node, Integer> firstNeighbourEntry = adjacentNodes1.entrySet().stream().findFirst().get();
        Node firstNeighbourNode = firstNeighbourEntry.getKey();

        Assert.assertNotNull(firstNeighbourNode);

        Node node = graph.getNodes().stream().filter(n -> firstNeighbourNode.equals(n)).findFirst().get();
        Integer neighbourDistance = node.getAdjacentNodes().get(node1);

        Assert.assertEquals(firstNeighbourEntry.getValue(), neighbourDistance);
    }

}

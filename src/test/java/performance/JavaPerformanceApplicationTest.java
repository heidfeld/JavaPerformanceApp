package performance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import performance.control.algorithm.PerformanceAlgorithm;
import performance.control.algorithm.dijkstra.Dijkstra;
import performance.control.algorithm.dijkstra.Graph;
import performance.control.algorithm.dijkstra.Node;

/**
 * Created by Lukasz Karmanski
 */
public class JavaPerformanceApplicationTest {

    @Test
    public void testDijkstra() {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        Graph result = Dijkstra.calculateShortestPathFromSource(graph, nodeA);
        result.toString();

        Assert.assertEquals(Integer.valueOf(24), result.getNode("E").getDistance());
        Assert.assertEquals(Integer.valueOf(23), result.getNode("F").getDistance());
        Assert.assertEquals(Integer.valueOf(0), result.getNode("A").getDistance());
        Assert.assertEquals(6L, result.countNodes());


    }

}

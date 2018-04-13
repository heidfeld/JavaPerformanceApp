package performance;

import org.junit.Assert;
import org.junit.Test;
import performance.control.algorithm.dijkstra.DijkstraGraphGenerator;
import performance.control.algorithm.dijkstra.Graph;

/**
 * Created by Lukasz Karmanski
 */
public class DijkstraGeneratorTest {

    @Test
    public void graphGeneratorTest() {
        Graph graph = DijkstraGraphGenerator.generate(100);

        Assert.assertNotNull(graph);
        Assert.assertEquals(100, graph.getSize());
    }

}

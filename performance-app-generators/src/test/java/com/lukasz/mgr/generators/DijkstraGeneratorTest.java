package com.lukasz.mgr.generators;

import org.junit.Assert;
import org.junit.Test;
import com.lukasz.mgr.api.dijkstra.Graph;
import com.lukasz.mgr.generators.control.dijkstra.DijkstraGraphGenerator;

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

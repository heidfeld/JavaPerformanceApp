package performance.control.algorithm;

import performance.control.algorithm.dijkstra.Dijkstra;
import performance.control.algorithm.dijkstra.Graph;
import performance.control.algorithm.dijkstra.Node;
import performance.control.algorithm.result.DijkstraResult;
import performance.db.MongoDatabaseService;

import javax.inject.Inject;

/**
 * Created by Lukasz Karmanski
 */
public class PerformanceAlgorithm {

    @Inject
    private MongoDatabaseService mongoDatabaseService;

    public DijkstraResult runDijkstraAlgorithm(String collectionName) {
        long dbStart = System.currentTimeMillis();
        Graph baseGraph = mongoDatabaseService.getDijkstraGraph(collectionName);
        long dbEnd = System.currentTimeMillis();
        Node randomNode = baseGraph.getRandomNode();
        long algoStart = System.currentTimeMillis();
        Graph resultGraph = Dijkstra.calculateShortestPathFromSource(baseGraph, randomNode);
        long algoTime = System.currentTimeMillis() - algoStart;
        long dbTime = dbEnd - dbStart;

        DijkstraResult result = new DijkstraResult();
        result.setAlgorithmName("Dijkstra Shortest Nodes Algorithm");
        result.setAlhorithmTime(algoTime);
        result.setDbTime(dbTime);
        result.setNodeSize(resultGraph.getSize());
        result.setNode(randomNode);
        resultGraph.getNodes().stream().forEach(n -> result.putDistance(n.getName(), n.getDistance()));
        return result;
    }

}

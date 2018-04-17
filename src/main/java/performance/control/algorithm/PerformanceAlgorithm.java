package performance.control.algorithm;

import performance.control.algorithm.dijkstra.Dijkstra;
import performance.control.algorithm.dijkstra.Graph;
import performance.control.algorithm.dijkstra.Node;
import performance.control.algorithm.dijkstra2.Graph2;
import performance.control.algorithm.result.Dijkstra2Result;
import performance.control.algorithm.result.DijkstraResult;
import performance.db.MongoDatabaseService;

import javax.inject.Inject;
import java.util.List;

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

    public Dijkstra2Result runDijkstra2Algorithm(String collectionName, String fromNode, String toNode) {
        long dbStart = System.currentTimeMillis();
        Graph2 graph = mongoDatabaseService.getDijkstra2Graph(collectionName);
        long dbEnd = System.currentTimeMillis();
        long algoStart = System.currentTimeMillis();
        List<String> shortestPath = graph.getShortestPath(fromNode, toNode);
        long algoTime = System.currentTimeMillis() - algoStart;
        long dbTime = dbEnd - dbStart;

        Dijkstra2Result result = new Dijkstra2Result();
        result.setAlgorithmName("Dijkstra 2 Shortest Nodes Algorithm");
        result.setAlhorithmTime(algoTime);
        result.setDbTime(dbTime);
        result.setSourceNode(fromNode);
        result.setDestinationNode(toNode);
        result.setShortestPath(shortestPath);
        return result;
    }

}

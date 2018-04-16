package performance.control.algorithm.result;

import performance.control.algorithm.dijkstra.Graph;
import performance.control.algorithm.dijkstra.Node;

public class DijkstraResult extends AlgorithmResult {

    private Graph graph;
    private Node node;
    private Integer nodeSize;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Integer getNodeSize() {
        return nodeSize;
    }

    public void setNodeSize(Integer nodeSize) {
        this.nodeSize = nodeSize;
    }
}

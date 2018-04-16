package performance.control.algorithm.result;

import performance.control.algorithm.dijkstra.Node;

import java.util.HashMap;
import java.util.Map;

public class DijkstraResult extends AlgorithmResult {

    private Map<String, Integer> distancesMap = new HashMap<>();
    private Node node;
    private Integer nodeSize;

    public Map<String, Integer> getDistancesMap() {
        return distancesMap;
    }

    public void putDistance(String nodeName, Integer distance) {
        this.distancesMap.put(nodeName, distance);
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

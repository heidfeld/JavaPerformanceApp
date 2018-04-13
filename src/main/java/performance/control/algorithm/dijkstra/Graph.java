package performance.control.algorithm.dijkstra;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lukasz Karmanski
 */
public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Node getNode(Node node) {
        return nodes.stream()
                .filter(n -> n.equals(node))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Graph doesn't contain specified node."));
    }

    public Node getNode(String nodeName) {
        return nodes.stream()
                .filter(n -> n.getName().equals(nodeName))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Graph doesn't contain specified node."));
    }

    public long countNodes() {
        return nodes.stream().count();

    }

    public int getSize() {
        return nodes.size();
    }
}

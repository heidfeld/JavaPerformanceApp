package com.lukasz.mgr.performance.control.algorithm.result;

import java.util.List;

public class Dijkstra2Result extends AlgorithmResult {

    private String sourceNode;
    private String destinationNode;
    private List<String> shortestPath;

    public String getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(String sourceNode) {
        this.sourceNode = sourceNode;
    }

    public String getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(String destinationNode) {
        this.destinationNode = destinationNode;
    }

    public List<String> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<String> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public String toString() {
        return "Algorithm Result{" +
                "algorithmName='" + algorithmName + '\'' +
                "alhorithmTime='" + alhorithmTime + '\'' +
                "dbTime='" + dbTime + '\'' +
                '}';
    }
}

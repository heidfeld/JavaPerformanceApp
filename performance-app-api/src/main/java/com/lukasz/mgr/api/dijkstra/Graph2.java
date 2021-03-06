package com.lukasz.mgr.api.dijkstra;

import java.util.*;

public class Graph2 {

    private final Map<String, List<Vertex2>> vertices;
    private long iterations = 0;

    public Graph2() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(String character, List<Vertex2> vertex) {
        this.vertices.put(character, vertex);
    }

    public long getIterations() {
        return this.iterations;
    }

    public List<String> getShortestPath(String start, String finish) {
        final Map<String, Integer> distances = new HashMap<>();
        final Map<String, Vertex2> previous = new HashMap<>();
        PriorityQueue<Vertex2> nodes = new PriorityQueue<>();

        for(String vertex : vertices.keySet()) {
            iterations = iterations + 1;
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
                nodes.add(new Vertex2(vertex, 0));
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                nodes.add(new Vertex2(vertex, Integer.MAX_VALUE));
            }
            previous.put(vertex, null);
        }

        while (!nodes.isEmpty()) {
            iterations = iterations + 1;
            Vertex2 smallest = nodes.poll();
            if (smallest.getId().equals(finish)) {
                final List<String> path = new ArrayList<>();
                while (previous.get(smallest.getId()) != null) {
                    iterations = iterations + 1;
                    path.add(smallest.getId());
                    smallest = previous.get(smallest.getId());
                }
                return path;
            }

            if (distances.get(smallest.getId()).equals(Integer.MAX_VALUE)) {
                break;
            }

            for (Vertex2 neighbor : vertices.get(smallest.getId())) {
                iterations = iterations + 1;
                Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
                if (alt < distances.get(neighbor.getId())) {
                    distances.put(neighbor.getId(), alt);
                    previous.put(neighbor.getId(), smallest);

                    for(Vertex2 n : nodes) {
                        if (n.getId().equals(neighbor.getId())) {
                            nodes.remove(n);
                            n.setDistance(alt);
                            nodes.add(n);
                            break;
                        }
                    }
                }
            }
        }

        return new ArrayList<>(distances.keySet());
    }

}

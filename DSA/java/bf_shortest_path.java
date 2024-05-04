import java.util.Arrays;

public class bf_shortest_path {
    static class Graph {
        private int[][] adjMatrix;
        private String[] vertexData;
        private int size;

        public Graph(int size) {
            this.size = size;
            this.adjMatrix = new int[size][size];
            this.vertexData = new String[size];
        }

        public void addEdge(int u, int v, int weight) {
            if (u >= 0 && u < size && v >= 0 && v < size) {
                adjMatrix[u][v] = weight;
            }
        }

        public void addVertexData(int vertex, String data) {
            if (vertex >= 0 && vertex < size) {
                vertexData[vertex] = data;
            }
        }

        public Result bellmanFord(String startVertexData) {
            int startVertex = findVertex(startVertexData);
            int[] distances = new int[size];
            Integer[] predecessors = new Integer[size];
            Arrays.fill(distances, Integer.MAX_VALUE);
            Arrays.fill(predecessors, null);
            distances[startVertex] = 0;

            for (int i = 0; i < size - 1; i++) {
                for (int u = 0; u < size; u++) {
                    for (int v = 0; v < size; v++) {
                        if (adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE &&
                            distances[u] + adjMatrix[u][v] < distances[v]) {
                            distances[v] = distances[u] + adjMatrix[u][v];
                            predecessors[v] = u;
                            System.out.printf("Relaxing edge %s->%s, Updated distance to %s: %d%n",
                                              vertexData[u], vertexData[v], vertexData[v], distances[v]);
                        }
                    }
                }
            }

            for (int u = 0; u < size; u++) {
                for (int v = 0; v < size; v++) {
                    if (adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE &&
                        distances[u] + adjMatrix[u][v] < distances[v]) {
                        return new Result(true, null, null);
                    }
                }
            }

            return new Result(false, distances, predecessors);
        }

        private int findVertex(String vertexData) {
            for (int i = 0; i < this.vertexData.length; i++) {
                if (this.vertexData[i].equals(vertexData)) {
                    return i;
                }
            }
            return -1; // Vertex not found
        }

        public String getPath(Integer[] predecessors, String startVertex, String endVertex) {
            if (predecessors == null) {
                return "Path not available";
            }
        
            StringBuilder path = new StringBuilder();
            int current = findVertex(endVertex);
        
            // Handle cases where endVertex is not found or has no path
            if (current == -1 || predecessors[current] == null) {
                return "No path from " + startVertex + " to " + endVertex;
            }
        
            while (current != -1) {
                path.insert(0, this.vertexData[current]);
                Integer pred = predecessors[current];
                if (pred != null && pred != findVertex(startVertex)) {
                    path.insert(0, "->");
                    current = pred;
                } else {
                    if (pred != null) {
                        path.insert(0, startVertex + "->");
                    }
                    break;
                }
            }
            return path.toString();
        }        
    }

    static class Result {
        boolean hasNegativeCycle;
        int[] distances;
        Integer[] predecessors;

        public Result(boolean hasNegativeCycle, int[] distances, Integer[] predecessors) {
            this.hasNegativeCycle = hasNegativeCycle;
            this.distances = distances;
            this.predecessors = predecessors;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addVertexData(0, "A");
        g.addVertexData(1, "B");
        g.addVertexData(2, "C");
        g.addVertexData(3, "D");
        g.addVertexData(4, "E");
    
        g.addEdge(3, 0, 4);  // D -> A, weight 4
        g.addEdge(3, 2, 7);  // D -> C, weight 7
        g.addEdge(3, 4, 3);  // D -> E, weight 3
        g.addEdge(0, 2, 4);  // A -> C, weight 4
        g.addEdge(2, 0, -3); // C -> A, weight -3
        g.addEdge(0, 4, 5);  // A -> E, weight 5
        g.addEdge(4, 2, 3);  // E -> C, weight 3
        g.addEdge(1, 2, -4); // B -> C, weight -4
        g.addEdge(4, 1, 2);  // E -> B, weight 2
    
        System.out.println("\nThe Bellman-Ford Algorithm starting from vertex D:");
        Result result = g.bellmanFord("D");
        if (!result.hasNegativeCycle) {
            for (int i = 0; i < result.distances.length; i++) {
                if (result.distances[i] != Integer.MAX_VALUE) {
                    String startVertexData = "D"; // Start vertex as a string
                    String endVertexData = g.vertexData[i]; // Convert end vertex index to string
                    String path = g.getPath(result.predecessors, startVertexData, endVertexData);
                    System.out.println(path + ", Distance: " + result.distances[i]);
                } else {
                    System.out.println("No path from D to " + g.vertexData[i] + ", Distance: Infinity");
                }
            }
        } else {
            System.out.println("Negative weight cycle detected. Cannot compute shortest paths.");
        }
    }

}

//Java
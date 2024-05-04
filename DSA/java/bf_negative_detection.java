import java.util.Arrays;

public class bf_negative_detection {
    static class Graph {
        private int[][] adjMatrix;
        private String[] vertexData;
        private int size;

        public Graph(int size) {
            this.size = size;
            this.adjMatrix = new int[size][size];
            this.vertexData = new String[size];
            Arrays.fill(this.vertexData, ""); // Initialize with empty strings
        }

        public void addEdge(int u, int v, int weight) {
            if (0 <= u && u < size && 0 <= v && v < size) {
                adjMatrix[u][v] = weight;
            }
        }

        public void addVertexData(int vertex, String data) {
            if (0 <= vertex && vertex < size) {
                vertexData[vertex] = data;
            }
        }

        public Result bellmanFord(String startVertexData) {
            int startVertex = -1;
            for (int i = 0; i < size; i++) {
                if (vertexData[i].equals(startVertexData)) {
                    startVertex = i;
                    break;
                }
            }
            if (startVertex == -1) {
                return new Result(true, null); // Start vertex not found
            }

            int[] distances = new int[size];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[startVertex] = 0;

            for (int i = 0; i < size - 1; i++) {
                for (int u = 0; u < size; u++) {
                    for (int v = 0; v < size; v++) {
                        if (adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE &&
                            distances[u] + adjMatrix[u][v] < distances[v]) {
                            distances[v] = distances[u] + adjMatrix[u][v];
                            System.out.println("Relaxing edge " + vertexData[u] + "->" + vertexData[v] + ", Updated distance to " + vertexData[v] + ": " + distances[v]);
                        }
                    }
                }
            }

            // Check for negative weight cycle
            for (int u = 0; u < size; u++) {
                for (int v = 0; v < size; v++) {
                    if (adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE &&
                        distances[u] + adjMatrix[u][v] < distances[v]) {
                        return new Result(true, null);
                    }
                }
            }

            return new Result(false, distances);
        }
    }

    static class Result {
        boolean hasNegativeCycle;
        int[] distances;

        public Result(boolean hasNegativeCycle, int[] distances) {
            this.hasNegativeCycle = hasNegativeCycle;
            this.distances = distances;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addVertexData(0, "A");
        g.addVertexData(1, "B");
        g.addVertexData(2, "C");
        g.addVertexData(3, "D");
        g.addVertexData(4, "E");

        // Add edges
        g.addEdge(3, 0, 4);
        g.addEdge(3, 2, 7);
        g.addEdge(3, 4, 3);
        g.addEdge(0, 2, 4);
        g.addEdge(2, 0, -9);
        g.addEdge(0, 4, 5);
        g.addEdge(4, 2, 3);
        g.addEdge(1, 2, -4);
        g.addEdge(4, 1, 2);

        // Run Bellman-Ford algorithm
        System.out.println("\nThe Bellman-Ford Algorithm starting from vertex D:");
        Result result = g.bellmanFord("D");
        if (!result.hasNegativeCycle) {
            for (int i = 0; i < result.distances.length; i++) {
                System.out.println("Distance from D to " + g.vertexData[i] + ": " + result.distances[i]);
            }
        } else {
            System.out.println("Negative weight cycle detected. Cannot compute shortest paths.");
        }
    }
}

//Java
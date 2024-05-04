public class dj_graph_undirected {
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
                adjMatrix[v][u] = weight;  // For undirected graph
            }
        }

        public void addVertexData(int vertex, String data) {
            if (vertex >= 0 && vertex < size) {
                vertexData[vertex] = data;
            }
        }

        public int[] dijkstra(String startVertexData) {
            int startVertex = findIndex(startVertexData);
            int[] distances = new int[size];
            boolean[] visited = new boolean[size];

            for (int i = 0; i < size; i++) {
                distances[i] = Integer.MAX_VALUE;
            }
            distances[startVertex] = 0;

            for (int i = 0; i < size; i++) {
                int u = minDistance(distances, visited);
                if (u == -1) break;

                visited[u] = true;
                for (int v = 0; v < size; v++) {
                    if (!visited[v] && adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE) {
                        int newDist = distances[u] + adjMatrix[u][v];
                        if (newDist < distances[v]) {
                            distances[v] = newDist;
                        }
                    }
                }
            }
            return distances;
        }

        private int findIndex(String data) {
            for (int i = 0; i < size; i++) {
                if (vertexData[i].equals(data)) {
                    return i;
                }
            }
            return -1;
        }

        private int minDistance(int[] distances, boolean[] visited) {
            int min = Integer.MAX_VALUE, minIndex = -1;

            for (int v = 0; v < size; v++) {
                if (!visited[v] && distances[v] <= min) {
                    min = distances[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);

        g.addVertexData(0, "A");
        g.addVertexData(1, "B");
        g.addVertexData(2, "C");
        g.addVertexData(3, "D");
        g.addVertexData(4, "E");
        g.addVertexData(5, "F");
        g.addVertexData(6, "G");

        g.addEdge(3, 0, 4); // D - A, weight 4
        g.addEdge(3, 4, 2); // D - E, weight 2
        g.addEdge(0, 2, 3); // A - C, weight 3
        g.addEdge(0, 4, 4); // A - E, weight 4
        g.addEdge(4, 2, 4); // E - C, weight 4
        g.addEdge(4, 6, 5); // E - G, weight 5
        g.addEdge(2, 5, 5); // C - F, weight 5
        g.addEdge(2, 1, 2); // C - B, weight 2
        g.addEdge(1, 5, 2); // B - F, weight 2
        g.addEdge(6, 5, 5); // G - F, weight 5

        // Dijkstra's algorithm from D to all vertices
        System.out.println("Dijkstra's Algorithm starting from vertex D:\n");
        int[] distances = g.dijkstra("D");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Shortest distance from D to " + g.vertexData[i] + ": " + distances[i]);
        }
    }
}

//Java
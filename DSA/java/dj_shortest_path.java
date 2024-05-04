public class dj_shortest_path {
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
            if (0 <= u && u < size && 0 <= v && v < size) {
                adjMatrix[u][v] = weight;
                adjMatrix[v][u] = weight;  // For undirected graph
            }
        }

        public void addVertexData(int vertex, String data) {
            if (0 <= vertex && vertex < size) {
                vertexData[vertex] = data;
            }
        }

        public String getShortestPath(int startVertex, int endVertex) {
            int[] distances = new int[size];
            int[] predecessors = new int[size];
            boolean[] visited = new boolean[size];
            for (int i = 0; i < size; i++) {
                distances[i] = Integer.MAX_VALUE;
                predecessors[i] = -1;
            }
            distances[startVertex] = 0;

            for (int i = 0; i < size; i++) {
                int u = -1;
                for (int j = 0; j < size; j++) {
                    if (!visited[j] && (u == -1 || distances[j] < distances[u])) {
                        u = j;
                    }
                }

                visited[u] = true;

                for (int v = 0; v < size; v++) {
                    if (adjMatrix[u][v] != 0 && !visited[v]) {
                        int alt = distances[u] + adjMatrix[u][v];
                        if (alt < distances[v]) {
                            distances[v] = alt;
                            predecessors[v] = u;
                        }
                    }
                }
            }

            StringBuilder path = new StringBuilder();
            for (int at = endVertex; at != -1; at = predecessors[at]) {
                path.insert(0, vertexData[at] + (path.length() > 0 ? "->" : ""));
            }

            return path.toString() + ", Distance: " + distances[endVertex];
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

        System.out.println("Dijkstra's Algorithm starting from vertex D: \n");
        for (int i = 0; i < g.size; i++) {
            System.out.println(g.getShortestPath(3, i));
        }
    }
}

//Java
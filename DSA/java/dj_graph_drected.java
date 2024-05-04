public class dj_graph_drected {
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
                // Uncomment the line below for an undirected graph
                // adjMatrix[v][u] = weight;
            }
        }

        public void addVertexData(int vertex, String data) {
            if (0 <= vertex && vertex < size) {
                vertexData[vertex] = data;
            }
        }

        public int[] dijkstra(String startVertexData) {
            int startVertex = findVertexIndex(startVertexData);
            int[] distances = new int[size];
            boolean[] visited = new boolean[size];

            for (int i = 0; i < size; i++) {
                distances[i] = Integer.MAX_VALUE;
            }
            distances[startVertex] = 0;

            for (int i = 0; i < size; i++) {
                int u = findMinDistanceVertex(distances, visited);
                if (u == -1) break;

                visited[u] = true;

                for (int v = 0; v < size; v++) {
                    if (!visited[v] && adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE) {
                        int alt = distances[u] + adjMatrix[u][v];
                        if (alt < distances[v]) {
                            distances[v] = alt;
                        }
                    }
                }
            }
            return distances;
        }

        private int findVertexIndex(String data) {
            for (int i = 0; i < vertexData.length; i++) {
                if (vertexData[i].equals(data)) {
                    return i;
                }
            }
            return -1;
        }

        private int findMinDistanceVertex(int[] distances, boolean[] visited) {
            int minDistance = Integer.MAX_VALUE, minIndex = -1;
            for (int i = 0; i < size; i++) {
                if (!visited[i] && distances[i] < minDistance) {
                    minDistance = distances[i];
                    minIndex = i;
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

        g.addEdge(3, 0, 4); // D -> A, weight 4
        g.addEdge(3, 4, 2); // D -> E, weight 2
        g.addEdge(0, 2, 3); // A -> C, weight 3
        g.addEdge(0, 4, 4); // A -> E, weight 4
        g.addEdge(4, 2, 4); // E -> C, weight 4
        g.addEdge(4, 6, 5); // E -> G, weight 5
        g.addEdge(2, 5, 5); // C -> F, weight 5
        g.addEdge(1, 2, 2); // B -> C, weight 2
        g.addEdge(1, 5, 2); // B -> F, weight 2
        g.addEdge(6, 5, 5); // G -> F, weight 5

        // Dijkstra's algorithm from D to all vertices
        System.out.println("Dijkstra's Algorithm starting from vertex D:\n");
        int[] distances = g.dijkstra("D");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Shortest distance from D to " + g.vertexData[i] + ": " + distances[i]);
        }
    }
}

//Java
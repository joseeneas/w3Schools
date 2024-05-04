public class graph_dfs_bft {
    static class Graph {
        private int[][] adjMatrix;
        private String[] vertexData;
        private int size;

        public Graph(int size) {
            this.size = size;
            this.adjMatrix = new int[size][size];
            this.vertexData = new String[size];
        }

        public void addEdge(int u, int v) {
            if (u >= 0 && u < size && v >= 0 && v < size) {
                adjMatrix[u][v] = 1;
                // adjMatrix[v][u] = 1;
            }
        }

        public void addVertexData(int vertex, String data) {
            if (vertex >= 0 && vertex < size) {
                vertexData[vertex] = data;
            }
        }

        public void printGraph() {
            System.out.println("Adjacency Matrix:");
            for (int[] row : adjMatrix) {
                for (int val : row) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
            System.out.println("\nVertex Data:");
            for (int i = 0; i < size; i++) {
                System.out.println("Vertex " + i + ": " + vertexData[i]);
            }
        }

        private void dfsUtil(int v, boolean[] visited) {
            visited[v] = true;
            System.out.print(vertexData[v] + " ");

            for (int i = 0; i < size; i++) {
                if (adjMatrix[v][i] == 1 && !visited[i]) {
                    dfsUtil(i, visited);
                }
            }
        }

        public void dfs(String startVertexData) {
            boolean[] visited = new boolean[size];
            int startVertex = findVertexIndex(startVertexData);
            dfsUtil(startVertex, visited);
        }

        public void bfs(String startVertexData) {
            int[] queue = new int[size];
            int queueStart = 0, queueEnd = 0;
            boolean[] visited = new boolean[size];
            int startVertex = findVertexIndex(startVertexData);
            queue[queueEnd++] = startVertex;
            visited[startVertex] = true;

            while (queueStart < queueEnd) {
                int currentVertex = queue[queueStart++];
                System.out.print(vertexData[currentVertex] + " ");

                for (int i = 0; i < size; i++) {
                    if (adjMatrix[currentVertex][i] == 1 && !visited[i]) {
                        queue[queueEnd++] = i;
                        visited[i] = true;
                    }
                }
            }
        }

        private int findVertexIndex(String data) {
            for (int i = 0; i < size; i++) {
                if (vertexData[i].equals(data)) {
                    return i;
                }
            }
            return -1;
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

        g.addEdge(3, 0); // D -> A
        g.addEdge(3, 4); // D -> E
        g.addEdge(4, 0); // E -> A
        g.addEdge(0, 2); // A -> C
        g.addEdge(2, 5); // C -> F
        g.addEdge(2, 6); // C -> G
        g.addEdge(5, 1); // F -> B
        g.addEdge(1, 2); // B -> C

        g.printGraph();

        System.out.println("\nDepth First Search starting from vertex D:");
        g.dfs("D");

        System.out.println("\n\nBreadth First Search starting from vertex D:");
        g.bfs("D");
    }
}

//Java
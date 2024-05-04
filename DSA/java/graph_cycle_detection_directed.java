public class graph_cycle_detection_directed {

    static class Graph {
        private int[][] adjMatrix;
        private char[] vertexData;
        private int size;

        public Graph(int size) {
            this.size = size;
            this.adjMatrix = new int[size][size];
            this.vertexData = new char[size];
        }

        public void addEdge(int u, int v) {
            if (u >= 0 && u < size && v >= 0 && v < size) {
                adjMatrix[u][v] = 1;
            }
        }

        public void addVertexData(int vertex, char data) {
            if (vertex >= 0 && vertex < size) {
                vertexData[vertex] = data;
            }
        }

        public void printGraph() {
            System.out.println("Adjacency Matrix:");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(adjMatrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\nVertex Data:");
            for (int i = 0; i < size; i++) {
                System.out.println("Vertex " + i + ": " + vertexData[i]);
            }
        }

        private boolean dfsUtil(int v, boolean[] visited, boolean[] recStack) {
            visited[v] = true;
            recStack[v] = true;
            System.out.println("Current vertex: " + vertexData[v]);

            for (int i = 0; i < size; i++) {
                if (adjMatrix[v][i] == 1) {
                    if (!visited[i]) {
                        if (dfsUtil(i, visited, recStack)) {
                            return true;
                        }
                    } else if (recStack[i]) {
                        return true;
                    }
                }
            }

            recStack[v] = false;
            return false;
        }

        public boolean isCyclic() {
            boolean[] visited = new boolean[size];
            boolean[] recStack = new boolean[size];

            for (int i = 0; i < size; i++) {
                if (!visited[i]) {
                    System.out.println(); //new line
                    if (dfsUtil(i, visited, recStack)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);

        g.addVertexData(0, 'A');
        g.addVertexData(1, 'B');
        g.addVertexData(2, 'C');
        g.addVertexData(3, 'D');
        g.addVertexData(4, 'E');
        g.addVertexData(5, 'F');
        g.addVertexData(6, 'G');

        g.addEdge(3, 0); // D -> A
        g.addEdge(0, 2); // A -> C
        g.addEdge(2, 1); // C -> B
        g.addEdge(2, 4); // C -> E
        g.addEdge(1, 5); // B -> F
        g.addEdge(4, 0); // E -> A
        g.addEdge(2, 6); // C -> G

        g.printGraph();
        
        System.out.println("\nGraph has cycle: " + g.isCyclic());
    }
}


//Java


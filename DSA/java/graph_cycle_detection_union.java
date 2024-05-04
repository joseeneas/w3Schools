public class graph_cycle_detection_union{

    public static void main(String[] args) {
        Graph g = new Graph(7);

        g.addVertexData(0, 'A');
        g.addVertexData(1, 'B');
        g.addVertexData(2, 'C');
        g.addVertexData(3, 'D');
        g.addVertexData(4, 'E');
        g.addVertexData(5, 'F');
        g.addVertexData(6, 'G');

        g.addEdge(1, 0); // B - A
        g.addEdge(0, 3); // A - D
        g.addEdge(0, 2); // A - C
        g.addEdge(2, 3); // C - D
        g.addEdge(3, 4); // D - E
        g.addEdge(3, 5); // D - F
        g.addEdge(3, 6); // D - G
        g.addEdge(4, 5); // E - F

        System.out.println("Graph has cycle: " + g.isCyclic());
    }

    public static class Graph {
        private int[][] adjMatrix;
        private char[] vertexData;
        private int[] parent;
        private int size;
    
        public Graph(int size) {
            this.size = size;
            this.adjMatrix = new int[size][size];
            this.vertexData = new char[size];
            this.parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }
    
        public void addEdge(int u, int v) {
            if (u >= 0 && u < size && v >= 0 && v < size) {
                adjMatrix[u][v] = 1;
                adjMatrix[v][u] = 1;
            }
        }
    
        public void addVertexData(int vertex, char data) {
            if (vertex >= 0 && vertex < size) {
                vertexData[vertex] = data;
            }
        }
    
        private int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return find(parent[i]);
        }
    
        private void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
            System.out.println("Union: " + vertexData[x] + " + " + vertexData[y]);
            parent[xRoot] = yRoot;
            System.out.println(java.util.Arrays.toString(parent) + "\n");
        }
    
        public boolean isCyclic() {
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (adjMatrix[i][j] == 1) {
                        int x = find(i);
                        int y = find(j);
                        if (x == y) {
                            return true;
                        }
                        union(x, y);
                    }
                }
            }
            return false;
        }
    }
}

//Java
class Graph {
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
            adjMatrix[v][u] = 1;
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
}

public class graph_with_classes {
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addVertexData(0, 'A');
        g.addVertexData(1, 'B');
        g.addVertexData(2, 'C');
        g.addVertexData(3, 'D');
        g.addEdge(0, 1);  // A - B
        g.addEdge(0, 2);  // A - C
        g.addEdge(0, 3);  // A - D
        g.addEdge(1, 2);  // B - C

        g.printGraph();
    }
}

//Java
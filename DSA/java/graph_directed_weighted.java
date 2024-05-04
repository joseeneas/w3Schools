class Graph {
    private Integer[][] adjMatrix;
    private char[] vertexData;
    private int size;

    public Graph(int size) {
        this.size = size;
        this.adjMatrix = new Integer[size][size];
        this.vertexData = new char[size];
    }

    public void addEdge(int u, int v, int weight) {
        if (u >= 0 && u < size && v >= 0 && v < size) {
            adjMatrix[u][v] = weight;
        }
    }

    public void addVertexData(int vertex, char data) {
        if (vertex >= 0 && vertex < size) {
            vertexData[vertex] = data;
        }
    }

    public void printGraph() {
        System.out.println("Adjacency Matrix:");
        for (Integer[] row : adjMatrix) {
            for (Integer cell : row) {
                System.out.print((cell != null ? cell : 0) + " ");
            }
            System.out.println();
        }

        System.out.println("\nVertex Data:");
        for (int i = 0; i < size; i++) {
            System.out.println("Vertex " + i + ": " + vertexData[i]);
        }
    }
}

public class graph_directed_weighted{
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addVertexData(0, 'A');
        g.addVertexData(1, 'B');
        g.addVertexData(2, 'C');
        g.addVertexData(3, 'D');
        g.addEdge(0, 1, 3); // A -> B with weight 3
        g.addEdge(0, 2, 2); // A -> C with weight 2
        g.addEdge(3, 0, 4); // D -> A with weight 4
        g.addEdge(2, 1, 1); // C -> B with weight 1

        g.printGraph();
    }
}

//Java
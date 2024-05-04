public class undirected_graph_edges {
    public static void main(String[] args) {

        String[] vertexData = {"A", "B", "C", "D"};

        int[][] adjacencyMatrix = {
            {0, 1, 1, 1}, // Edges for A
            {1, 0, 1, 0}, // Edges for B
            {1, 1, 0, 0}, // Edges for C
            {1, 0, 0, 0}  // Edges for D
        };

        printAdjacencyMatrix(adjacencyMatrix);

        printConnections(adjacencyMatrix, vertexData);
    }

    public static void printAdjacencyMatrix(int[][] matrix) {
        System.out.println("Adjacency Matrix:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
    
    public static void printConnections(int[][] matrix, String[] vertices) {
        System.out.println("\nConnections for each vertex:");
        for (int i = 0; i < vertices.length; i++) {
            System.out.print(vertices[i] + ": ");
            for (int j = 0; j < vertices.length; j++) {
                if (matrix[i][j] == 1) { // if there is a connection
                    System.out.print(vertices[j] + " ");
                }
            }
            System.out.println();
        }
    }
}

//Java
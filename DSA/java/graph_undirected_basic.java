public class undirected_graph_basic {
    public static void main(String[] args) {

        String[] vertexData = {"A", "B", "C", "D"};

        int[][] adjacencyMatrix = {
            {0, 1, 1, 1}, // Edges for A
            {1, 0, 1, 0}, // Edges for B
            {1, 1, 0, 0}, // Edges for C
            {1, 0, 0, 0}  // Edges for D
        };

        System.out.print("vertexData: ");
        for (String vertex : vertexData) {
            System.out.print(vertex + " ");
        }
        System.out.println();
        System.out.println();

        printAdjacencyMatrix(adjacencyMatrix);
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
}

//Java
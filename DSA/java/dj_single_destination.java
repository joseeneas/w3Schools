import java.util.Arrays;

public class dj_single_destination {
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
            adjMatrix[u][v] = weight;
            adjMatrix[v][u] = weight; // For undirected graph
        }

        public void addVertexData(int vertex, String data) {
            vertexData[vertex] = data;
        }

        public String dijkstra(String startVertexData, String endVertexData) {
            int[] predecessors = new int[size];
            Arrays.fill(predecessors, -1);
            int startVertex = Arrays.asList(vertexData).indexOf(startVertexData);
            int endVertex = Arrays.asList(vertexData).indexOf(endVertexData);
            int[] distances = new int[size];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[startVertex] = 0;
            boolean[] visited = new boolean[size];

            for (int count = 0; count < size - 1; count++) {
                int u = minDistance(distances, visited);
        
                if (u == -1) {
                    break;
                }

                if (u == endVertex) {
                    System.out.println("Breaking out of loop. Current vertex: " + vertexData[u]);
                    System.out.println("Distances: " + Arrays.toString(distances));
                    break;
                }
        
                visited[u] = true;
                System.out.println("Visited vertex: " + vertexData[u]);
        
                for (int v = 0; v < size; v++) {
                    if (!visited[v] && adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE && distances[u] + adjMatrix[u][v] < distances[v]) {
                        distances[v] = distances[u] + adjMatrix[u][v];
                        predecessors[v] = u;
                    }
                }
            }

            String shortestPath = getPath(distances, predecessors, startVertex, endVertex);
            return "Path: " + shortestPath + ", Distance: " + distances[endVertex];
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

        public String getPath(int[] distances, int[] predecessors, int startVertex, int endVertex) {
            if (endVertex == -1 || distances[endVertex] == Integer.MAX_VALUE) {
                return "No path";
            }
        
            StringBuilder path = new StringBuilder();
            for(int vertex = endVertex; vertex != -1; vertex = predecessors[vertex]) {
                path.insert(0, vertexData[vertex]);
                if(vertex != startVertex) {
                    path.insert(0, "->");
                }
            }
            return path.toString();
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(10);
        
        g.addVertexData(0, "A");
        g.addVertexData(1, "B");
        g.addVertexData(2, "C");
        g.addVertexData(3, "D");
        g.addVertexData(4, "E");
        g.addVertexData(5, "F");
        g.addVertexData(6, "G");
        g.addVertexData(7, "H");
        g.addVertexData(8, "I");
        g.addVertexData(9, "J");

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
        g.addEdge(6, 8, 4); // G - I, weight 4
        g.addEdge(6, 7, 5); // G - H, weight 5
        g.addEdge(8, 9, 2); // I - J, weight 2

        System.out.println("Dijkstra's Algorithm, from vertex D to F:\n");
        String result = g.dijkstra("D", "F");
        System.out.println(result);
    }
}

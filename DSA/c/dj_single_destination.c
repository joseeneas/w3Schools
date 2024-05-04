#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define SIZE 10

typedef struct Graph
{
    int adjMatrix[SIZE][SIZE];
    char *vertexData[SIZE];
} Graph;

void addEdge(Graph *g, int u, int v, int weight)
{
    g->adjMatrix[u][v] = weight;
    g->adjMatrix[v][u] = weight;
}

void addVertexData(Graph *g, int vertex, char *data)
{
    g->vertexData[vertex] = data;
}

// Function to find the vertex with minimum distance value
int minDistance(int dist[], int sptSet[])
{
    // Initialize min value
    int min = INT_MAX, min_index;
    for (int v = 0; v < SIZE; v++)
        if (sptSet[v] == 0 && dist[v] <= min)
            min = dist[v], min_index = v;
    return min_index;
}

void printPath(Graph *g, int parent[], int j)
{
    // Base case: If j is the source vertex, return
    if (j == -1 || parent[j] == -1)
    {
        return;
    }

    printPath(g, parent, parent[j]);
    printf("->%s", g->vertexData[j]);
}

// Function that implements Dijkstra's algorithm
void dijkstra(Graph *g, int src, int target)
{
    int dist[SIZE];
    int sptSet[SIZE];
    int parent[SIZE];
    for (int i = 0; i < SIZE; i++)
    {
        parent[src] = -1;
        dist[i] = INT_MAX;
        sptSet[i] = 0;
    }
    dist[src] = 0;
    for (int count = 0; count < SIZE - 1; count++)
    {
        int u = minDistance(dist, sptSet);

        // Check if the algorithm is complete
        if (u == -1 || u == target)
        {
            printf("Breaking out of loop. Current vertex: %s\n", g->vertexData[u]);
            printf("Distances: [");
            for (int i = 0; i < SIZE; i++)
            {
                if (i > 0)
                    printf(", ");
                printf("%d", dist[i]);
            }
            printf("]\n");
            break;
        }

        printf("Visited vertex: %s\n", g->vertexData[u]);

        sptSet[u] = 1;
        for (int v = 0; v < SIZE; v++)
            if (!sptSet[v] && g->adjMatrix[u][v] && dist[u] + g->adjMatrix[u][v] < dist[v])
            {
                parent[v] = u;
                dist[v] = dist[u] + g->adjMatrix[u][v];
            }
    }
    printf("Path: ");
    if (src != target)
    {
        printf("%s", g->vertexData[src]); // Print source vertex
        printPath(g, parent, target);     // Start path printing from next vertex
    }
    else
    {
        printf("%s", g->vertexData[src]); // Print only the source vertex if it is the target
    }
    printf(", Distance: %d\n", dist[target]);
}

int main()
{
    Graph g = {0};

    // Initialize vertex data
    addVertexData(&g, 0, "A");
    addVertexData(&g, 1, "B");
    addVertexData(&g, 2, "C");
    addVertexData(&g, 3, "D");
    addVertexData(&g, 4, "E");
    addVertexData(&g, 5, "F");
    addVertexData(&g, 6, "G");
    addVertexData(&g, 7, "H");
    addVertexData(&g, 8, "I");
    addVertexData(&g, 9, "J");

    // Initialize edges
    addEdge(&g, 3, 0, 4); // D - A, weight 4
    addEdge(&g, 3, 4, 2); // D - E, weight 2
    addEdge(&g, 0, 2, 3); // A - C, weight 3
    addEdge(&g, 0, 4, 4); // A - E, weight 4
    addEdge(&g, 4, 2, 4); // E - C, weight 4
    addEdge(&g, 4, 6, 5); // E - G, weight 5
    addEdge(&g, 2, 5, 5); // C - F, weight 5
    addEdge(&g, 2, 1, 2); // C - B, weight 2
    addEdge(&g, 1, 5, 2); // B - F, weight 2
    addEdge(&g, 6, 5, 5); // G - F, weight 5
    addEdge(&g, 6, 8, 4); // G - I, weight 4
    addEdge(&g, 6, 7, 5); // G - H, weight 5
    addEdge(&g, 8, 9, 2); // I - J, weight 2

    // Run Dijkstra's algorithm from D to F
    printf("Dijkstra's Algorithm, from vertex D to F:\n\n");
    dijkstra(&g, 3, 5);

    return 0;
}

// C
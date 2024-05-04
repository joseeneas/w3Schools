#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define MAX_VERTEX_DATA 10 // Maximum length of vertex data string

typedef struct
{
    int size;
    int **adjMatrix;
    char **vertexData;
} Graph;

Graph *createGraph(int size)
{
    Graph *g = (Graph *)malloc(sizeof(Graph));
    g->size = size;

    // Allocate memory for adjacency matrix
    g->adjMatrix = (int **)malloc(size * sizeof(int *));
    for (int i = 0; i < size; i++)
    {
        g->adjMatrix[i] = (int *)calloc(size, sizeof(int));
    }

    // Allocate memory for vertex data
    g->vertexData = (char **)malloc(size * sizeof(char *));
    for (int i = 0; i < size; i++)
    {
        g->vertexData[i] = (char *)calloc(MAX_VERTEX_DATA, sizeof(char));
    }

    return g;
}

void addEdge(Graph *g, int u, int v, int weight)
{
    if (u >= 0 && u < g->size && v >= 0 && v < g->size)
    {
        g->adjMatrix[u][v] = weight;
        // g->adjMatrix[v][u] = weight; // For undirected graph
    }
}

void addVertexData(Graph *g, int vertex, const char *data)
{
    if (vertex >= 0 && vertex < g->size)
    {
        strncpy(g->vertexData[vertex], data, MAX_VERTEX_DATA - 1);
    }
}

void bellmanFord(Graph *g, const char *startVertexData)
{
    int startVertex = -1;
    for (int i = 0; i < g->size; i++)
    {
        if (strcmp(g->vertexData[i], startVertexData) == 0)
        {
            startVertex = i;
            break;
        }
    }

    if (startVertex == -1)
    {
        printf("Start vertex not found\n");
        return;
    }

    int distances[g->size];
    for (int i = 0; i < g->size; i++)
    {
        distances[i] = INT_MAX;
    }
    distances[startVertex] = 0;

    for (int i = 0; i < g->size - 1; i++)
    {
        for (int u = 0; u < g->size; u++)
        {
            for (int v = 0; v < g->size; v++)
            {
                if (g->adjMatrix[u][v] != 0 && distances[u] != INT_MAX &&
                    distances[u] + g->adjMatrix[u][v] < distances[v])
                {
                    distances[v] = distances[u] + g->adjMatrix[u][v];
                    printf("Relaxing edge %s->%s, Updated distance to %s: %d\n", g->vertexData[u], g->vertexData[v], g->vertexData[v], distances[v]);
                }
            }
        }
    }

    // Print distances
    for (int i = 0; i < g->size; i++)
    {
        printf("Distance from %s to %s: %d\n", startVertexData, g->vertexData[i], distances[i]);
    }
}

void freeGraph(Graph *g)
{
    if (g != NULL)
    {
        // Free each row of the adjacency matrix
        for (int i = 0; i < g->size; i++)
        {
            free(g->adjMatrix[i]);
        }
        // Free the adjacency matrix itself
        free(g->adjMatrix);

        // Free each vertex data string
        for (int i = 0; i < g->size; i++)
        {
            free(g->vertexData[i]);
        }
        // Free the vertex data array
        free(g->vertexData);

        // Finally, free the graph structure
        free(g);
    }
}

int main()
{
    Graph *g = createGraph(5);

    addVertexData(g, 0, "A");
    addVertexData(g, 1, "B");
    addVertexData(g, 2, "C");
    addVertexData(g, 3, "D");
    addVertexData(g, 4, "E");

    addEdge(g, 3, 0, 4);  // D -> A, weight 4
    addEdge(g, 3, 2, 7);  // D -> C, weight 7
    addEdge(g, 3, 4, 3);  // D -> E, weight 3
    addEdge(g, 0, 2, 4);  // A -> C, weight 4
    addEdge(g, 2, 0, -3); // C -> A, weight -3
    addEdge(g, 0, 4, 5);  // A -> E, weight 5
    addEdge(g, 4, 2, 3);  // E -> C, weight 3
    addEdge(g, 1, 2, -4); // B -> C, weight -4
    addEdge(g, 4, 1, 2);  // E -> B, weight 2

    printf("\nThe Bellman-Ford Algorithm starting from vertex D:\n");
    bellmanFord(g, "D");

    freeGraph(g); // Freeing up memory

    return 0;
}

// C
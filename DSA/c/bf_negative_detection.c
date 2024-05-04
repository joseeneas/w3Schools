#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <string.h>

#define MAX_VERTICES 5
#define INF INT_MAX

typedef struct
{
    int adjMatrix[MAX_VERTICES][MAX_VERTICES];
    char *vertexData[MAX_VERTICES];
    int size;
} Graph;

void initGraph(Graph *g, int size)
{
    g->size = size;
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            g->adjMatrix[i][j] = 0;
        }
        g->vertexData[i] = NULL;
    }
}

void addEdge(Graph *g, int u, int v, int weight)
{
    if (u >= 0 && u < g->size && v >= 0 && v < g->size)
    {
        g->adjMatrix[u][v] = weight;
    }
}

void addVertexData(Graph *g, int vertex, char *data)
{
    if (vertex >= 0 && vertex < g->size)
    {
        g->vertexData[vertex] = data;
    }
}

int findVertexIndex(Graph *g, char *vertexData)
{
    for (int i = 0; i < g->size; i++)
    {
        if (g->vertexData[i] && strcmp(g->vertexData[i], vertexData) == 0)
        {
            return i;
        }
    }
    return -1;
}

int bellmanFord(Graph *g, char *startVertexData, int *distances)
{
    int startVertex = findVertexIndex(g, startVertexData);
    if (startVertex == -1)
        return -1;

    for (int i = 0; i < g->size; i++)
    {
        distances[i] = INF;
    }
    distances[startVertex] = 0;

    for (int i = 0; i < g->size - 1; i++)
    {
        for (int u = 0; u < g->size; u++)
        {
            for (int v = 0; v < g->size; v++)
            {
                if (g->adjMatrix[u][v] && distances[u] != INF &&
                    distances[u] + g->adjMatrix[u][v] < distances[v])
                {
                    distances[v] = distances[u] + g->adjMatrix[u][v];
                    printf("Relaxing edge %s->%s, Updated distance to %s: %d\n",
                           g->vertexData[u], g->vertexData[v], g->vertexData[v], distances[v]);
                }
            }
        }
    }

    for (int u = 0; u < g->size; u++)
    {
        for (int v = 0; v < g->size; v++)
        {
            if (g->adjMatrix[u][v] && distances[u] != INF &&
                distances[u] + g->adjMatrix[u][v] < distances[v])
            {
                return 0; // Negative cycle detected
            }
        }
    }

    return 1; // No negative cycle detected
}

int main()
{
    Graph g;
    initGraph(&g, MAX_VERTICES);

    addVertexData(&g, 0, "A");
    addVertexData(&g, 1, "B");
    addVertexData(&g, 2, "C");
    addVertexData(&g, 3, "D");
    addVertexData(&g, 4, "E");

    addEdge(&g, 3, 0, 4);  // D -> A, weight 4
    addEdge(&g, 3, 2, 7);  // D -> C, weight 7
    addEdge(&g, 3, 4, 3);  // D -> E, weight 3
    addEdge(&g, 0, 2, 4);  // A -> C, weight 4
    addEdge(&g, 2, 0, -9); // C -> A, weight -9
    addEdge(&g, 0, 4, 5);  // A -> E, weight 5
    addEdge(&g, 4, 2, 3);  // E -> C, weight 3
    addEdge(&g, 1, 2, -4); // B -> C, weight -4
    addEdge(&g, 4, 1, 2);  // E -> B, weight 2

    int distances[MAX_VERTICES];
    printf("The Bellman-Ford Algorithm starting from vertex D:\n");
    if (bellmanFord(&g, "D", distances))
    {
        for (int i = 0; i < g.size; i++)
        {
            if (distances[i] != INF)
            {
                printf("Distance from D to %s: %d\n", g.vertexData[i], distances[i]);
            }
            else
            {
                printf("Distance from D to %s: Infinity\n", g.vertexData[i]);
            }
        }
    }
    else
    {
        printf("Negative weight cycle detected. Cannot compute the shortest paths.\n");
    }

    return 0;
}

// C
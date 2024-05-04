#include <stdio.h>
#include <stdbool.h>

#define SIZE 7

typedef struct
{
    int adjMatrix[SIZE][SIZE];
    char vertexData[SIZE];
} Graph;

void initGraph(Graph *g)
{
    for (int i = 0; i < SIZE; i++)
    {
        for (int j = 0; j < SIZE; j++)
        {
            g->adjMatrix[i][j] = 0;
        }
        g->vertexData[i] = '\0';
    }
}

void addEdge(Graph *g, int u, int v)
{
    if (u >= 0 && u < SIZE && v >= 0 && v < SIZE)
    {
        g->adjMatrix[u][v] = 1;
    }
}

void addVertexData(Graph *g, int vertex, char data)
{
    if (vertex >= 0 && vertex < SIZE)
    {
        g->vertexData[vertex] = data;
    }
}

void printGraph(Graph *g)
{
    printf("Adjacency Matrix:\n");
    for (int i = 0; i < SIZE; i++)
    {
        for (int j = 0; j < SIZE; j++)
        {
            printf("%d ", g->adjMatrix[i][j]);
        }
        printf("\n");
    }

    printf("\nVertex Data:\n");
    for (int i = 0; i < SIZE; i++)
    {
        printf("Vertex %d: %c\n", i, g->vertexData[i]);
    }
}

bool dfsUtil(Graph *g, int v, bool visited[], bool recStack[])
{
    visited[v] = true;
    recStack[v] = true;
    printf("Current vertex: %c\n", g->vertexData[v]);

    for (int i = 0; i < SIZE; i++)
    {
        if (g->adjMatrix[v][i] == 1)
        {
            if (!visited[i])
            {
                if (dfsUtil(g, i, visited, recStack))
                {
                    return true;
                }
            }
            else if (recStack[i])
            {
                return true;
            }
        }
    }

    recStack[v] = false;
    return false;
}

bool isCyclic(Graph *g)
{
    bool visited[SIZE] = {false};
    bool recStack[SIZE] = {false};

    for (int i = 0; i < SIZE; i++)
    {
        if (!visited[i])
        {
            printf("\n");
            if (dfsUtil(g, i, visited, recStack))
            {
                return true;
            }
        }
    }
    return false;
}

int main()
{
    Graph g;
    initGraph(&g);

    addVertexData(&g, 0, 'A');
    addVertexData(&g, 1, 'B');
    addVertexData(&g, 2, 'C');
    addVertexData(&g, 3, 'D');
    addVertexData(&g, 4, 'E');
    addVertexData(&g, 5, 'F');
    addVertexData(&g, 6, 'G');

    addEdge(&g, 3, 0); // D -> A
    addEdge(&g, 0, 2); // A -> C
    addEdge(&g, 2, 1); // C -> B
    addEdge(&g, 2, 4); // C -> E
    addEdge(&g, 1, 5); // B -> F
    addEdge(&g, 4, 0); // E -> A
    addEdge(&g, 2, 6); // C -> G

    printGraph(&g);

    printf("\nGraph has cycle: %s\n", isCyclic(&g) ? "Yes" : "No");
    return 0;
}

// C
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct
{
    int **adj_matrix;
    char *vertex_data;
    int *parent;
    int size;
} Graph;

Graph *create_graph(int size)
{
    Graph *g = malloc(sizeof(Graph));
    g->size = size;

    g->adj_matrix = malloc(size * sizeof(int *));
    for (int i = 0; i < size; i++)
    {
        g->adj_matrix[i] = calloc(size, sizeof(int));
    }

    g->vertex_data = malloc(size * sizeof(char));
    for (int i = 0; i < size; i++)
    {
        g->vertex_data[i] = '\0';
    }

    g->parent = malloc(size * sizeof(int));
    for (int i = 0; i < size; i++)
    {
        g->parent[i] = i;
    }

    return g;
}

void add_edge(Graph *g, int u, int v)
{
    if (u >= 0 && u < g->size && v >= 0 && v < g->size)
    {
        g->adj_matrix[u][v] = 1;
        g->adj_matrix[v][u] = 1;
    }
}

void add_vertex_data(Graph *g, int vertex, char data)
{
    if (vertex >= 0 && vertex < g->size)
    {
        g->vertex_data[vertex] = data;
    }
}

int find(Graph *g, int i)
{
    if (g->parent[i] == i)
    {
        return i;
    }
    return find(g, g->parent[i]);
}

void union_vertices(Graph *g, int x, int y)
{
    int xRoot = find(g, x);
    int yRoot = find(g, y);
    printf("Union: %c + %c\n", g->vertex_data[x], g->vertex_data[y]);
    g->parent[xRoot] = yRoot;
}

bool is_cyclic(Graph *g)
{
    for (int i = 0; i < g->size; i++)
    {
        for (int j = i + 1; j < g->size; j++)
        {
            if (g->adj_matrix[i][j])
            {
                int x = find(g, i);
                int y = find(g, j);
                if (x == y)
                {
                    return true;
                }
                union_vertices(g, x, y);
            }
        }
    }
    return false;
}

void free_graph(Graph *g)
{
    for (int i = 0; i < g->size; i++)
    {
        free(g->adj_matrix[i]);
    }
    free(g->adj_matrix);
    free(g->vertex_data);
    free(g->parent);
    free(g);
}

int main()
{
    Graph *g = create_graph(7);

    add_vertex_data(g, 0, 'A');
    add_vertex_data(g, 1, 'B');
    add_vertex_data(g, 2, 'C');
    add_vertex_data(g, 3, 'D');
    add_vertex_data(g, 4, 'E');
    add_vertex_data(g, 5, 'F');
    add_vertex_data(g, 6, 'G');

    add_edge(g, 1, 0); // B - A
    add_edge(g, 0, 3); // A - D
    add_edge(g, 0, 2); // A - C
    add_edge(g, 2, 3); // C - D
    add_edge(g, 3, 4); // D - E
    add_edge(g, 3, 5); // D - F
    add_edge(g, 3, 6); // D - G
    add_edge(g, 4, 5); // E - F

    printf("Graph has cycle: %s\n", is_cyclic(g) ? "true" : "false");

    free_graph(g);
    return 0;
}

// C
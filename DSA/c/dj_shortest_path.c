#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

typedef struct
{
    int **adj_matrix;
    char *vertex_data;
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
    return g;
}

void add_edge(Graph *g, int u, int v, int weight)
{
    if (u >= 0 && u < g->size && v >= 0 && v < g->size)
    {
        g->adj_matrix[u][v] = weight;
        g->adj_matrix[v][u] = weight; // For undirected graph
    }
}

void add_vertex_data(Graph *g, int vertex, char data)
{
    if (vertex >= 0 && vertex < g->size)
    {
        g->vertex_data[vertex] = data;
    }
}

void dijkstra(Graph *g, int start_vertex, int *distances, int *predecessors)
{
    int visited[g->size];
    for (int i = 0; i < g->size; i++)
    {
        distances[i] = INT_MAX;
        visited[i] = 0;
        predecessors[i] = -1;
    }
    distances[start_vertex] = 0;

    for (int count = 0; count < g->size - 1; count++)
    {
        int min = INT_MAX, min_index;
        for (int v = 0; v < g->size; v++)
        {
            if (!visited[v] && distances[v] <= min)
            {
                min = distances[v], min_index = v;
            }
        }
        int u = min_index;
        visited[u] = 1;

        for (int v = 0; v < g->size; v++)
        {
            if (!visited[v] && g->adj_matrix[u][v] && distances[u] != INT_MAX &&
                distances[u] + g->adj_matrix[u][v] < distances[v])
            {
                distances[v] = distances[u] + g->adj_matrix[u][v];
                predecessors[v] = u;
            }
        }
    }
}

void print_path(Graph *g, int *predecessors, int start_vertex, int end_vertex)
{
    int stack[g->size], top = -1;
    int current = end_vertex;
    while (current != -1)
    {
        stack[++top] = current;
        current = predecessors[current];
    }

    while (top != -1)
    {
        printf("%c", g->vertex_data[stack[top--]]);
        if (top != -1)
            printf("->");
    }
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

    add_edge(g, 3, 0, 4); // D - A, weight 4
    add_edge(g, 3, 4, 2); // D - E, weight 2
    add_edge(g, 0, 2, 3); // A - C, weight 3
    add_edge(g, 0, 4, 4); // A - E, weight 4
    add_edge(g, 4, 2, 4); // E - C, weight 4
    add_edge(g, 4, 6, 5); // E - G, weight 5
    add_edge(g, 2, 5, 5); // C - F, weight 5
    add_edge(g, 2, 1, 2); // C - B, weight 2
    add_edge(g, 1, 5, 2); // B - F, weight 2
    add_edge(g, 6, 5, 5); // G - F, weight 5

    int distances[g->size], predecessors[g->size];
    dijkstra(g, 3, distances, predecessors);

    printf("Dijkstra's Algorithm starting from vertex D:\n\n");
    for (int i = 0; i < g->size; i++)
    {
        print_path(g, predecessors, 3, i);
        printf(", Distance: %d\n", distances[i]);
    }

    // Free the graph
    for (int i = 0; i < g->size; i++)
        free(g->adj_matrix[i]);
    free(g->adj_matrix);
    free(g->vertex_data);
    free(g);
    return 0;
}

// C
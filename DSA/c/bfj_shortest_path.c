#include <stdio.h>
#include <stdlib.h>
#include <limits.h> // Include <limits.h> for INT_MAX and INT_MIN
#include <string.h> // Include <string.h> for string operations

#define MAX_SIZE 100 // Define the maximum size for the graph

// Structure to represent a graph
typedef struct
{
    int adj_matrix[MAX_SIZE][MAX_SIZE]; // Adjacency matrix
    int size;                           // Number of vertices
    char vertex_data[MAX_SIZE];         // Data for each vertex
} Graph;

// Function to initialize the graph
void init_graph(Graph *g, int size)
{
    g->size = size;
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            g->adj_matrix[i][j] = 0; // Initialize adjacency matrix to 0
        }
        g->vertex_data[i] = '\0'; // Initialize vertex data to empty
    }
}

// Function to add an edge to the graph
void add_edge(Graph *g, int u, int v, int weight)
{
    if (u >= 0 && u < g->size && v >= 0 && v < g->size)
    {
        g->adj_matrix[u][v] = weight;
        // For an undirected graph, also set g->adj_matrix[v][u] = weight;
    }
}

// Function to add data to a vertex
void add_vertex_data(Graph *g, int vertex, char data)
{
    if (vertex >= 0 && vertex < g->size)
    {
        g->vertex_data[vertex] = data;
    }
}

int bellman_ford(Graph *g, char start_vertex_data, int distances[], int predecessors[])
{
    int start_vertex = -1;
    for (int i = 0; i < g->size; i++)
    {
        if (g->vertex_data[i] == start_vertex_data)
        {
            start_vertex = i;
            break;
        }
    }

    for (int i = 0; i < g->size; i++)
    {
        distances[i] = INT_MAX; // Use INT_MAX from <limits.h>
        predecessors[i] = -1;
    }

    distances[start_vertex] = 0;

    for (int i = 0; i < g->size - 1; i++)
    {
        for (int u = 0; u < g->size; u++)
        {
            for (int v = 0; v < g->size; v++)
            {
                if (g->adj_matrix[u][v] != 0)
                {
                    if (distances[u] != INT_MAX && distances[u] + g->adj_matrix[u][v] < distances[v])
                    {
                        distances[v] = distances[u] + g->adj_matrix[u][v];
                        predecessors[v] = u;
                        printf("Relaxing edge %c->%c, Updated distance to %c: %d\n", g->vertex_data[u], g->vertex_data[v], g->vertex_data[v], distances[v]);
                    }
                }
            }
        }
    }

    // Negative cycle detection
    for (int u = 0; u < g->size; u++)
    {
        for (int v = 0; v < g->size; v++)
        {
            if (g->adj_matrix[u][v] != 0)
            {
                if (distances[u] != INT_MAX && distances[u] + g->adj_matrix[u][v] < distances[v])
                {
                    return 1; // Indicate a negative cycle was found
                }
            }
        }
    }

    return 0; // Indicate no negative cycle
}

// Function to get the shortest path from start_vertex_data to end_vertex_data
void get_path(Graph *g, int predecessors[], char start_vertex_data, char end_vertex_data, char path[])
{
    int path_index = 0;
    int current = -1;

    for (int i = 0; i < g->size; i++)
    {
        if (g->vertex_data[i] == end_vertex_data)
        {
            current = i;
            break;
        }
    }

    while (current != -1)
    {
        path[path_index++] = g->vertex_data[current];
        current = predecessors[current];
        if (current != -1 && g->vertex_data[current] == start_vertex_data)
        {
            path[path_index++] = start_vertex_data;
            break;
        }
    }

    // Reverse the path and add arrows
    char reversed_path[MAX_SIZE];
    int reversed_index = 0;
    for (int i = path_index - 1; i >= 0; i--)
    {
        reversed_path[reversed_index++] = path[i];
        if (i > 0)
        {
            reversed_path[reversed_index++] = '-';
            reversed_path[reversed_index++] = '>';
        }
    }
    reversed_path[reversed_index] = '\0';

    strcpy(path, reversed_path);
}

int main()
{
    // Create and initialize the graph
    Graph g;
    init_graph(&g, 5);

    add_vertex_data(&g, 0, 'A');
    add_vertex_data(&g, 1, 'B');
    add_vertex_data(&g, 2, 'C');
    add_vertex_data(&g, 3, 'D');
    add_vertex_data(&g, 4, 'E');

    add_edge(&g, 3, 0, 4);  // D -> A, weight 4
    add_edge(&g, 3, 2, 7);  // D -> C, weight 7
    add_edge(&g, 3, 4, 3);  // D -> E, weight 3
    add_edge(&g, 0, 2, 4);  // A -> C, weight 4
    add_edge(&g, 2, 0, -3); // C -> A, weight -3
    add_edge(&g, 0, 4, 5);  // A -> E, weight 5
    add_edge(&g, 4, 2, 3);  // E -> C, weight 3
    add_edge(&g, 1, 2, -4); // B -> C, weight -4
    add_edge(&g, 4, 1, 2);  // E -> B, weight 2

    int distances[MAX_SIZE];    // Declare distances array in main
    int predecessors[MAX_SIZE]; // Declare predecessors array in main

    // Running the Bellman-Ford algorithm from D to all vertices
    printf("\nThe Bellman-Ford Algorithm starting from vertex D:\n");
    int negative_cycle = bellman_ford(&g, 'D', distances, predecessors); // Pass distances and predecessors arrays
    if (!negative_cycle)
    {
        for (int i = 0; i < g.size; i++)
        {
            if (g.vertex_data[i] != '\0')
            {
                char path[MAX_SIZE];
                get_path(&g, predecessors, 'D', g.vertex_data[i], path);
                if (path[0] != '\0')
                {
                    printf("%s, Distance: %d\n", path, distances[i]);
                }
                else
                {
                    printf("No path from D to %c, Distance: Infinity\n", g.vertex_data[i]);
                }
            }
        }
    }
    else
    {
        printf("Negative weight cycle detected. Cannot compute shortest paths.\n");
    }

    return 0;
}

// C
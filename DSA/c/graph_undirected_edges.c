#include <stdio.h>

void printAdjacencyMatrix(int matrix[4][4], int size)
{
    printf("Adjacency Matrix:\n");
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
}

void printConnections(int matrix[4][4], char vertices[4], int size)
{
    printf("\nConnections for each vertex:\n");
    for (int i = 0; i < size; i++)
    {
        printf("%c: ", vertices[i]);
        for (int j = 0; j < size; j++)
        {
            if (matrix[i][j])
            { // if there is a connection
                printf("%c ", vertices[j]);
            }
        }
        printf("\n");
    }
}

int main()
{
    char vertexData[4] = {'A', 'B', 'C', 'D'};
    int adjacencyMatrix[4][4] = {
        {0, 1, 1, 1}, // Edges for A
        {1, 0, 1, 0}, // Edges for B
        {1, 1, 0, 0}, // Edges for C
        {1, 0, 0, 0}  // Edges for D
    };

    printAdjacencyMatrix(adjacencyMatrix, 4);
    printConnections(adjacencyMatrix, vertexData, 4);

    return 0;
}

// C
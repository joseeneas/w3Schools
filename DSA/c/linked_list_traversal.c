#include <stdio.h>
#include <stdlib.h>

typedef struct Node
{
    int data;
    struct Node *next;
} Node;

void traverseAndPrint(Node *head)
{
    Node *currentNode = head;
    while (currentNode)
    {
        printf("%d -> ", currentNode->data);
        currentNode = currentNode->next;
    }
    printf("null\n");
}

int main()
{
    Node *node1 = (Node *)malloc(sizeof(Node));
    Node *node2 = (Node *)malloc(sizeof(Node));
    Node *node3 = (Node *)malloc(sizeof(Node));
    Node *node4 = (Node *)malloc(sizeof(Node));
    Node *node5 = (Node *)malloc(sizeof(Node));

    node1->data = 7;
    node2->data = 11;
    node3->data = 3;
    node4->data = 2;
    node5->data = 9;

    node1->next = node2;
    node2->next = node3;
    node3->next = node4;
    node4->next = node5;
    node5->next = NULL;

    traverseAndPrint(node1);

    // Free the allocated memory
    free(node1);
    free(node2);
    free(node3);
    free(node4);
    free(node5);
    return 0;
}

// C
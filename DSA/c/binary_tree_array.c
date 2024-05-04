#include <stdio.h>

#define ARRAY_SIZE 15 // Define the size of the array

char *get_data(char binaryTreeArray[], int index);
int left_child_index(int index);
int right_child_index(int index);

int main()
{
    char binaryTreeArray[ARRAY_SIZE] = {'R', 'A', 'B', 'C', 'D', 'E', 'F', '\0', '\0', '\0', '\0', '\0', '\0', 'G', '\0'};

    int rightChild = right_child_index(0);
    int leftChildOfRightChild = left_child_index(rightChild);
    char *data = get_data(binaryTreeArray, leftChildOfRightChild);

    if (data != NULL)
    {
        printf("root.right.left.data: %c\n", *data);
    }
    else
    {
        printf("No data found.\n");
    }

    return 0;
}

char *get_data(char binaryTreeArray[], int index)
{
    if (index >= 0 && index < ARRAY_SIZE)
    {
        return &binaryTreeArray[index];
    }
    return NULL;
}

int left_child_index(int index)
{
    return 2 * index + 1;
}

int right_child_index(int index)
{
    return 2 * index + 2;
}

// C
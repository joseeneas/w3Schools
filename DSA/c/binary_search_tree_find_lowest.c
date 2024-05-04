#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode
{
    int data;
    struct TreeNode *left;
    struct TreeNode *right;
} TreeNode;

TreeNode *newTreeNode(int data)
{
    TreeNode *node = (TreeNode *)malloc(sizeof(TreeNode));
    node->data = data;
    node->left = NULL;
    node->right = NULL;
    return node;
}

void inOrderTraversal(TreeNode *node)
{
    if (node == NULL)
        return;
    inOrderTraversal(node->left);
    printf("%d, ", node->data);
    inOrderTraversal(node->right);
}

TreeNode *minValueNode(TreeNode *node)
{
    TreeNode *current = node;
    while (current && current->left != NULL)
    {
        current = current->left;
    }
    return current;
}

int main()
{
    TreeNode *root = newTreeNode(13);
    root->left = newTreeNode(7);
    root->right = newTreeNode(15);

    root->left->left = newTreeNode(3);
    root->left->right = newTreeNode(8);

    root->right->left = newTreeNode(14);
    root->right->right = newTreeNode(19);

    root->right->right->left = newTreeNode(18);

    inOrderTraversal(root);
    printf("\n");

    TreeNode *minNode = minValueNode(root);
    printf("Lowest value: %d\n", minNode->data);

    return 0;
}

// C
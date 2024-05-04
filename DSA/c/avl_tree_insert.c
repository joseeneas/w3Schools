#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode
{
    char data;
    struct TreeNode *left, *right;
    int height;
} TreeNode;

int max(int a, int b)
{
    return (a > b) ? a : b;
}

int height(TreeNode *N)
{
    if (N == NULL)
        return 0;
    return N->height;
}

TreeNode *newNode(char data)
{
    TreeNode *node = (TreeNode *)malloc(sizeof(TreeNode));
    node->data = data;
    node->left = NULL;
    node->right = NULL;
    node->height = 1;
    return (node);
}

TreeNode *rightRotate(TreeNode *y)
{
    printf("Rotate right on node %c\n", y->data);
    TreeNode *x = y->left;
    TreeNode *T2 = x->right;

    x->right = y;
    y->left = T2;

    y->height = max(height(y->left), height(y->right)) + 1;
    x->height = max(height(x->left), height(x->right)) + 1;

    return x;
}

TreeNode *leftRotate(TreeNode *x)
{
    printf("Rotate left on node %c\n", x->data);
    TreeNode *y = x->right;
    TreeNode *T2 = y->left;

    y->left = x;
    x->right = T2;

    x->height = max(height(x->left), height(x->right)) + 1;
    y->height = max(height(y->left), height(y->right)) + 1;

    return y;
}

int getBalance(TreeNode *N)
{
    if (N == NULL)
        return 0;
    return height(N->left) - height(N->right);
}

TreeNode *insert(TreeNode *node, char data)
{
    if (node == NULL)
        return (newNode(data));

    if (data < node->data)
        node->left = insert(node->left, data);
    else if (data > node->data)
        node->right = insert(node->right, data);
    else
        return node;

    node->height = 1 + max(height(node->left), height(node->right));

    int balance = getBalance(node);

    if (balance > 1 && data < node->left->data)
        return rightRotate(node);
    if (balance < -1 && data > node->right->data)
        return leftRotate(node);
    if (balance > 1 && data > node->left->data)
    {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }
    if (balance < -1 && data < node->right->data)
    {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

void inOrderTraversal(TreeNode *root)
{
    if (root != NULL)
    {
        inOrderTraversal(root->left);
        printf("%c ", root->data);
        inOrderTraversal(root->right);
    }
}

int main()
{
    TreeNode *root = NULL;
    char letters[] = {'C', 'B', 'E', 'A', 'D', 'H', 'G', 'F'};
    int n = sizeof(letters) / sizeof(letters[0]);
    for (int i = 0; i < n; i++)
    {
        root = insert(root, letters[i]);
    }

    inOrderTraversal(root);
    return 0;
}

// C
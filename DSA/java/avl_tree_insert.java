public class avl_tree_insert {
    static class AVLTree {
        class TreeNode {
            char data;
            TreeNode left, right;
            int height;

            TreeNode(char d) {
                data = d;
                height = 1;
            }
        }

        TreeNode root;

        int height(TreeNode N) {
            if (N == null) return 0;
            return N.height;
        }

        int getBalance(TreeNode N) {
            if (N == null) return 0;
            return height(N.left) - height(N.right);
        }

        TreeNode rightRotate(TreeNode y) {
            System.out.println("Rotate right on node " + y.data);
            TreeNode x = y.left;
            TreeNode T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        TreeNode leftRotate(TreeNode x) {
            System.out.println("Rotate left on node " + x.data);
            TreeNode y = x.right;
            TreeNode T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        TreeNode insert(TreeNode node, char data) {
            if (node == null) return new TreeNode(data);

            if (data < node.data) node.left = insert(node.left, data);
            else if (data > node.data) node.right = insert(node.right, data);
            else return node;

            node.height = 1 + Math.max(height(node.left), height(node.right));

            int balance = getBalance(node);

            if (balance > 1 && data < node.left.data) return rightRotate(node);
            if (balance < -1 && data > node.right.data) return leftRotate(node);
            if (balance > 1 && data > node.left.data) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && data < node.right.data) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        void inOrderTraversal(TreeNode node) {
            if (node != null) {
                inOrderTraversal(node.left);
                System.out.print(node.data + ", ");
                inOrderTraversal(node.right);
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        char[] letters = {'C', 'B', 'E', 'A', 'D', 'H', 'G', 'F'};
        for (char letter : letters) {
            tree.root = tree.insert(tree.root, letter);
        }
        tree.inOrderTraversal(tree.root);
    }
}

//Java

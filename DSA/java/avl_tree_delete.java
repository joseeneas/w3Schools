public class avl_tree_delete {
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

        private int height(TreeNode N) {
            if (N == null)
                return 0;
            return N.height;
        }

        private int getBalance(TreeNode N) {
            if (N == null)
                return 0;
            return height(N.left) - height(N.right);
        }

        private TreeNode rightRotate(TreeNode y) {
            TreeNode x = y.left;
            TreeNode T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        private TreeNode leftRotate(TreeNode x) {
            TreeNode y = x.right;
            TreeNode T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        TreeNode insert(TreeNode node, char data) {
            if (node == null)
                return new TreeNode(data);

            if (data < node.data)
                node.left = insert(node.left, data);
            else if (data > node.data)
                node.right = insert(node.right, data);
            else
                return node;

            node.height = 1 + Math.max(height(node.left), height(node.right));

            int balance = getBalance(node);

            // Left Left Case
            if (balance > 1 && data < node.left.data)
                return rightRotate(node);

            // Right Right Case
            if (balance < -1 && data > node.right.data)
                return leftRotate(node);

            // Left Right Case
            if (balance > 1 && data > node.left.data) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // Right Left Case
            if (balance < -1 && data < node.right.data) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        private TreeNode minValueNode(TreeNode node) {
            TreeNode current = node;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        TreeNode delete(TreeNode root, char data) {
            if (root == null)
                return root;

            if (data < root.data)
                root.left = delete(root.left, data);
            else if (data > root.data)
                root.right = delete(root.right, data);
            else {
                if ((root.left == null) || (root.right == null)) {
                    TreeNode temp = null;
                    if (temp == root.left)
                        temp = root.right;
                    else
                        temp = root.left;

                    if (temp == null) {
                        temp = root;
                        root = null;
                    } else
                        root = temp;
                } else {
                    TreeNode temp = minValueNode(root.right);
                    root.data = temp.data;
                    root.right = delete(root.right, temp.data);
                }
            }

            if (root == null)
                return root;

            root.height = Math.max(height(root.left), height(root.right)) + 1;

            int balance = getBalance(root);

            // Left Left Case
            if (balance > 1 && getBalance(root.left) >= 0)
                return rightRotate(root);

            // Left Right Case
            if (balance > 1 && getBalance(root.left) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }

            // Right Right Case
            if (balance < -1 && getBalance(root.right) <= 0)
                return leftRotate(root);

            // Right Left Case
            if (balance < -1 && getBalance(root.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }

            return root;
        }

        void inOrderTraversal(TreeNode node) {
            if (node != null) {
                inOrderTraversal(node.left);
                System.out.print(node.data + ", ");
                inOrderTraversal(node.right);
            }
        }

        public static void main(String[] args) {
            AVLTree tree = new AVLTree();
            char[] letters = { 'C', 'B', 'E', 'A', 'D', 'H', 'G', 'F' };
            for (char letter : letters) {
                tree.root = tree.insert(tree.root, letter);
            }

            tree.inOrderTraversal(tree.root);

            System.out.println("\nDeleting A");
            tree.root = tree.delete(tree.root, 'A');
            tree.inOrderTraversal(tree.root);
        }
    }
}

// Java

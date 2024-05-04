public class binary_search_tree_find_lowest{

    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static void inOrderTraversal(TreeNode node) {
        if (node == null)
            return;
        inOrderTraversal(node.left);
        System.out.print(node.data + ", ");
        inOrderTraversal(node.right);
    }

    public static TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(13);
        root.left = new TreeNode(7);
        root.right = new TreeNode(15);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(8);

        root.right.left = new TreeNode(14);
        root.right.right = new TreeNode(19);

        root.right.right.left = new TreeNode(18);

        inOrderTraversal(root);
        System.out.println();

        TreeNode minNode = minValueNode(root);
        System.out.println("Lowest value: " + minNode.data);
    }
}

// Java

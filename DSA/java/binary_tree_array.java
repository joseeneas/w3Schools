public class binary_tree_array {
    public static void main(String[] args) {
        Character[] binaryTreeArray = {'R', 'A', 'B', 'C', 'D', 'E', 'F', null, null, null, null, null, null, 'G'};

        int rightChild = rightChildIndex(0);
        int leftChildOfRightChild = leftChildIndex(rightChild);
        Character data = getData(binaryTreeArray, leftChildOfRightChild);

        System.out.println("root.right.left.data: " + data);
    }

    public static int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    public static int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    public static Character getData(Character[] binaryTreeArray, int index) {
        if (index >= 0 && index < binaryTreeArray.length) {
            return binaryTreeArray[index];
        }
        return null;
    }
}

//Java

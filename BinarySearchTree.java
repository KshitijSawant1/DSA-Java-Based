import java.util.Scanner;

class NodeBST {
    int data;
    NodeBST left, right;

    public NodeBST(int item) {
        data = item;
        left = right = null;
    }
}

class BST {
    NodeBST root;

    BST() {
        root = null;
    }

    void insert(int data) {
        root = insertRec(root, data);
    }

    NodeBST insertRec(NodeBST root, int data) {
        if (root == null) {
            root = new NodeBST(data);
            return root;
        }
        if (data < root.data)
            root.left = insertRec(root.left, data);
        else if (data > root.data)
            root.right = insertRec(root.right, data);
        return root;
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(NodeBST root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    void preorder() {
        preorderRec(root);
    }

    void preorderRec(NodeBST root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    void postorder() {
        postorderRec(root);
    }

    void postorderRec(NodeBST root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    boolean search(int data) {
        return searchRec(root, data);
    }

    boolean searchRec(NodeBST root, int data) {
        if (root == null)
            return false;
        if (root.data == data)
            return true;
        if (data < root.data)
            return searchRec(root.left, data);
        return searchRec(root.right, data);
    }

    void delete(int data) {
        root = deleteRec(root, data);
    }

    NodeBST deleteRec(NodeBST root, int data) {
        if (root == null)
            return root;
        if (data < root.data)
            root.left = deleteRec(root.left, data);
        else if (data > root.data)
            root.right = deleteRec(root.right, data);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    int minValue(NodeBST root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    void displayTree() {
        displayTreeRec(root, 0);
    }

    void displayTreeRec(NodeBST root, int space) {
        if (root == null)
            return;
        space += 5;
        displayTreeRec(root.right, space);
        System.out.println();
        for (int i = 5; i < space; i++)
            System.out.print(" ");
        System.out.println(root.data);
        displayTreeRec(root.left, space);
    }
}

public class BinarySearchTree {
    private static final Scanner sc = new Scanner(System.in);
    private static final BST tree = new BST();

    public static void main(String[] args) {
        displayMenu();
        while (true) {
            System.out.println();
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertElement();
                    break;
                case 2:
                    deleteElement();
                    break;
                case 3:
                    searchElement();
                    break;
                case 4:
                    displayInorder();
                    break;
                case 5:
                    displayPreorder();
                    break;
                case 6:
                    displayPostorder();
                    break;
                case 7:
                    displayTreeStructure();
                    break;
                case 8:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nBinary Search Tree Operations Menu:");
        System.out.println("1. Insert element");
        System.out.println("2. Delete element");
        System.out.println("3. Search element");
        System.out.println("4. Display in-order traversal");
        System.out.println("5. Display pre-order traversal");
        System.out.println("6. Display post-order traversal");
        System.out.println("7. Display tree structure");
        System.out.println("8. Exit");
    }

    private static void insertElement() {
        System.out.print("Enter element to insert: ");
        int insertElement = sc.nextInt();
        tree.insert(insertElement);
        System.out.println("Element inserted successfully.");
    }

    private static void deleteElement() {
        System.out.print("Enter element to delete: ");
        int deleteElement = sc.nextInt();
        tree.delete(deleteElement);
        System.out.println("Element deleted successfully.");
    }

    private static void searchElement() {
        System.out.print("Enter element to search: ");
        int searchElement = sc.nextInt();
        boolean found = tree.search(searchElement);
        if (found)
            System.out.println("Element found in the tree.");
        else
            System.out.println("Element not found in the tree.");
    }

    private static void displayInorder() {
        System.out.println("In-order Traversal of the tree:");
        tree.inorder();
        System.out.println();
    }

    private static void displayPreorder() {
        System.out.println("Pre-order Traversal of the tree:");
        tree.preorder();
        System.out.println();
    }

    private static void displayPostorder() {
        System.out.println("Post-order Traversal of the tree:");
        tree.postorder();
        System.out.println();
    }

    private static void displayTreeStructure() {
        System.out.println("Tree Structure:");
        tree.displayTree();
        System.out.println();
    }

    private static void exitProgram() {
        System.out.println("Exiting...");
        sc.close();
        System.exit(0);
    }
}

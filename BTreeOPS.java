import java.util.Scanner;

// Define the node structure of the BTree
class Node {
    int data;          // Data of the node
    Node left, right;  // Left and right children of the node

    // Constructor to create a new node
    public Node(int item) {
        data = item;
        left = right = null;
    }
}

// Define the BTree class with traversal methods
class BTree {
    Node root;  // Root of the BTree

    // Constructor for BTree
    public BTree() {
        root = null;
    }

    // Inorder traversal of the BTree (Left, Root, Right)
    void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    // Preorder traversal of the BTree (Root, Left, Right)
    void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    // Postorder traversal of the BTree (Left, Right, Root)
    void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    // Menu-driven 
    void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Sample tree creation
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        while (true) {
            System.out.println("\nBinary Tree Traversal Menu:");
            System.out.println("1. Inorder Traversal");
            System.out.println("2. Preorder Traversal");
            System.out.println("3. Postorder Traversal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Inorder traversal: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Preorder traversal: ");
                    preorder(root);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Postorder traversal: ");
                    postorder(root);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Main method to run the BTree menu
    public static void main(String[] args) {
        BTree tree = new BTree();
        tree.menu();  // Start the menu-driven traversal
    }
}

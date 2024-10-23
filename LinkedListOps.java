import java.util.Scanner;

public class LinkedListOps {
    private Node head;
    private Scanner scanner;

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedListOps() {
        scanner = new Scanner(System.in);
    }

    public void Traversing() {
        if (head == null) {
            System.out.println("List is empty");
        } else {
            System.out.print("List Contents: ");
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
        System.out.println("Traversing completed successfully.");
    }

    public void Insertion_at_beginning() {
        System.out.print("Enter data to insert at beginning: ");
        int data = scanner.nextInt();
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        System.out.println("Insertion at beginning completed successfully.");
    }

    public void Insertion_at_the_end() {
        System.out.print("Enter data to insert at the end: ");
        int data = scanner.nextInt();
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Insertion at the end completed successfully.");
    }

    public void Insertion_after_a_given_node() {
        System.out.print("Enter the node data after which to insert: ");
        int after = scanner.nextInt();
        System.out.print("Enter the data to insert: ");
        int data = scanner.nextInt();
        Node current = head;
        while (current != null && current.data != after) {
            current = current.next;
        }
        if (current != null) {
            Node newNode = new Node(data);
            newNode.next = current.next;
            current.next = newNode;
            System.out.println("Insertion after a given node completed successfully.");
        } else {
            System.out.println("Node " + after + " not found.");
        }
    }

    public void Insertion_before_a_given_node() {
        System.out.print("Enter the node data before which to insert: ");
        int before = scanner.nextInt();
        System.out.print("Enter the data to insert: ");
        int data = scanner.nextInt();
        if (head == null) {
            Insertion_at_beginning();
        } else {
            Node current = head, prev = null;
            while (current != null && current.data != before) {
                prev = current;
                current = current.next;
            }
            if (current != null) {
                Node newNode = new Node(data);
                newNode.next = current;
                prev.next = newNode;
                System.out.println("Insertion before a given node completed successfully.");
            } else {
                System.out.println("Node " + before + " not found.");
            }
        }
    }

    public void Deleting_a_starting_node() {
        if (head != null) {
            head = head.next;
            System.out.println("Starting node deletion completed successfully.");
        } else {
            System.out.println("List is empty, nothing to delete.");
        }
    }

    public void Deleting_a_end_node() {
        if (head == null) {
            System.out.println("List is empty, nothing to delete.");
        } else if (head.next == null) {
            head = null;
            System.out.println("End node deletion completed successfully.");
        } else {
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
            System.out.println("End node deletion completed successfully.");
        }
    }

    public void Deleting_a_given_node() {
        System.out.print("Enter the node data to delete: ");
        int data = scanner.nextInt();
        if (head == null) {
            System.out.println("List is empty, nothing to delete.");
        } else {
            Node current = head, prev = null;
            while (current != null && current.data != data) {
                prev = current;
                current = current.next;
            }
            if (current != null) {
                prev.next = current.next;
                System.out.println("Given node deletion completed successfully.");
            } else {
                System.out.println("Node " + data + " not found.");
            }
        }
    }

    public void Searching_for_a_given_node() {
        System.out.print("Enter the node data to search for: ");
        int data = scanner.nextInt();
        Node current = head;
        Node prev = null;
        
        while (current != null) {
            if (current.data == data) {
                System.out.println("Node found with data: " + data);
                if (prev != null) {
                    System.out.println("Previous Node Data: " + prev.data);
                } else {
                    System.out.println("This node is the head of the list.");
                }
                
                if (current.next != null) {
                    System.out.println("Next Node Data: " + current.next.data);
                } else {
                    System.out.println("This node is the last node before null.");
                }
                if (head == current) {
                    System.out.println("Position: Head node.");
                } else if (current.next == null) {
                    System.out.println("Position: Last node before null.");
                } else {
                    System.out.println("Position: Between node.");
                }
    
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Node " + data + " not found.");
    }
    

    public void Exiting() {
        System.out.println("Exiting program.");
        scanner.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        LinkedListOps list = new LinkedListOps();
        while (true) {
            System.out.println("\nLinked List Regulation Contents : ");
            System.out.println(); 
            System.out.println("01) Traversing");
            System.out.println("02) Insertion at beginning");
            System.out.println("03) Insertion at the end");
            System.out.println("04) Insertion after a given node");
            System.out.println("05) Insertion before a given node");
            System.out.println("06) Searching for a given node");
            System.out.println("07) Deleting a starting node");
            System.out.println("08) Deleting an end node");
            System.out.println("09) Deleting a given node");
            System.out.println("10) Exiting");
            System.out.print("Enter your choice: ");
            int choice = list.scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1: list.Traversing(); break;
                case 2: list.Insertion_at_beginning(); break;
                case 3: list.Insertion_at_the_end(); break;
                case 4: list.Insertion_after_a_given_node(); break;
                case 5: list.Insertion_before_a_given_node(); break;
                case 6: list.Searching_for_a_given_node(); break;
                case 7: list.Deleting_a_starting_node(); break;
                case 8: list.Deleting_a_end_node(); break;
                case 9: list.Deleting_a_given_node(); break;
                case 10: list.Exiting(); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

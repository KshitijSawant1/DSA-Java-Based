import java.util.Scanner;

public class LinkedListOps2 {
    private Node head;
    @SuppressWarnings("unused")
    private Scanner scanner;

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedListOps2() {
        scanner = new Scanner(System.in);
    }

    // Insert node at the beginning
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Insert node at the end
    public void insertAtEnd(int data) {
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
    }

    // Insert node after a specific node
    public void insertAfterNode(int after, int data) {
        Node current = head;
        while (current != null && current.data != after) {
            current = current.next;
        }
        if (current != null) {
            Node newNode = new Node(data);
            newNode.next = current.next;
            current.next = newNode;
        } else {
            System.out.println("Node " + after + " not found.");
        }
    }

    // Delete node at the beginning
    public void deleteAtBeginning() {
        if (head != null) {
            head = head.next;
        } else {
            System.out.println("List is empty.");
        }
    }

    // Delete node at the end
    public void deleteAtEnd() {
        if (head == null) {
            System.out.println("List is empty.");
        } else if (head.next == null) {
            head = null;
        } else {
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
    }

    // Delete a specific node
    public void deleteGivenNode(int data) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.data == data) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        } else {
            System.out.println("Node " + data + " not found.");
        }
    }

    // Traverse the list
    public void traverse() {
        if (head == null) {
            System.out.println("List is empty.");
        } else {
            Node current = head;
            System.out.print("List: ");
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

    // Search for a node
    public void search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                System.out.println("Node found: " + data);
                return;
            }
            current = current.next;
        }
        System.out.println("Node " + data + " not found.");
    }

    public static void main(String[] args) {
        LinkedListOps2 list = new LinkedListOps2();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1) Insert");
            System.out.println("2) Delete");
            System.out.println("3) Traverse");
            System.out.println("4) Search");
            System.out.println("5) Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("1) Insert at Beginning");
                    System.out.println("2) Insert at End");
                    System.out.println("3) Insert After Node");
                    System.out.print("Enter choice: ");
                    int insertChoice = sc.nextInt();
                    System.out.print("Enter data: ");
                    int data = sc.nextInt();
                    if (insertChoice == 1) {
                        list.insertAtBeginning(data);
                    } else if (insertChoice == 2) {
                        list.insertAtEnd(data);
                    } else if (insertChoice == 3) {
                        System.out.print("Enter node after which to insert: ");
                        int after = sc.nextInt();
                        list.insertAfterNode(after, data);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 2:
                    System.out.println("1) Delete at Beginning");
                    System.out.println("2) Delete at End");
                    System.out.println("3) Delete Given Node");
                    System.out.print("Enter choice: ");
                    int deleteChoice = sc.nextInt();
                    if (deleteChoice == 1) {
                        list.deleteAtBeginning();
                    } else if (deleteChoice == 2) {
                        list.deleteAtEnd();
                    } else if (deleteChoice == 3) {
                        System.out.print("Enter node to delete: ");
                        int deleteData = sc.nextInt();
                        list.deleteGivenNode(deleteData);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 3:
                    list.traverse();
                    break;

                case 4:
                    System.out.print("Enter node to search: ");
                    int searchData = sc.nextInt();
                    list.search(searchData);
                    break;

                case 5:
                    sc.close();
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

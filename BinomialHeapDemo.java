import java.util.Scanner;

class BinomialHeapNode {
    int key;
    int degree;
    BinomialHeapNode parent;
    BinomialHeapNode child;
    BinomialHeapNode sibling;

    public BinomialHeapNode(int key) {
        this.key = key;
        this.degree = 0;
        this.parent = null;
        this.child = null;
        this.sibling = null;
    }
}

class BinomialHeap {
    private BinomialHeapNode head;

    public BinomialHeap() {
        this.head = null;
    }

    // Insert a node into the binomial heap
    public void insert(int key) {
        BinomialHeapNode newNode = new BinomialHeapNode(key);
        BinomialHeap newHeap = new BinomialHeap();
        newHeap.head = newNode;
        this.head = union(this.head, newHeap.head);
    }

    // Union of two binomial heaps
    private BinomialHeapNode union(BinomialHeapNode h1, BinomialHeapNode h2) {
        BinomialHeapNode newHead = merge(h1, h2);
        if (newHead == null) {
            return null;
        }

        BinomialHeapNode prev = null;
        BinomialHeapNode curr = newHead;
        BinomialHeapNode next = curr.sibling;

        while (next != null) {
            if (curr.degree != next.degree || (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                if (curr.key <= next.key) {
                    curr.sibling = next.sibling;
                    link(next, curr);
                } else {
                    if (prev == null) {
                        newHead = next;
                    } else {
                        prev.sibling = next;
                    }
                    link(curr, next);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
        return newHead;
    }

    // Merge two binomial heaps
    private BinomialHeapNode merge(BinomialHeapNode h1, BinomialHeapNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        BinomialHeapNode newHead;
        if (h1.degree <= h2.degree) {
            newHead = h1;
            h1 = h1.sibling;
        } else {
            newHead = h2;
            h2 = h2.sibling;
        }

        BinomialHeapNode tail = newHead;
        while (h1 != null && h2 != null) {
            if (h1.degree <= h2.degree) {
                tail.sibling = h1;
                h1 = h1.sibling;
            } else {
                tail.sibling = h2;
                h2 = h2.sibling;
            }
            tail = tail.sibling;
        }

        tail.sibling = (h1 != null) ? h1 : h2;
        return newHead;
    }

    // Link two binomial trees
    private void link(BinomialHeapNode y, BinomialHeapNode z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
    }

    // Find minimum node in binomial heap
    public int findMin() {
        if (head == null) {
            return -1;
        }

        BinomialHeapNode minNode = head;
        BinomialHeapNode current = head.sibling;

        while (current != null) {
            if (current.key < minNode.key) {
                minNode = current;
            }
            current = current.sibling;
        }
        return minNode.key;
    }

    // Extract the node with minimum key
    public int extractMin() {
        if (head == null) {
            return -1;
        }

        BinomialHeapNode prevMinNode = null;
        BinomialHeapNode minNode = head;
        BinomialHeapNode current = head.sibling;
        BinomialHeapNode prevCurrent = head;

        while (current != null) {
            if (current.key < minNode.key) {
                prevMinNode = prevCurrent;
                minNode = current;
            }
            prevCurrent = current;
            current = current.sibling;
        }

        if (prevMinNode != null) {
            prevMinNode.sibling = minNode.sibling;
        } else {
            head = minNode.sibling;
        }

        BinomialHeapNode child = minNode.child;
        BinomialHeapNode revChild = null;
        while (child != null) {
            BinomialHeapNode next = child.sibling;
            child.sibling = revChild;
            revChild = child;
            child = next;
        }

        head = union(head, revChild);
        return minNode.key;
    }

    // Display the heap (Pre-order traversal)
    public void display(BinomialHeapNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            display(node.child);
            display(node.sibling);
        }
    }

    public BinomialHeapNode getHead() {
        return head;
    }
}

public class BinomialHeapDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinomialHeap heap = new BinomialHeap();
        int choice;

        do {
            System.out.println("\n--- Binomial Heap Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Find Minimum");
            System.out.println("3. Extract Minimum");
            System.out.println("4. Display Heap");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                    int key = sc.nextInt();
                    heap.insert(key);
                    System.out.println("Inserted " + key);
                    break;
                case 2:
                    int min = heap.findMin();
                    if (min == -1) {
                        System.out.println("Heap is empty");
                    } else {
                        System.out.println("Minimum key: " + min);
                    }
                    break;
                case 3:
                    int extractedMin = heap.extractMin();
                    if (extractedMin == -1) {
                        System.out.println("Heap is empty");
                    } else {
                        System.out.println("Extracted minimum key: " + extractedMin);
                    }
                    break;
                case 4:
                    System.out.println("Heap elements (Pre-order): ");
                    heap.display(heap.getHead());
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}

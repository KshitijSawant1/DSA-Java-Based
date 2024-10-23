import java.util.Scanner;

public class QueueOps {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;

    // Constructor
    public QueueOps(int size) {
        this.maxSize = size;
        this.queueArray = new int[size];
        this.front = -1;
        this.rear = -1;
    }

    // Check if the queue is full
    public boolean isQueueFull() {
        if (rear == maxSize - 1) {
            System.out.println("Queue Overflow");
            return true;
        } else {
            System.out.println("Queue has capacity to accept elements");
            return false;
        }
    }

    // Check if the queue is empty
    public boolean isQueueEmpty() {
        if (front == -1 || front > rear) {
            System.out.println("Queue Underflow");
            return true;
        } else {
            System.out.println("Queue has capacity to remove elements");
            return false;
        }
    }

    // To insert an element into the queue
    public void enqueue(int item) {
        if (isQueueFull()) {
            return;
        }

        if (front == -1) { // For the first time enqueue
            front = 0;
        }
        rear++;
        queueArray[rear] = item;
        System.out.println("Value Inserted: " + item);
    }

    // To delete an element from the queue
    public void dequeue() {
        if (isQueueEmpty()) {
            return;
        }

        int item = queueArray[front];
        if (front == rear) { // If only one element was present
            front = -1;
            rear = -1;
        } else {
            front++;
        }
        System.out.println("Item deleted: " + item);
    }

    // To display the queue
    public void display() {
        if (isQueueEmpty()) {
            return;
        }

        System.out.println("Printing values:");
        for (int i = front; i <= rear; i++) {
            System.out.println("Queue Array[" + i + "] = " + queueArray[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the size of the queue: ");
        int size = scanner.nextInt();
        QueueOps queue = new QueueOps(size);

        int choice;
        do {
            System.out.println("\nQueue Operations Menu:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Display Queue");
            System.out.println("4. Check if Queue is Full");
            System.out.println("5. Check if Queue is Empty");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Enter the element to enqueue: ");
                    int item = scanner.nextInt();
                    queue.enqueue(item);
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.display();
                    break;
                case 4:
                    queue.isQueueFull();
                    break;
                case 5:
                    queue.isQueueEmpty();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}

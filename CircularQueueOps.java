import java.util.Scanner;

public class CircularQueueOps {
    private int[] queue;
    private int front, rear, size;

    public CircularQueueOps(int size) {
        this.size = size;
        this.queue = new int[this.size];
        this.front = this.rear = -1;
    }

    public void enqueue(int item) {
        if ((rear + 1) % size == front) {
            System.out.println("Queue is Overflow");
        } else {
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            queue[rear] = item;
        }
    }

    public void dequeue() {
        if (front == -1) {
            System.out.println("Queue is Underflow");
        } else {
            int removedElement = queue[front];
            if (front == rear) {
                System.out.println("Item dequeued: " + removedElement);
                front = rear = -1;
            } else {
                System.out.println("Item dequeued: " + removedElement);
                front = (front + 1) % size;
            }
        }
    }
    
    public void display() {
        if (front == -1) {
            System.out.println("Queue is Empty");
        } else {
            int i = front;
            System.out.print("Queue contents: ");
            while (i != rear) {
                System.out.print(queue[i] + " ");
                i = (i + 1) % size;
            }
            System.out.println(queue[i]);
        }
    }

    public void checkOverflow() {
        if ((rear + 1) % size == front) {
            System.out.println("Queue is in Overflow State");
        } else {
            int count = (rear - front + size) % size + 1; // Calculation to handle circular nature
            System.out.println("Queue is not in Overflow State. It currently holds " + count + " items.");
        }
    }
    
    public void checkUnderflow() {
        if (front == -1) {
            System.out.println("Queue is in Underflow State");
        } else {
            int count = (rear - front + size) % size + 1; // Calculation to handle circular nature
            System.out.println("Queue is not in Underflow State. It currently holds " + count + " items.");
        }
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the Circular Queue: ");
        int size = scanner.nextInt();
        CircularQueueOps queue = new CircularQueueOps(size);

        while (true) {
            System.out.println("\n1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Display the contents");
            System.out.println("4. Check if Overflow");
            System.out.println("5. Check if Underflow");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the element: ");
                    int element = scanner.nextInt();
                    queue.enqueue(element);
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.display();
                    break;
                case 4:
                    queue.checkOverflow();
                    break;
                case 5:
                    queue.checkUnderflow();
                    break;
                case 6:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}

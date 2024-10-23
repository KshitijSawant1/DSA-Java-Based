import java.util.Scanner;

public class StackOps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the Stack: ");
        int stackSize = sc.nextInt();
        Stack stack = new Stack(stackSize);

        int choice;

        do {
            System.out.println("\nStack Operations Menu:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Display Stack");
            System.out.println("4. Check if Stack is Full");
            System.out.println("5. Check if Stack is Empty");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    stack.push(sc);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.display();
                    break;
                case 4:
                    stack.isFull();
                    break;
                case 5:
                    stack.isEmpty();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        sc.close();
    }
}

class Stack {
    int top , maxsize;
    int[] arr;

    // Class Constructor with dynamic size
    Stack(int size) {
        maxsize = size;
        arr = new int[maxsize];
        top = -1;
    }

    // Function to check if Stack is Empty
    void isEmpty() {
        if (top == -1) {
            System.out.println("Array Stack is Empty");
        } else {
            System.out.println("Array Stack is not Empty");
        }
    }

    // Function to check if Stack is Full 
    void isFull() {
        if (top == (maxsize - 1)) {
            System.out.println("Array Stack is Full");
        } else {
            System.out.println("Array Stack is not Full");
        }
    }

    // Function to push Element in the Stack 
    void push(Scanner sc) {
        if (top == (maxsize - 1)) {
            System.out.println("Array Stack is Overflow");
        } else {
            System.out.print("Enter the Value to push in the Stack: ");
            int val = sc.nextInt();
            System.out.println();
            top++;
            arr[top] = val;
            System.out.println("Item Pushed is: " + val);
        }
    }

    // Function to pop Element from the Stack 
    void pop() {
        if (top == -1) {
            System.out.println("Array Stack is Underflow");
        } else {
            System.out.println("Item Popped is: " + arr[top]);
            top--;
        }
    }

    // Function to display Elements in the Stack
    void display() {
        if (top == -1) {
            System.out.println("Array Stack is Empty");
        } else {
            System.out.println("Printing Array Stack Elements:");
            for (int i = top; i >= 0; i--) {
                System.out.println("Element [" + i + "]: " + arr[i]);
            }
        }
    }
}

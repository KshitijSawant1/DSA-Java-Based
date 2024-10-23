import java.util.Scanner;

class HashTable {
    private int[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        table = new int[size];
        for (int i = 0; i < size; i++) {
            table[i] = -1; // Initialize all table slots as empty
        }
    }

    // Hash function to find index
    private int hashFunction(int key) {
        return key % size;
    }

    // Linear probing for collision resolution
    private int linearProbe(int key) {
        int index = hashFunction(key);
        int i = 0;
        while (table[(index + i) % size] != -1 && i < size) {
            i++;
        }
        return (index + i) % size;
    }

    // Insert key into the hash table
    public void insert(int key) {
        int index = hashFunction(key);
        if (table[index] == -1) {
            table[index] = key;
            System.out.println("Inserted " + key + " at index " + index);
        } else {
            System.out.println("Collision occurred for " + key + " at index " + index);
            int newIndex = linearProbe(key);
            table[newIndex] = key;
            System.out.println("Inserted " + key + " at index " + newIndex);
        }
        System.out.println(); // Add space between outputs
    }

    // Search key in the hash table
    public boolean search(int key) {
        int index = hashFunction(key);
        int i = 0;
        while (table[(index + i) % size] != key && table[(index + i) % size] != -1 && i < size) {
            i++;
        }
        return table[(index + i) % size] == key;
    }

    // Display the hash table
    public void display() {
        System.out.println("Hash Table:");
        for (int i = 0; i < size; i++) {
            if (table[i] != -1) {
                System.out.println("Index " + i + ": " + table[i]);
            } else {
                System.out.println("Index " + i + ": Empty");
            }
        }
        System.out.println(); // Add space between outputs
    }
}

public class HashingDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the hash table: ");
        int size = sc.nextInt();
        HashTable hashTable = new HashTable(size);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Insert a key");
            System.out.println("2. Search a key");
            System.out.println("3. Display the hash table");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                    int key = sc.nextInt();
                    hashTable.insert(key);
                    break;
                case 2:
                    System.out.print("Enter key to search: ");
                    int searchKey = sc.nextInt();
                    if (hashTable.search(searchKey)) {
                        System.out.println("Key " + searchKey + " found.");
                    } else {
                        System.out.println("Key " + searchKey + " not found.");
                    }
                    System.out.println(); // Add space between outputs
                    break;
                case 3:
                    hashTable.display();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

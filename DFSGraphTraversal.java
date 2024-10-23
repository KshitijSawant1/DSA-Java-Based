import java.util.*;

// Class representing the GraphDFS
class GraphDFS {
    private int vertices; // Number of vertices
    private LinkedList<Integer> adjList[]; // Adjacency list

    // Constructor
    public GraphDFS(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];

        // Initialize each adjacency list
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add edge to the GraphDFS
    public void addEdge(int src, int dest) {
        adjList[src].add(dest); // Add dest to src's list
        adjList[dest].add(src); // For an undirected GraphDFS
    }

    // Perform DFS traversal
    public void DFS(int startVertex) {
        // Create a boolean array to mark visited vertices
        boolean visited[] = new boolean[vertices];

        // Call the recursive helper method to start DFS traversal
        System.out.println("DFS traversal starting from vertex " + startVertex + ":");
        DFSUtil(startVertex, visited);
        System.out.println();
    }

    // A recursive function to perform DFS traversal
    private void DFSUtil(int vertex, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[vertex] = true;
        System.out.print(vertex + " ");

        // Recur for all adjacent vertices
        for (int adjVertex : adjList[vertex]) {
            if (!visited[adjVertex]) {
                DFSUtil(adjVertex, visited);
            }
        }
    }

    // Display the adjacency list (GraphDFS structure)
    public void displayGraphDFS() {
        System.out.println("GraphDFS adjacency list:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + ": ");
            for (int adjVertex : adjList[i]) {
                System.out.print(adjVertex + " ");
            }
            System.out.println();
        }
    }
}

public class DFSGraphTraversal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take the number of vertices from the user
        System.out.print("Enter the number of vertices in the graph: ");
        int vertices = sc.nextInt();

        // Create a graph
        GraphDFS graph = new GraphDFS(vertices);
        System.out.println("\nGraph Operations Menu:");
        System.out.println("1. Add edge");
        System.out.println("2. Display graph (Adjacency list)");
        System.out.println("3. Perform DFS traversal");
        System.out.println("4. Exit");

        // User interaction menu
        while (true) {
            System.out.print("Choose an option (1-4): ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // Add an edge to the graph
                    System.out.print("Enter source vertex: ");
                    int src = sc.nextInt();
                    System.out.print("Enter destination vertex: ");
                    int dest = sc.nextInt();
                    graph.addEdge(src, dest);
                    break;

                case 2:
                    // Display the adjacency list
                    graph.displayGraphDFS();
                    break;

                case 3:
                    // Perform DFS traversal
                    System.out.print("Enter starting vertex for DFS traversal: ");
                    int startVertex = sc.nextInt();
                    graph.DFS(startVertex);
                    break;

                case 4:
                    // Exit the program
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please choose between 1 and 4.");
            }
        }
    }
}

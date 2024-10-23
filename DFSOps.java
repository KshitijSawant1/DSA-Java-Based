import java.util.*;

class GraphDFS {
    private int vertices;
    private LinkedList<Integer> adjList[];

    public GraphDFS(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src);
    }

    public void DFS(int startVertex) {
        boolean visited[] = new boolean[vertices];
        System.out.println("DFS traversal starting from vertex " + startVertex + ":");
        DFSUtil(startVertex, visited);
        System.out.println();
    }

    private void DFSUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int adjVertex : adjList[vertex]) {
            if (!visited[adjVertex]) {
                DFSUtil(adjVertex, visited);
            }
        }
    }

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

public class DFSOps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the number of vertices in the graph: ");
        int vertices = sc.nextInt();

        GraphDFS graph = new GraphDFS(vertices);
        System.out.println("\nGraph Operations Menu:");
        System.out.println("1. Add edge");
        System.out.println("2. Display graph (Adjacency list)");
        System.out.println("3. Perform DFS traversal");
        System.out.println("4. Exit");

        while (true) {
            System.out.println();
            System.out.print("Choose an option (1-4): ");
            int choice = sc.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.print("Enter source vertex: ");
                    int src = sc.nextInt();
                    System.out.print("Enter destination vertex: ");
                    int dest = sc.nextInt();
                    graph.addEdge(src, dest);
                    break;
                case 2:
                    graph.displayGraphDFS();
                    break;
                case 3:
                    System.out.print("Enter starting vertex for DFS traversal: ");
                    int startVertex = sc.nextInt();
                    graph.DFS(startVertex);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please choose between 1 and 4.");
            }
        }
    }
}

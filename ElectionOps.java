import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ElectionOps {
    // HashMap to store voter registry (Voter ID and their names)
    static HashMap<String, String> voterRegistry = new HashMap<>();

    // Array of candidates
    static String[] candidates = {"Alice", "Bob", "Charlie"};

    // Array to keep track of vote counts for each candidate
    static int[] voteCount = new int[3];

    // HashSet to track voters who have already voted to prevent duplicates
    static HashSet<String> votersWhoVoted = new HashSet<>();

    // Initializing the voter registry with voter ID and names
    static {
        voterRegistry.put("V001", "Alice");
        voterRegistry.put("V002", "Bob");
        voterRegistry.put("V003", "Charlie");
    }

    // Method to authenticate voter using their Voter ID
    public static boolean authenticateVoter(String voterID) {
        if (voterRegistry.containsKey(voterID)) {
            System.out.println("Voter " + voterRegistry.get(voterID) + " authenticated.");
            return true;
        } else {
            System.out.println("Voter not found.");
            return false;
        }
    }

    // Method to check if a voter has already voted
    public static boolean checkDuplicate(String voterID) {
        if (votersWhoVoted.contains(voterID)) {
            System.out.println("Voter " + voterID + " has already voted!");
            return false;
        } else {
            votersWhoVoted.add(voterID);
            return true;
        }
    }

    // Method to cast a vote for a given candidate
    public static void castVote(String voterID, int candidateIndex) {
        if (authenticateVoter(voterID)) {
            voteCount[candidateIndex]++;  // Increment vote count for the selected candidate
            System.out.println("Vote cast for " + candidates[candidateIndex]);
        }
    }

    // Voting system with duplicate check to prevent voters from voting multiple times
    public static void voteSystem(String voterID, int candidateIndex) {
        if (checkDuplicate(voterID)) {
            castVote(voterID, candidateIndex);
        } else {
            System.out.println("Duplicate vote detected for " + voterID);
        }
    }

    // Method to simulate encryption of votes using SHA-256 for secure voting
    public static String encryptVote(String vote) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(vote.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));  // Convert byte to hexadecimal
            }
            return hexString.toString();  // Return the encrypted vote as a hexadecimal string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to cast a secure vote, encrypting the vote before casting
    public static void secureVote(String voterID, int candidateIndex) {
        String vote = voterID + "-" + candidates[candidateIndex];  // Combine voterID and candidate
        String encryptedVote = encryptVote(vote);  // Encrypt the vote
        System.out.println("Encrypted vote: " + encryptedVote);
    }

    // Method to display the final vote count for all candidates
    public static void displayResults() {
        System.out.println("Final Vote Count: ");
        for (int i = 0; i < candidates.length; i++) {
            System.out.println(candidates[i] + ": " + voteCount[i] + " votes");
        }
    }

    // Main method for the menu-driven voting system
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Menu-driven interface
        while (true) {
            System.out.println("\nVoting System Menu:");
            System.out.println("1. Cast a vote");
            System.out.println("2. Cast a secure vote");
            System.out.println("3. Show results");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();  // Get user input for menu selection

            switch (choice) {
                case 1:
                    // Option to cast a regular vote
                    System.out.print("Enter Voter ID: ");
                    String voterID = sc.next();  // Get voter ID input
                    System.out.println("Choose a candidate: ");
                    for (int i = 0; i < candidates.length; i++) {
                        System.out.println((i + 1) + ". " + candidates[i]);  // List candidates
                    }
                    System.out.print("Enter your choice (1-" + candidates.length + "): ");
                    int candidateIndex = sc.nextInt() - 1;  // Select candidate
                    if (candidateIndex >= 0 && candidateIndex < candidates.length) {
                        voteSystem(voterID, candidateIndex);  // Cast vote
                    } else {
                        System.out.println("Invalid candidate choice.");
                    }
                    break;

                case 2:
                    // Option to cast a secure vote with encryption
                    System.out.print("Enter Voter ID: ");
                    String secureVoterID = sc.next();  // Get voter ID
                    System.out.println("Choose a candidate: ");
                    for (int i = 0; i < candidates.length; i++) {
                        System.out.println((i + 1) + ". " + candidates[i]);  // List candidates
                    }
                    System.out.print("Enter your choice (1-" + candidates.length + "): ");
                    int secureCandidateIndex = sc.nextInt() - 1;  // Select candidate
                    if (secureCandidateIndex >= 0 && secureCandidateIndex < candidates.length) {
                        secureVote(secureVoterID, secureCandidateIndex);  // Cast secure vote
                    } else {
                        System.out.println("Invalid candidate choice.");
                    }
                    break;

                case 3:
                    // Option to show the results of the voting
                    displayResults();
                    break;

                case 4:
                    // Exit the system
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select from the menu.");
            }
        }
    }
}


/*
1. Cast a Vote
Description: This option allows the user to cast a vote for one of the candidates. 
            Before casting a vote, the system checks if the voter is registered and if they have already voted (to prevent duplicate voting). If the voter passes the checks, the vote is cast and counted.

Key Methods Involved:

authenticateVoter(String voterID)
checkDuplicate(String voterID)
voteSystem(String voterID, int candidateIndex) 

Explanation:

The system prompts the user for their voter ID and candidate choice.
The candidate list is printed, and the user selects their candidate by entering the corresponding number.
The voteSystem method checks if the voter is registered and if they have already voted, using the authenticateVoter and checkDuplicate methods.
If all checks pass, the vote is cast, and the vote count for the chosen candidate is updated.


2. Cast a Secure Vote
Description: This option allows the user to cast a secure vote. The vote is encrypted using the SHA-256 algorithm before being recorded. The process ensures the vote is securely hashed, which simulates how a secure voting system would operate.

Key Methods Involved:

secureVote(String voterID, int candidateIndex)
encryptVote(String vote)

Explanation:

The user is prompted to enter their voter ID and select a candidate just like in Option 1.
The secureVote method is called.
The secureVote method combines the voter ID and candidate choice into a string and passes it to the encryptVote method.
The encryptVote method hashes the vote string using the SHA-256 algorithm and prints the encrypted vote.


3. Show Results
Description: This option displays the current vote count for each candidate. It is a simple option that shows the results of the voting process so far.

Key Method Involved:

displayResults()

Explanation:

When this option is selected, the displayResults method is called.
The method iterates through the list of candidates and prints the current vote count for each.

Explanation:

The loop iterates over each candidate, and their corresponding vote count is printed.
This provides real-time feedback on how many votes each candidate has received.

How It Works:
SHA-256 is a cryptographic hash function that generates a fixed-size (256-bit) hash value for any input.
In the code, each vote (which is represented as a combination of the voter ID and the candidate) is hashed using SHA-256.
The hashed vote is displayed as a hexadecimal string, which simulates how votes might be encrypted in a real-world secure voting system.
 */
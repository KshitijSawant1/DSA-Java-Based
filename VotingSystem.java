import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VotingSystem {

    static HashMap<String, String> voterRegistry = new HashMap<>();
    static String[] candidates = {"Alice", "Bob", "Charlie"};
    static int[] voteCount = new int[3];
    static HashSet<String> votersWhoVoted = new HashSet<>();

    static {
        voterRegistry.put("V001", "Alice");
        voterRegistry.put("V002", "Bob");
        voterRegistry.put("V003", "Charlie");
        voterRegistry.put("V004", "Derek");
        voterRegistry.put("V005", "Emma");
        voterRegistry.put("V006", "Felix");
        voterRegistry.put("V007", "Grace");
        voterRegistry.put("V008", "Henry");
        voterRegistry.put("V009", "Ivan");

    }

    public static boolean authenticateVoter(String voterID) {
        if (voterRegistry.containsKey(voterID)) {
            System.out.println("Voter " + voterRegistry.get(voterID) + " authenticated.");
            return true;
        } else {
            System.out.println("Voter not found.");
            return false;
        }
    }

    public static boolean checkDuplicate(String voterID) {
        if (votersWhoVoted.contains(voterID)) {
            System.out.println("Voter " + voterID + " has already voted!");
            return false;
        } else {
            votersWhoVoted.add(voterID);  // Add the voter to the HashSet after their first vote
            return true;
        }
    }

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

    public static void secureVote(String voterID, int candidateIndex) {
        if (authenticateVoter(voterID) && checkDuplicate(voterID)) {
            String vote = voterID + "-" + candidates[candidateIndex];  // Combine voterID and candidate
            String encryptedVote = encryptVote(vote);  // Encrypt the vote
            voteCount[candidateIndex]++;  // Increment vote count for the selected candidate
            printSeparator(false);
            System.out.println("Encrypted vote: " + encryptedVote);
            System.out.println("Vote cast securely for " + candidates[candidateIndex]);
            printSeparator(false);
        }
    }

    public static void displayResults() {
        printSeparator(false);
        System.out.println("Final Vote Count: ");
        for (int i = 0; i < candidates.length; i++) {
            System.out.println(candidates[i] + ": " + voteCount[i] + " votes");
        }
        printSeparator(false);
    }

    public static void printSeparator(boolean isBlank) {
        if (isBlank) {
            System.out.println();  // Print a blank line
        } else {
            System.out.println("------------------------------------------");  // Print separator
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printSeparator(true);
            printSeparator(false);
            System.out.println("Voting System Menu:");
            printSeparator(false);
            System.out.println("1. Cast a secure vote");
            System.out.println("2. Show results");
            System.out.println("3. Exit");
            printSeparator(false);
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            printSeparator(true);

            switch (choice) {
                case 1:
                    System.out.print("Enter Voter ID: ");
                    String secureVoterID = sc.next();
                    System.out.println("Choose a candidate: ");
                    for (int i = 0; i < candidates.length; i++) {
                        System.out.println((i + 1) + ". " + candidates[i]);
                    }
                    System.out.print("Enter your choice (1-" + candidates.length + "): ");
                    int secureCandidateIndex = sc.nextInt() - 1;
                    if (secureCandidateIndex >= 0 && secureCandidateIndex < candidates.length) {
                        secureVote(secureVoterID, secureCandidateIndex);
                    } else {
                        printSeparator(false);
                        System.out.println("Invalid candidate choice.");
                        printSeparator(false);
                    }
                    break;

                case 2:
                    displayResults();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    printSeparator(false);
                    System.out.println("Invalid choice. Please select from the menu.");
                    printSeparator(false);
            }
        }
    }
}

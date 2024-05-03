import java.util.Scanner;
import java.util.HashMap;

public class HostelManagementSystem {
    // Sample HashMap to store student records
    private static HashMap<Integer, String> studentRecords = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isAdmin = false;
        String adminUsername = "admin";
        String adminPassword = "password";
        
        // Login system
        System.out.println("Welcome to Hostel Management System!");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            isAdmin = true;
            System.out.println("Admin login successful!");
        } else {
            System.out.println("User login successful!");
        }
        
        // Menu options
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Enter Student Details");
            System.out.println("2. Delete Student Record");
            System.out.println("3. Room Allocation Record");
            System.out.println("4. Billing and Payment System");
            System.out.println("5. Inventory Management");
            System.out.println("6. Visitors Management");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    enterStudentDetails(scanner);
                    break;
                case 2:
                    deleteStudentRecord(scanner);
                    break;
                case 3:
                    roomAllocationRecord(scanner);
                    break;
                case 4:
                    billingAndPaymentSystem(scanner);
                    break;
                case 5:
                    inventoryManagement(scanner);
                    break;
                case 6:
                    visitorsManagement(scanner);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 7.");
            }
        }
    }
    
    // Method to enter student details
    public static void enterStudentDetails(Scanner scanner) {
        System.out.println("Enter Student Details:");
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        studentRecords.put(studentId, studentName);
        System.out.println("Student details added successfully.");
    }
    
    // Method to delete student record
    public static void deleteStudentRecord(Scanner scanner) {
        System.out.println("Delete Student Record:");
        System.out.print("Enter Student ID to delete: ");
        int studentId = scanner.nextInt();
        if (studentRecords.containsKey(studentId)) {
            studentRecords.remove(studentId);
            System.out.println("Student record deleted successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }
    
    // Method for room allocation record
    public static void roomAllocationRecord(Scanner scanner) {
        System.out.println("Room Allocation Record:");
        // Add your code here for room allocation record
    }
    
    // Method for billing and payment system
    public static void billingAndPaymentSystem(Scanner scanner) {
        System.out.println("Billing and Payment System:");
        // Add your code here for billing and payment system
    }
    
    // Method for inventory management
    public static void inventoryManagement(Scanner scanner) {
        System.out.println("Inventory Management:");
        // Add your code here for inventory management
    }
    
    // Method for visitors management
    public static void visitorsManagement(Scanner scanner) {
        System.out.println("Visitors Management:");
        // Add your code here for visitors management
    }
}

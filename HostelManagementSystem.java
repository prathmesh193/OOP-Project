import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Clear {
    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }
}

public class HostelManagementSystem extends Clear{
    private static HashMap<Integer, String> studentRecords = new HashMap<>();
    private static HashMap<String, Integer> inventory = new HashMap<>();
    private static HashMap<Integer, Integer> roomAllocationRecords = new HashMap<>();
    private static HashMap<Integer, String> billingAndPaymentRecords = new HashMap<>();
    private static HashMap<Integer, String> visitorLog = new HashMap<>(); 
    static Clear c= new Clear();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isAdmin = false;
        String adminUsername = "admin";
        String adminPassword = "password";
    
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
        try{
            Thread.sleep(2000);
        }
        catch(Exception e){
            System.out.println(e);
        }
        c.cls();
        while (true) {
            System.out.println("\n********Menu********");
            System.out.println("1. Manage Students.");
            System.out.println("2. Room Allocation Record");
            System.out.println("3. Billing and Payment System");
            System.out.println("4. Inventory Management");
            System.out.println("5. Visitors Management");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    c.cls();
                    manageStudentDetails(scanner);
                    break;
                case 2:
                    c.cls();
                    roomAllocationRecord(scanner, isAdmin); 
                    break;
                case 3:
                    c.cls();
                    billingAndPaymentSystem(scanner, isAdmin); 
                    break;
                case 4:
                    c.cls();
                    inventoryManagement(scanner, isAdmin);
                    break;
                case 5:
                    c.cls();
                    visitorsManagement(scanner);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        }
    }

    public static void manageStudentDetails(Scanner scanner) {
        System.out.println("Student Details:");
        System.out.println("1. Enter Student Details");
        System.out.println("2. Delete Student Record");
        System.out.println("3. Display Student Details");
        System.out.print("Enter your choice: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                enterStudentDetails(scanner);
                break;
            case 2:
                deleteStudentRecord(scanner);
                break;
            case 3:
                displayStudentDetails();
                break;
            default:
                System.out.println("Invalid choice! Please enter a number between 1 and 3.");
        }
    }

    public static void enterStudentDetails(Scanner scanner) {
        System.out.println("Enter Student Details:");
        System.out.print("Enter Student ID: ");
        int studentId;
        try {
            studentId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Student ID must be an integer.");
            return;
        }
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        studentRecords.put(studentId, studentName);
        System.out.println("Student details added successfully.");
    }

    public static void deleteStudentRecord(Scanner scanner) {
        System.out.println("Delete Student Record:");
        System.out.print("Enter Student ID to delete: ");
        int studentId;
        try {
            studentId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Student ID must be an integer.");
            return;
        }
        if (studentRecords.containsKey(studentId)) {
            studentRecords.remove(studentId);
            System.out.println("Student record deleted successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    public static void displayStudentDetails() {
        System.out.println("Student Details:");
        for (Map.Entry<Integer, String> entry : studentRecords.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }

    public static void roomAllocationRecord(Scanner scanner, boolean isAdmin) {
        System.out.println("Room Allocation Record:");
        if (isAdmin) {
            System.out.println("1. Add room allocation record");
            System.out.println("2. View room allocation records");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                return;
            }

            switch (choice) {
                case 1:
                    addRoomAllocationRecord(scanner);
                    break;
                case 2:
                    viewRoomAllocationRecords();
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 2.");
            }
        } else {
            System.out.println("Access denied! Please login as admin to access this feature.");
        }
    }

    public static void addRoomAllocationRecord(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int studentId;
        try {
            studentId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Student ID must be an integer.");
            return;
        }
        System.out.print("Enter Room Number: ");
        int roomNumber;
        try {
            roomNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Room number must be an integer.");
            return;
        }

        if (roomAllocationRecords.containsKey(studentId)) {
            System.out.println("The student is already allocated to a room. Please delete the existing record before adding a new one.");
        } else {
            roomAllocationRecords.put(studentId, roomNumber);
            System.out.println("Room allocation record added successfully.");
        }
    }

    public static void viewRoomAllocationRecords() {
        System.out.println("Room Allocation Records:");
        for (Map.Entry<Integer, Integer> entry : roomAllocationRecords.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + ", Room Number: " + entry.getValue());
        }
    }

    public static void billingAndPaymentSystem(Scanner scanner, boolean isAdmin) {
        System.out.println("Billing and Payment System:");
        if (isAdmin) {
            System.out.println("1. Add billing and payment record");
            System.out.println("2. View billing and payment records");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                return;
            }

            switch (choice) {
                case 1:
                    addBillingAndPaymentRecord(scanner);
                    break;
                case 2:
                    viewBillingAndPaymentRecords();
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 2.");
            }
        } else {
            System.out.println("Access denied! Please login as admin to access this feature.");
        }
    }

    public static void addBillingAndPaymentRecord(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int studentId;
        try {
            studentId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Student ID must be an integer.");
            return;
        }
        System.out.print("Enter Billing and Payment Details: ");
        String billingAndPaymentDetails = scanner.nextLine();

        if (billingAndPaymentRecords.containsKey(studentId)) {
            System.out.println("The student already has a billing and payment record. Please delete the existing record before adding a new one.");
        } else {
            billingAndPaymentRecords.put(studentId, billingAndPaymentDetails);
            System.out.println("Billing and payment record added successfully.");
        }
    }

    public static void viewBillingAndPaymentRecords() {
        System.out.println("Billing and Payment Records:");
        for (Map.Entry<Integer, String> entry : billingAndPaymentRecords.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + ", Billing and Payment Details: " + entry.getValue());
        }
    }

    public static void inventoryManagement(Scanner scanner, boolean isAdmin) {
        System.out.println("Inventory Management:");
        if (isAdmin) {
            System.out.println("1. Add inventory item");
            System.out.println("2. Update inventory item quantity");
            System.out.println("3. View inventory");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                return;
            }

            switch (choice) {
                case 1:
                    addInventoryItem(scanner);
                    break;
                case 2:
                    updateInventoryItem(scanner);
                    break;
                case 3:
                    viewInventory();
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 3.");
            }
        } else {
            System.out.println("Access denied! Please login as admin to access this feature.");
        }
    }

    public static void addInventoryItem(Scanner scanner) {
        System.out.print("Enter the name of the inventory item: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter the quantity of the inventory item: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Quantity must be an integer.");
            return;
        }

        if (inventory.containsKey(itemName)) {
            System.out.println("Inventory item already exists. Updating quantity...");
            int currentQuantity = inventory.get(itemName);
            inventory.put(itemName, currentQuantity + quantity);
        } else {
            inventory.put(itemName, quantity);
        }

        System.out.println("Inventory item added successfully.");
    }

    public static void updateInventoryItem(Scanner scanner) {
        System.out.print("Enter the name of the inventory item to update: ");
        String itemName = scanner.nextLine();
        if (inventory.containsKey(itemName)) {
            System.out.print("Enter the new quantity of the inventory item: ");
            int newQuantity;
            try {
                newQuantity = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Quantity must be an integer.");
                return;
            }
            inventory.put(itemName, newQuantity);
            System.out.println("Inventory item quantity updated successfully.");
        } else {
            System.out.println("Inventory item not found.");
        }
    }

    public static void viewInventory() {
        System.out.println("Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Item: " + entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }

    public static void visitorsManagement(Scanner scanner) {
        System.out.println("Visitors Management:");
        System.out.println("1. Add Visitor");
        System.out.println("2. View Visitor Log");
        System.out.print("Enter your choice: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                addVisitor(scanner);
                break;
            case 2:
                viewVisitorLog();
                break;
            default:
                System.out.println("Invalid choice! Please enter either 1 or 2.");
        }
    }

    public static void addVisitor(Scanner scanner) {
        System.out.print("Enter Visitor Name: ");
        String visitorName = scanner.nextLine();
        System.out.print("Enter Visiting Student ID: ");
        int studentId;
        try {
            studentId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Student ID must be an integer.");
            return;
        }
        System.out.print("Enter Purpose of Visit: ");
        String purpose = scanner.nextLine();

        visitorLog.put(studentId, "Visitor Name: " + visitorName + ", Purpose of Visit: " + purpose);
        System.out.println("Visitor Added:");
        System.out.println("Visitor Name: " + visitorName);
        System.out.println("Visiting Student ID: " + studentId);
        System.out.println("Purpose of Visit: " + purpose);
    }

    public static void viewVisitorLog() {
        System.out.println("Visitor Log:");
        for (Map.Entry<Integer, String> entry : visitorLog.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + ", Visitor Details: " + entry.getValue());
        }
    }
}

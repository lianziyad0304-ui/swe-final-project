import java.util.Scanner;

public class PetCareCenter {

    // Accounts for demo (you can change passwords)
    private static final String STAFF_USER = "staff";
    private static final String STAFF_PASS = "1234";
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "1234";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Pet Care Center System ===");

        // -------- LOGIN (Use Case 1) --------
        String role = loginAndGetRole(sc);

        if (role == null) {
            System.out.println("Login failed! Wrong username or password.");
            sc.close();
            return;
        }

        System.out.println("Login successful! Role: " + role + "\n");

        // -------- MAIN MENU --------
        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println("1) Register Pet (Staff only)");
            System.out.println("2) Logout");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                // Use Case 2: Register Pet (Staff only)
                if (!role.equals("staff")) {
                    System.out.println("Access denied! Only Staff can register pets.\n");
                    continue;
                }
                registerPetFlow(sc);

            } else if (choice.equals("2")) {
                System.out.println("Logged out.\n");
                // يرجّعك لتسجيل الدخول من جديد
                role = loginAndGetRole(sc);
                if (role == null) {
                    System.out.println("Login failed! Wrong username or password.");
                    break;
                }
                System.out.println("Login successful! Role: " + role + "\n");

            } else if (choice.equals("0")) {
                System.out.println("Bye!");
                break;

            } else {
                System.out.println("Invalid choice.\n");
            }
        }

        sc.close();
    }

    // ---------- Use Case 1: Login ----------
    private static String loginAndGetRole(Scanner sc) {
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String username = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        if (username.equals(STAFF_USER) && password.equals(STAFF_PASS)) return "staff";
        if (username.equals(ADMIN_USER) && password.equals(ADMIN_PASS)) return "admin";
        return null;
    }

    // ---------- Use Case 2: Register Pet ----------
    private static void registerPetFlow(Scanner sc) {
        System.out.println("\n=== Register Pet ===");

        System.out.print("Pet name: ");
        String name = sc.nextLine().trim();

        System.out.print("Pet type: ");
        String type = sc.nextLine().trim();

        int age = readPositiveInt(sc, "Pet age: ");

        System.out.print("Owner name: ");
        String owner = sc.nextLine().trim();

        boolean ok = registerPet(name, type, age, owner);

        if (ok) {
            System.out.println("Pet registered successfully!\n");
        } else {
            System.out.println("Invalid input! Please try again.\n");
        }
    }

    private static boolean registerPet(String name, String type, int age, String owner) {
        // Validation
        if (name.isEmpty() || type.isEmpty() || owner.isEmpty()) return false;
        if (age <= 0) return false;

        // (No DB) Just display saved data
        System.out.println("\n--- Pet Info ---");
        System.out.println("Pet Name : " + name);
        System.out.println("Pet Type : " + type);
        System.out.println("Pet Age  : " + age);
        System.out.println("Owner    : " + owner);
        System.out.println("--------------");
        return true;
    }

    // Helper: ensures age is valid number > 0
    private static int readPositiveInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(s);
                if (value > 0) return value;
                System.out.println("Age must be > 0.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
}
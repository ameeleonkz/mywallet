import java.util.*;

public class Main {
    private static final FinanceManager financeManager = new FinanceManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        financeManager.loadData();
        while (true) {
            if (currentUser == null) {
                System.out.println("1. Login\n2. Register\n3. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
            } else {
                showMainMenu();
            }
        }
    }

    private static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        currentUser = financeManager.login(username, password);
        if (currentUser == null) {
            System.out.println("Invalid credentials!");
        } else {
            System.out.println("Welcome, " + currentUser.getUsername() + "!");
        }
    }

    private static void register() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (financeManager.register(username, password)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists!");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n1. Add Income\n2. Add Expense\n3. View Balance\n4. Set Budget\n5. View Statistics\n6. Logout\n");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addIncome();
                break;
            case 2:
                addExpense();
                break;
            case 3:
                viewBalance();
                break;
            case 4:
                setBudget();
                break;
            case 5:
                viewStatistics();
                break;
            case 6:
                logout();
                break;
        }
    }

    private static double getValidAmount() {
        while (true) {
            try {
                String input = scanner.nextLine();
                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    System.out.print("Amount must be positive. Try again: ");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount. Please enter a number: ");
            }
        }
    }

    private static void addIncome() {
        System.out.print("Amount: ");
        double amount = getValidAmount();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        
        financeManager.addIncome(currentUser, amount, category);
        System.out.println("Income added successfully!");
    }

    private static void addExpense() {
        System.out.print("Amount: ");
        double amount = getValidAmount();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        
        financeManager.addExpense(currentUser, amount, category);
    }

    private static void viewBalance() {
        Wallet wallet = currentUser.getWallet();
        System.out.println("Current balance: " + wallet.getBalance());
        System.out.println("Total income: " + wallet.getTotalIncome());
        System.out.println("Total expenses: " + wallet.getTotalExpenses());
    }

    private static void setBudget() {
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Budget limit: ");
        double limit = scanner.nextDouble();
        
        financeManager.setBudget(currentUser, category, limit);
        System.out.println("Budget set successfully!");
    }

    private static void viewStatistics() {
        financeManager.showStatistics(currentUser);
    }

    private static void logout() {
        financeManager.saveData();
        currentUser = null;
        System.out.println("Logged out successfully!");
    }
}

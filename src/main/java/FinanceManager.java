import java.util.*;
import java.io.*;
import com.google.gson.*;

public class FinanceManager {
    private Map<String, User> users = new HashMap<>();

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password));
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void addIncome(User user, double amount, String category) {
        user.getWallet().addIncome(amount, category);
    }

    public void addExpense(User user, double amount, String category) {
        user.getWallet().addExpense(amount, category);
    }

    public void setBudget(User user, String category, double limit) {
        user.getWallet().setBudget(category, limit);
    }

    public void showStatistics(User user) {
        Wallet wallet = user.getWallet();
        System.out.println("\nSTATISTICS:");
        System.out.println("Total balance: " + wallet.getBalance());
        
        System.out.println("\nINCOMES BY CATEGORY:");
        wallet.getCategoryIncomes().forEach((category, amount) ->
            System.out.println(category + ": " + amount));

        System.out.println("\nEXPENSES BY CATEGORY:");
        wallet.getCategoryExpenses().forEach((category, amount) -> {
            System.out.println(category + ": " + amount);
            Double budget = wallet.getCategoryBudgets().get(category);
            if (budget != null) {
                System.out.println("Budget: " + budget + " (Remaining: " + (budget - amount) + ")");
            }
        });
    }

    public void saveData() {
        Gson gson = new Gson();
        File dataDir = new File("userdata");
        dataDir.mkdir();
        
        for (User user : users.values()) {
            try (FileWriter writer = new FileWriter("userdata/" + user.getUsername() + ".json")) {
                gson.toJson(user, writer);
            } catch (IOException e) {
                System.out.println("Error saving data for user: " + user.getUsername());
            }
        }
    }

    public void loadData() {
        Gson gson = new Gson();
        File dataDir = new File("userdata");
        if (!dataDir.exists()) return;

        for (File file : dataDir.listFiles()) {
            if (file.getName().endsWith(".json")) {
                try (FileReader reader = new FileReader(file)) {
                    User user = gson.fromJson(reader, User.class);
                    users.put(user.getUsername(), user);
                } catch (IOException e) {
                    System.out.println("Error loading data from: " + file.getName());
                }
            }
        }
    }
}

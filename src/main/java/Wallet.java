import java.util.*;

public class Wallet {
    private double balance = 0;
    private Map<String, Double> categoryBudgets = new HashMap<>();
    private Map<String, Double> categoryExpenses = new HashMap<>();
    private Map<String, Double> categoryIncomes = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void addIncome(double amount, String category) {
        balance += amount;
        categoryIncomes.merge(category, amount, Double::sum);
        transactions.add(new Transaction(TransactionType.INCOME, amount, category));
    }

    public void addExpense(double amount, String category) {
        if (balance >= amount) {
            balance -= amount;
            categoryExpenses.merge(category, amount, Double::sum);
            transactions.add(new Transaction(TransactionType.EXPENSE, amount, category));
            
            Double budget = categoryBudgets.get(category);
            if (budget != null && categoryExpenses.get(category) > budget) {
                System.out.println("WARNING: Budget exceeded for category: " + category);
            }
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void setBudget(String category, double limit) {
        categoryBudgets.put(category, limit);
    }

    public double getBalance() { return balance; }
    public double getTotalIncome() { 
        return categoryIncomes.values().stream().mapToDouble(Double::doubleValue).sum();
    }
    public double getTotalExpenses() {
        return categoryExpenses.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public Map<String, Double> getCategoryBudgets() { return categoryBudgets; }
    public Map<String, Double> getCategoryExpenses() { return categoryExpenses; }
    public Map<String, Double> getCategoryIncomes() { return categoryIncomes; }
}

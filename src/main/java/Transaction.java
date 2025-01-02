public class Transaction {
    private TransactionType type;
    private double amount;
    private String category;

    public Transaction(TransactionType type, double amount, String category) {
        this.type = type;
        this.amount = amount;
        this.category = category;
    }
}

enum TransactionType {
    INCOME, EXPENSE
}

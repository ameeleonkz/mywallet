import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {
    private Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new Wallet();
    }

    @Test
    void testAddIncome() {
        wallet.addIncome(100.0, "Salary");
        assertEquals(100.0, wallet.getBalance());
        assertEquals(100.0, wallet.getTotalIncome());
    }

    @Test
    void testAddExpense() {
        wallet.addIncome(100.0, "Salary");
        wallet.addExpense(50.0, "Food");
        assertEquals(50.0, wallet.getBalance());
        assertEquals(50.0, wallet.getTotalExpenses());
    }

    @Test
    void testInsufficientFunds() {
        wallet.addIncome(50.0, "Salary");
        wallet.addExpense(100.0, "Food");
        assertEquals(50.0, wallet.getBalance());
    }

    @Test
    void testSetBudget() {
        wallet.setBudget("Food", 100.0);
        assertEquals(100.0, wallet.getCategoryBudgets().get("Food"));
    }
}

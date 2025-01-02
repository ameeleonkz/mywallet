import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    @Test
    void testTransactionCreation() {
        Transaction transaction = new Transaction(TransactionType.INCOME, 100.0, "Salary");
        assertNotNull(transaction);
    }
}

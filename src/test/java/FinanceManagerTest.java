import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class FinanceManagerTest {
    private FinanceManager manager;

    @BeforeEach
    void setUp() {
        manager = new FinanceManager();
    }

    @Test
    void testRegister() {
        assertTrue(manager.register("testUser", "password123"));
        assertFalse(manager.register("testUser", "password123")); // Duplicate username
    }

    @Test
    void testLogin() {
        manager.register("testUser", "password123");
        User user = manager.login("testUser", "password123");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());

        assertNull(manager.login("testUser", "wrongPassword"));
        assertNull(manager.login("nonExistentUser", "password123"));
    }

    @Test
    void testAddIncome() {
        manager.register("testUser", "password123");
        User user = manager.login("testUser", "password123");
        manager.addIncome(user, 100.0, "Salary");
        assertEquals(100.0, user.getWallet().getBalance());
    }
}

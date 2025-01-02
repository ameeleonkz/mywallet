import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    void testUserCreation() {
        User user = new User("testUser", "password123");
        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertNotNull(user.getWallet());
    }
}

package cs320;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

    //
    // Helper strings for boundary tests
    //
    private String tenChars() { return "ABCDEFGHIJ"; }          // 10 chars
    private String thirtyChars() { return "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234"; } // 30 chars

    //
    // Positive test: valid contact creation
    //
    @Test
    public void testValidContactCreation() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("12345", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    //
    // Boundary acceptance tests
    //
    @Test
    public void testFirstNameMaxLengthAccepted() {
        Contact c = new Contact("12345", tenChars(), "Doe", "1234567890", "123 Main St");
        assertEquals(tenChars(), c.getFirstName());
    }

    @Test
    public void testLastNameMaxLengthAccepted() {
        Contact c = new Contact("12345", "John", tenChars(), "1234567890", "123 Main St");
        assertEquals(tenChars(), c.getLastName());
    }

    @Test
    public void testAddressMaxLengthAccepted() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", thirtyChars());
        assertEquals(thirtyChars(), c.getAddress());
    }

    @Test
    public void testPhoneExactLengthAccepted() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1234567890", c.getPhone());
    }

    //
    // Null rejection tests
    //
    @Test
    public void testLastNameNullRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", null, "1234567890", "123 Main St");
        });
    }

    @Test
    public void testAddressNullRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "1234567890", null);
        });
    }

    //
    // Setter tests: lastName
    //
    @Test
    public void testSetLastNameValid() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        c.setLastName("Smith");
        assertEquals("Smith", c.getLastName());
    }

    @Test
    public void testSetLastNameInvalidTooLong() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            c.setLastName("ThisLastNameIsWayTooLong");
        });
    }

    @Test
    public void testSetLastNameNullRejected() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            c.setLastName(null);
        });
    }

    //
    // Setter tests: phone
    //
    @Test
    public void testSetPhoneValid() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        c.setPhone("0987654321");
        assertEquals("0987654321", c.getPhone());
    }

    @Test
    public void testSetPhoneInvalidLength() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            c.setPhone("12345");
        });
    }

    @Test
    public void testSetPhoneNullRejected() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            c.setPhone(null);
        });
    }

    //
    // Setter tests: address
    //
    @Test
    public void testSetAddressValid() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        c.setAddress("456 New Road");
        assertEquals("456 New Road", c.getAddress());
    }

    @Test
    public void testSetAddressInvalidTooLong() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            c.setAddress("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
        });
    }

    @Test
    public void testSetAddressNullRejected() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            c.setAddress(null);
        });
    }

    //
    // Immutability test: contactId cannot be changed
    //
    @Test
    public void testContactIdIsImmutable() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");

        // There is no setter, so we confirm immutability by checking only the getter exists
        assertEquals("12345", c.getContactId());

        // And we confirm no setter exists by attempting reflection
        assertThrows(NoSuchMethodException.class, () -> {
            Contact.class.getDeclaredMethod("setContactId", String.class);
        });
    }
}

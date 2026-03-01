package cs320;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
	//
	// testAddContact()
	// - Confirm whether the service can
	//   store a new contact
	//
	@Test
	public void testAddContact() {
	    ContactService service = new ContactService();
	    Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");

	    service.addContact(contact);

	    // No direct getter for list, so test by updating or deleting
	    assertDoesNotThrow(() -> service.updateContact("12345", "Jane", null, null, null));
	}
	
	//
	// testAddDuplicateContact()
	// - Test adding a duplicate ID
	//
	@Test
	public void testAddDuplicateContact() {
	    ContactService service = new ContactService();
	    Contact c1 = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
	    Contact c2 = new Contact("12345", "Jane", "Smith", "0987654321", "456 Oak St");

	    service.addContact(c1);

	    assertThrows(IllegalArgumentException.class, () -> {
	        service.addContact(c2);
	    });
	}

	//
	// testDeleteContact()
	// - Test deleting a contact
	//
	@Test
	public void testDeleteContact() {
	    ContactService service = new ContactService();
	    Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");

	    service.addContact(contact);
	    service.deleteContact("12345");

	    // This should fail to update since the
	    // contact should already be gone at this point
	    assertThrows(IllegalArgumentException.class, () -> {
	        service.updateContact("12345", "Jane", null, null, null);
	    });
	}

	//
	// testUpdateContact()
	// - Verify that updateContact works and that
	//   is using Contact's validation layer
	@Test
	public void testUpdateContact() {
	    ContactService service = new ContactService();
	    Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");

	    service.addContact(contact);
	    service.updateContact("12345", "Jane", "Smith", "1112223333", "411 Bird Ave");

	    // Verify updates by creating a new update call
	    assertDoesNotThrow(() -> service.updateContact("12345", "Jill", null, null, null));
	}
	
	//
	// testUpdateInvalidPhone()
	// - Test to make sure updates throw exceptions
	//
	@Test
	public void testUpdateInvalidPhone() {
	    ContactService service = new ContactService();
	    Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");

	    service.addContact(contact);

	    assertThrows(IllegalArgumentException.class, () -> {
	        service.updateContact("12345", null, null, "999", null);
	    });
	}

	//
	// testUpdateNonexistentContact()
	// - Test updating a contact that doesn't exist
	//
	@Test
	public void testUpdateNonexistentContact() {
	    ContactService service = new ContactService();

	    assertThrows(IllegalArgumentException.class, () -> {
	        service.updateContact("99999", "John", null, null, null);
	    });
	}


}

package cs320;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
	//
	// We'll use an ArrayList to service as 
	// our in-memory storage
	//
	private List<Contact> contacts = new ArrayList<>();
	
	//
	// This method must:
	// - ensure the ID is unique
	// - add the contact if valid
	// - throw an exception if duplicate
	//
	public void addContact(Contact contact) {
	    for (Contact c : contacts) {
	        if (c.getContactId().equals(contact.getContactId())) {
	            throw new IllegalArgumentException("Duplicate contact ID");
	        }
	    }
	    contacts.add(contact);
	}

	//
	// This method must:
	// - remove a contact by ID
	// - do nothing if the ID doesn't exist
	public void deleteContact(String contactId) {
	    contacts.removeIf(c -> c.getContactId().equals(contactId));
	}

	//
	// This method must:
	// - Find the contact
	// - Update only the fields provided
	// - Use the Contact setters to ensure the validation
	//   layer is reused
	public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
	    for (Contact c : contacts) {
	        if (c.getContactId().equals(contactId)) {
	            if (firstName != null) c.setFirstName(firstName);
	            if (lastName != null) c.setLastName(lastName);
	            if (phone != null) c.setPhone(phone);
	            if (address != null) c.setAddress(address);
	            return;
	        }
	    }
	    throw new IllegalArgumentException("Contact not found");
	}


}

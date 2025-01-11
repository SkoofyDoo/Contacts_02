package org.example.contacts.dao;

import org.example.contacts.model.Contact;
import java.util.List;

/**
 * DAO - PATTERN - Data Access Object
 * alle Datenbankzugriffsmethoden (eines Objektes) werden im Interface festgelegt
 * */

public interface ContactDAO {

    /**
     *
     * @param newContact
     * @return true if saved
     */
    //  void -> kein Rückgabewert
    boolean save(Contact newContact);
    /**
     *
     * @return Result-List
     */
    // List ist der Obertyp von Listen (Array, Observable Array usw.)
    // Aus der "Contact" Liste alle Kontakte finden
    List<Contact> findAll();

    /**
     *
     * @param name eg. Max Muster
     * @return find Contacts
     */

    // Methode bei Namen finden (Datentyp / gesuchter Name)
    List<Contact> findByName(String name);

    boolean delete(long id);
}

package org.example.contacts.dao;

import org.example.contacts.model.Contact;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ContactFileDAO implements ContactDAO{

    static ArrayList<Contact> contacts = new ArrayList<>();
    private String count = UUID.randomUUID().toString();
    public static final String FILE_NAME = "contacts.txt";

    /**
     * @param newContact
     * @return true if saved
     */
    @Override
    public boolean save(Contact newContact) {
        // wir schreiben immer eine neue Datei
        try {
            newContact.setId((int) System.currentTimeMillis());
            FileWriter fileWriter = new FileWriter(FILE_NAME, true);
            fileWriter.write(newContact.getId() + "," + newContact.getName() + "," + newContact.getNumber()); // 3,Max Meier,32423242
            fileWriter.write(System.lineSeparator());
            fileWriter.close();//TODO verbessern

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * @return Result-List
     */
    @Override
    public List<Contact> findAll() {
        List<Contact> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(
                            Integer.parseInt(parts[0]), // ID
                            parts[1],                  // Name
                            parts[2]                   // Telefonnummer
                    );
                    result.add(contact);
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param name eg. Max Muster
     * @return find Contacts
     */
    @Override
    public List<Contact> findByName(String name) {
        // Erstmal Kontakte suchen
        List<Contact> contacts = findAll();
        // Dann in einer neuen Liste speichern
        List<Contact> resultList = new ArrayList<>();

        // Schleife für Durchgehen der Liste contacts
        for(Contact c : contacts){
            // Wenn in der Liste der gesuchter Name auftaucht
            if(c.getName().toLowerCase().contains(name.toLowerCase())){
                // in der neuen Liste speichern
                resultList.add(c);
            }
        }
        return resultList;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        // Aus der Liste "Datei" lesen
        List<Contact> listOrg = findAll();
        boolean removed = listOrg.removeIf((c -> c.getId() == id ));
        if(!removed){
            return false;
        }
        // Datei neu schreiben
        try {
            FileWriter fw = new FileWriter(FILE_NAME);
            for (Contact c: listOrg){
                fw.write(c.getId() + "," + c.getName() + "," + c.getNumber());
                fw.write(System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private static void appendToFile(String filename, Contact contact) throws IOException, IOException {
        FileWriter fileWriter = new FileWriter(filename, true);
        fileWriter.write(contact.getId() + "," + contact.getName() + "," + contact.getNumber() + "\n");
        fileWriter.close();
    }

}

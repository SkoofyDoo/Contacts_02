package org.example.contacts.dao;

import org.example.contacts.model.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactDummyDAO implements ContactDAO {



    static ArrayList<Contact> contacts = new ArrayList<>();
    // per default 0
    static int count;

    // wenn etwas nur ein mal existieren soll -> static
    static {
        contacts.add(new Contact(++count, "Max", "333"));
        contacts.add(new Contact(++count, "Dax", "334"));
    }

    /**
     * @param newContact
     * @return true if saved
     */
    @Override
    public boolean save(Contact newContact) {
        newContact.setId(count++);
        System.out.println(count);
        return contacts.add(newContact);


    }

    /**
     * @return Result-List
     */
    @Override
    public List<Contact> findAll() {
        // Datei einlesen
        // neue Liste erzeugen
        ArrayList<Contact> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File("contacts.txt"));

            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] lineArr = line.split(";");
                long id = Long.parseLong(lineArr[0]);
                String name = lineArr[1];
                String number = lineArr[2];
                list.add(new Contact((int) id,name,number));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        return contacts;
    }

    /**
     * @param name eg. Max Muster
     * @return find Contact
     */
    @Override
    public List<Contact> findByName(String name) {
        ArrayList<Contact> resultList = new ArrayList<>();
        for (Contact c : contacts) {
            //TODO start with
            if(c.getName().equals(name)) {
                resultList.add(c);
            }
        } return resultList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) {
        for (Contact c:contacts) {
            if(c.getId() == id){
               return contacts.remove(c);
            }
        }
        return false;
    }
}

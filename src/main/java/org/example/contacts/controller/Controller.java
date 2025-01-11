package org.example.contacts.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.contacts.dao.ContactDAO;
import org.example.contacts.dao.ContactDummyDAO;
import org.example.contacts.dao.ContactFileDAO;
import org.example.contacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private ContactDAO dao;

    public TextField searchField;

    // Der Datentyp soll links stehen
    ArrayList<Contact> contacts = new ArrayList<>();

    @FXML
    private ListView<Contact> listView;

    @FXML
    private TextField nameField;

    @FXML
    private TextField nummerField;

    @FXML
    void onDelete(ActionEvent event) {
        //contacts.removeLast();
        Contact removeContact = listView.getSelectionModel().getSelectedItem();

        boolean deleted = dao.delete(removeContact.getId());
        if (deleted) {
            listView.getItems().setAll(dao.findAll());
        }

    }

    @FXML
    void onSave(ActionEvent event) {
        if (nameField.getText().isEmpty() || nummerField.getText().isEmpty()) {
            System.out.println("Bitte Name und Nummer eingeben");
        } else {

            Contact newContact = new Contact(nameField.getText(), nummerField.getText());

            boolean saved = dao.save(newContact);
            if (saved) {
                // Refresh der Liste
                listView.getItems().setAll(dao.findAll());
            }
        }


    }

    @FXML
    public void initialize() {
        System.out.println("init");
        //dao = new ContactDummyDAO(); // switch to other Object
        dao = new ContactFileDAO();
        listView.getItems().setAll(dao.findAll()); // refresh



//            ObservableList<Contact> oList = FXCollections.observableArrayList();
//            listView.setItems(oList);
    }
    // TODO Text Change -> ohne Button-Event
    public void onSearch(ActionEvent actionEvent) {
        System.out.println(actionEvent);
        List<Contact> findList = dao.findByName(searchField.getText());
        listView.getItems().setAll(findList);
    }
}




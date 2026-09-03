package app;

import Controller.Controller;
import Storage.ListStorage;
import gui.Gui;
import javafx.application.Application;
import model.*;
import Controller.Storage;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        String fileName = "src/storage.ser";
        Storage storage = ListStorage.loadStorage(fileName);
        if (storage == null) {
            storage = new ListStorage();
            System.out.println("Empty ListStorage is created");
            Controller.setStorage(storage);
            initStorage();
            System.out.println("Storage is initialized");
        } else {
            Controller.setStorage(storage);
        }

        Application.launch(Gui.class);

        ListStorage.saveStorage(fileName, storage);
    }

    public static void initStorage(){

    }
}

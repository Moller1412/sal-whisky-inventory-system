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
        //test
        Oprindelse oprindelse = Controller.createOprindelse("markTest", "gaardTest");

        Råvare råvare = Controller.createRåvare("test","test",1000, LocalDate.of(2025, 1, 1),oprindelse);

        Medarbejder medarbejder = Controller.createMedarbejder(1,"test","123123");

        Destillering destillering = Controller.createDestillering(1,true,100,råvare,medarbejder);

        Leverandør leverandør = Controller.createLeverandør("John whisky", "test Adresse", "12345678");

        Destillat destillat = Controller.createDestillat(1,200,50,destillering);
        Fad fad = Controller.createFad(1,500,FadType.Sherry,leverandør);
        Controller.addDestillatTilFad(fad,destillat);
        fad.setStartLagring(LocalDate.of(2020,12,1));

//        FærdigVare færdigVare = Controller.createFærdigvare("test",200, fad);
//
//        færdigVare.setDatoForTabning(LocalDate.now());

        Lager lager = Controller.createLager("Lager", 200);

        Reol reol = Controller.createReol(1, lager);
        lager.addReolTilLager(reol);

        Række række = Controller.createRække(1, reol);
        reol.addRækkeTilReol(række);

        Hylde hylde = Controller.createHylde(1, række);
        række.addHyldeTilRække(hylde);

//        System.out.println(Controller.printHistorie(færdigVare));
    }
}

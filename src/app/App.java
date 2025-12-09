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


        ListStorage.saveStorage(fileName, storage);
        initStorage();
        Application.launch(Gui.class);


    }

    public static void initStorage(){
        //test
        Oprindelse oprindelse = new Oprindelse("markTest", "gaardTest");

        Råvare råvare = new Råvare("test","test",5, LocalDate.of(2025, 1, 1),oprindelse);

        Medarbejder medarbejder = new Medarbejder(1,"test","123123");

        Destillering destillering = new Destillering(1,true,100,råvare,medarbejder);


        Leverandør leverandør = new Leverandør("John whisky", "test Adresse", "123456789");


        Destillat destillat = new Destillat(1,200,50,destillering);
        Fad fad = new Fad(1,500,FadType.Sherry,leverandør);
        Controller.addDestillatTilFad(fad,destillat);
        fad.setStartLagring(LocalDate.of(2020,12,1));

        FærdigVare færdigVare = new FærdigVare("test",200, fad);

        færdigVare.setDatoForTabning(LocalDate.now());




        Lager lager = new Lager("Lager", 200);


        Reol reol = new Reol(1, lager);
        lager.addReolTilLager(reol);

        Række række = new Række(1, reol);
        reol.addRækkeTilReol(række);

        Hylde hylde = new Hylde(1, række);
        række.addHyldeTilRække(hylde);

        System.out.println(Controller.printHistorie(færdigVare));
    }
}

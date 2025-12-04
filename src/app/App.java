package app;

import Controller.Controller;
import Storage.Storage;
import gui.Gui;
import javafx.application.Application;
import model.*;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        Application.launch(Gui.class);

    }

    public static void initStorage(){
        //test
        Oprindelse oprindelse = new Oprindelse("markTest", "gaardTest");
        Råvare råvare = new Råvare("test","test",5, LocalDate.of(2025, 1, 1),oprindelse);
        Medarbejder medarbejder = new Medarbejder(1,"test",123123);
        Destillering destillering = new Destillering(1,true,100,råvare,medarbejder);
        Storage.storeDestillering(destillering);


        Leverandør leverandør = new Leverandør("John whisky", "test Adresse", "123456789");
        Storage.storeLeverandører(leverandør);

        Destillat destillat = new Destillat(1,200,50,destillering);
        Fad fad = new Fad(1,500,FadType.Sherry,leverandør);
        Controller.addDestillatTilFad(fad,destillat);
        fad.setStartLagring(LocalDate.of(2020,12,1));


        Storage.storeFad(fad);

    }
}

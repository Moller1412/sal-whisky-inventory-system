package app;

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
        Destillering destillering = new Destillering(true,1,råvare,medarbejder);
        Storage.storeDestillering(destillering);


        Leverandør leverandør = new Leverandør("John whisky", "test Adresse", "123456789");
        Storage.storeLeverandører(leverandør);
    }
}

package Controller;

import Storage.Storage;
import model.*;

import java.time.LocalDate;

public abstract class Controller {

    public static void createDestillat(int nr, double maengde, double vandPåfyldt, double alkoholProcent,
                                       LocalDate slutDato, Destillering destillering, FærdigVare færdigVare){
        Destillat destillat = new Destillat(nr, maengde, vandPåfyldt, alkoholProcent, slutDato, destillering, færdigVare);
        Storage.storeDestillat(destillat);
    }

    public static void createFad(int id, double størrelse, boolean erAktiv, FadType fadType){
        Fad fad = new Fad(id, størrelse, erAktiv, fadType);
        Storage.storeFad(fad);
    }
}

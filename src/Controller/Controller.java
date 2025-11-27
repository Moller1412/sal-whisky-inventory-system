package Controller;

import Storage.Storage;
import model.*;

import java.time.LocalDate;

public abstract class Controller {

    public static void createDestillat(int nr, double maengde, double alkoholProcent,
                                       LocalDate slutDato, Destillering destillering){
        Destillat destillat = new Destillat(nr, maengde, alkoholProcent, slutDato, destillering);
        Storage.storeDestillat(destillat);
    }

    public static void createFad(int id, double størrelse, boolean erAktiv, FadType fadType, Leverandør leverandør){
        Fad fad = new Fad(id, størrelse, erAktiv, fadType, leverandør);
        Storage.storeFad(fad);
    }

    public static void addDestillatTilFad(Fad fad, Destillat destillat){
        if(fad.isErAktiv()) throw new IllegalArgumentException("Fadet er fyldt");
        if(destillat.getMaengde() > fad.getStørrelse()){
            double rest = destillat.getMaengde() - fad.getStørrelse();
            fad.setDestillat(destillat);
            destillat.setMaengde(rest);
            destillat.addFad(fad);
            fad.setStartLagring(LocalDate.now());
            throw new RuntimeException("Der er " + rest + " liter destillat til overs");
        }
        else {
            fad.setDestillat(destillat);
            destillat.addFad(fad);
            fad.setStartLagring(LocalDate.now());
        }
    }

    public static void addFadTilHylde(Fad fad, Hylde hylde){
        if (hylde.isErOptaget()) throw new IllegalArgumentException("Hylde er optaget ");
        fad.setHylde(hylde);
        hylde.setErOptaget(true);
    }

    public static void addDestillatPåFadTilFærdigvare(Destillat destillat, FærdigVare færdigVare, Fad fad){
        if(fad.getDestillat() != destillat) throw new IllegalArgumentException("Fadets Destillat matcher ikke med det indtastede destillat");
       if(fad.erFadKlarTilTapning()){
           destillat.setFærdigVare(færdigVare);
           fad.setErAktiv(false);
           fad.setStartLagring(null);
       }
       else
           throw new IllegalArgumentException("Destillat på fad er ikke klar ");
    }


}

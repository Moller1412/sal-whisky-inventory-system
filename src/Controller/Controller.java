package Controller;

import Storage.Storage;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {

    public static void createDestillat(int nr, double maengde, double alkoholProcent,
                                        Destillering destillering){
        if (nr <= 0 || maengde <= 0) throw new IllegalArgumentException("Nummer skal være 0 eller over.");
        if (alkoholProcent < 0 || alkoholProcent > 100) throw new IllegalArgumentException("Alkohol procent skal være mellem 0 og 100.");
        if (destillering == null) throw new IllegalArgumentException("Destillering må ikke være null.");
        Destillat destillat = new Destillat(nr, maengde, alkoholProcent, destillering);
        Storage.storeDestillat(destillat);
    }

    public static void createFad(int id, double størrelse, FadType fadType, Leverandør leverandør){
        Fad fad = new Fad(id, størrelse, fadType, leverandør);
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
            fad.setLiterIFad(fad.getStørrelse());
            fad.setAlkoholProcent(destillat.getAlkoholProcent());
            throw new RuntimeException("Der er " + rest + " liter destillat til overs");
        }
        else {
            fad.setDestillat(destillat);
            destillat.addFad(fad);
            fad.setStartLagring(LocalDate.now());
            fad.setLiterIFad(destillat.getMaengde());
            fad.setAlkoholProcent(destillat.getAlkoholProcent());
        }
    }

    public static void addFadTilHylde(Fad fad, Hylde hylde){
        if (hylde.isErOptaget()) throw new IllegalArgumentException("Hylde er optaget ");
        fad.setHylde(hylde);
        hylde.setErOptaget(true);
    }

    public static void addDestillatFraFadTilFærdigvare(Destillat destillat, FærdigVare færdigVare, Fad fad){
        if(fad.getDestillat() != destillat) throw new IllegalArgumentException("Fadets Destillat matcher ikke med det indtastede destillat");
       if(fad.erFadKlarTilTapning()){
           destillat.setFærdigVare(færdigVare);
           fad.setErAktiv(false);
           fad.setStartLagring(null);
           fad.setLiterIFad(0);
       }
       else
           throw new IllegalArgumentException("Destillat på fad er ikke klar ");
    }


    public static ArrayList<Destillering> getDestillering() {
        return Storage.getDestillering();
    }

    public static ArrayList<Destillat> getDestillater() {
        return Storage.getDestillater();
    }

    public static ArrayList<Leverandør> getLeverandører() {
        return Storage.getLeverandører();
    }

    public static ArrayList<Fad> getFade(){
        return Storage.getFade();
    }


}

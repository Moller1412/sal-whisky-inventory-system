package Controller;

import Storage.Storage;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {

    public static void createDestillat(int nr, double maengde, double alkoholProcent,
                                        Destillering destillering){

        if (nr <= 0 || maengde <= 0) throw new IllegalArgumentException("Nummer og mængde skal være et positivt tal.");
        if (alkoholProcent < 0 || alkoholProcent > 100) throw new IllegalArgumentException("Alkohol procent skal være mellem 0 og 100.");
        if (destillering == null) throw new IllegalArgumentException("Destillering må ikke være null.");
        Destillat destillat = new Destillat(nr, maengde, alkoholProcent, destillering);
        Storage.storeDestillat(destillat);
    }

    public static void createFad(int id, double størrelse, FadType fadType, Leverandør leverandør){

        if (id <= 0 || størrelse <= 0) throw new IllegalArgumentException("Id og størrelse skal være et positivt tal.");
        if (fadType == null || leverandør == null) throw new IllegalArgumentException("fadType og leverandør må ikke være null.");
        Fad fad = new Fad(id, størrelse, fadType, leverandør);
        Storage.storeFad(fad);
    }

    public static void addDestillatTilFad(Fad fad, Destillat destillat){
        if (fad == null || destillat == null) throw new IllegalArgumentException("Fad og Destillat må ikke være null");
        if(fad.isErAktiv()) throw new IllegalArgumentException("Fadet er fyldt");
        if(destillat.getMaengde() > fad.getStørrelse()){
            double rest = destillat.getMaengde() - fad.getStørrelse();
            fad.setDestillat(destillat);
            fad.setErAktiv(true);
            destillat.setMaengde(rest);
            destillat.addFad(fad);
            fad.setStartLagring(LocalDate.now());
            fad.setLiterIFad(fad.getStørrelse());
            fad.setAlkoholProcent(destillat.getAlkoholProcent());
            System.out.println("Der er " + rest + " Til overs");
        }
        else {
            fad.setDestillat(destillat);
            destillat.addFad(fad);
            fad.setStartLagring(LocalDate.now());
            fad.setLiterIFad(destillat.getMaengde());
            fad.setAlkoholProcent(destillat.getAlkoholProcent());
            fad.setErAktiv(true);
        }
    }

    public static void addFadTilHylde(Fad fad, Hylde hylde){
        if (fad == null || hylde == null) throw new IllegalArgumentException("Fad og Hylde må ikke være null.");
        if (hylde.isErOptaget()) throw new IllegalArgumentException("Hylde er optaget ");
        fad.setHylde(hylde);
        hylde.setErOptaget(true);
    }

    public static void createFærdigvare(String navn, int pris, Fad fad){
        if (fad == null){
            throw new IllegalArgumentException("Fadet kan ikke være null");
        }
       if(fad.erFadKlarTilTapning()){
          FærdigVare færdigVare = new FærdigVare(navn, pris, fad);
           færdigVare.setDatoForTabning(LocalDate.now());
           Storage.storeFærdigvare(færdigVare);
           fad.setErAktiv(false);
           fad.setStartLagring(null);
           fad.setLiterIFad(0);
           fad.setHylde(null);

       }
       else
           throw new IllegalArgumentException("Der er ikke gået de minimum 3 år ");
    }

    public static ArrayList<Fad> fadeDerErKlarTilFærdigvare(){
        ArrayList<Fad> fadeKlarTilTap = new ArrayList<>();
        for (Fad f : Storage.getFade()) {
            if(f.erFadKlarTilTapning()){
                fadeKlarTilTap.add(f);
            }
        }
        return fadeKlarTilTap;
    }

    public static void createLeverandør(String navn, String adresse, String tlf){
        if (navn == null ){
            throw new IllegalArgumentException("leverandør mangler gyldigt navn");
        }
        if (adresse == null){
            throw new IllegalArgumentException("leverandør mangler gyldigt adresse");
        }
        if (tlf == null){
            throw new IllegalArgumentException("leverandør mangler gyldigt tlf");
        }
        Leverandør leverandør = new Leverandør(navn, adresse, tlf);
        Storage.storeLeverandører(leverandør);
    }

    public static void printHistorie(FærdigVare færdigVare){
        System.out.println(færdigVare.printInformationFraFærdigvare());
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

    public static ArrayList<FærdigVare> getFærdigvare(){
        return Storage.getFærdigvarer();}


}

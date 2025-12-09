package Controller;

import Storage.ListStorage;
import gui.Updatable;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public abstract class Controller {
    private static Storage storage;
    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }

    public static Destillat createDestillat(int nr, double maengde, double alkoholProcent,
                                        Destillering destillering){

        if (nr <= 0 || maengde <= 0) throw new IllegalArgumentException("Nummer og mængde skal være et positivt tal.");
        if (alkoholProcent < 0 || alkoholProcent > 100) throw new IllegalArgumentException("Alkohol procent skal være mellem 0 og 100.");
        if (destillering == null) throw new IllegalArgumentException("Destillering må ikke være null.");
        Destillat destillat = new Destillat(nr, maengde, alkoholProcent, destillering);
        storage.storeDestillat(destillat);
        notifyObservers();
        return destillat;
    }

    public static Fad createFad(int id, double størrelse, FadType fadType, Leverandør leverandør){

        if (id <= 0 || størrelse <= 0) throw new IllegalArgumentException("Id og størrelse skal være et positivt tal.");
        if (fadType == null || leverandør == null) throw new IllegalArgumentException("fadType og leverandør må ikke være null.");
        Fad fad = new Fad(id, størrelse, fadType, leverandør);
        storage.storeFad(fad);
        notifyObservers();
        return fad;
    }

    public static String addDestillatTilFad(Fad fad, Destillat destillat){
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
            return "Der er " + rest + " Til overs";
        }
        else {
            fad.setDestillat(destillat);
            destillat.addFad(fad);
            fad.setStartLagring(LocalDate.now());
            fad.setLiterIFad(destillat.getMaengde());
            fad.setAlkoholProcent(destillat.getAlkoholProcent());
            fad.setErAktiv(true);
            return "Fadet er fyldt med alt destillatet";
        }
    }

    public static void addFadTilHylde(Fad fad, Hylde hylde){
        if (fad == null || hylde == null) throw new IllegalArgumentException("Fad og Hylde må ikke være null.");
        if (hylde.isErOptaget()) throw new IllegalArgumentException("Hylde er optaget ");
        fad.setHylde(hylde);
        hylde.setErOptaget(true);
        hylde.setFad(fad);
    }

    public static FærdigVare createFærdigvare(String navn, int pris, Fad fad){
        if (fad == null){
            throw new IllegalArgumentException("Fadet kan ikke være null");
        }
       if(fad.erFadKlarTilTapning()){
          FærdigVare færdigVare = new FærdigVare(navn, pris, fad);
           færdigVare.setDatoForTabning(LocalDate.now());
           storage.storeFærdigvare(færdigVare);
           fad.setErAktiv(false);
           fad.setStartLagring(null);
           fad.setLiterIFad(0);
           fad.setHylde(null);
           notifyObservers();
           return færdigVare;
       }
       else
           throw new IllegalArgumentException("Der er ikke gået de minimum 3 år ");
    }

    public static ArrayList<Fad> fadeDerErKlarTilFærdigvare(){
        ArrayList<Fad> fadeKlarTilTap = new ArrayList<>();
        for (Fad f : storage.getFad()) {
            if(f.erFadKlarTilTapning()){
                fadeKlarTilTap.add(f);
            }
        }
        return fadeKlarTilTap;
    }

    public static Leverandør createLeverandør(String navn, String adresse, String tlf){
        if (navn == null || navn.isBlank()){
            throw new IllegalArgumentException("leverandør mangler gyldigt navn");
        }
        if (adresse == null || adresse.isBlank()){
            throw new IllegalArgumentException("leverandør mangler gyldigt adresse");
        }
        if (tlf == null || tlf.isBlank() || !tlf.matches("\\d{8}")){
            throw new IllegalArgumentException("leverandør mangler gyldigt tlf");
        }
        Leverandør leverandør = new Leverandør(navn, adresse, tlf);
        storage.storeLeverandør(leverandør);
        notifyObservers();
        return leverandør;
    }

    public static Destillering createDestillering(int nr, boolean erRøget, int antalRåvare, Råvare råvare, Medarbejder medarbejder){
        if (råvare == null)
            throw new IllegalArgumentException("Råvare kan ikke være null");

        if (medarbejder == null)
            throw new IllegalArgumentException("Medarbejder kan ikke være null");

        if (nr < 1)
            throw new IllegalArgumentException("nr må ikke være mindre end 0");

        if (antalRåvare < 1)
            throw new IllegalArgumentException("Antal råvare må ikke være mindre end 1");

        if (råvare.getMængde() - antalRåvare < 0)
            throw new IllegalArgumentException("Ikke nok råvare på lager til at oprette destillering.");

        Destillering destillering = new Destillering(nr, erRøget, antalRåvare, råvare, medarbejder);
        medarbejder.addDestillering(destillering);

        int mængde = råvare.getMængde() - antalRåvare;
        råvare.setMængde(mængde);
        storage.storeDestillering(destillering);
        notifyObservers();
        return destillering;
    }

    public static Medarbejder createMedarbejder(int medarbejderNr, String navn, String tlf){
        if (medarbejderNr <= 0)throw new IllegalArgumentException("medarbejderNr må ikke være 0 eller under.");
        if (navn == null || tlf == null)throw new IllegalArgumentException("Navn og tlf må ikke være null.");
        if (navn.isBlank() || tlf.isBlank())throw new IllegalArgumentException("Navn og tlf må ikke stå tomt");
        for (Medarbejder medarbejder : storage.getMedarbejder()) {
            if (medarbejderNr == medarbejder.getMedarbejderNr())throw new IllegalArgumentException("MedarbejderNr allerede i brug. Ugyldigt.");
        }

        Medarbejder medarbejder = new Medarbejder(medarbejderNr, navn, tlf);
        storage.storeMedarbejder(medarbejder);
        notifyObservers();
        return medarbejder;
    }

    public static Råvare createRåvare(String navn, String type, int mængde, LocalDate høstDato, Oprindelse oprindelse){
        if (navn == null || type == null)throw new IllegalArgumentException("navn og type må ikke være null.");
        if (navn.isBlank() || type.isBlank())throw new IllegalArgumentException("navn og type må ikke være tomt.");
        if (mængde <= 0)throw new IllegalArgumentException("mængde må ikke være 0 eller under.");
        if (!høstDato.isBefore(LocalDate.now().plusDays(1)))throw new IllegalArgumentException("høstDato skal være senest datoen i dag.");
        if (oprindelse == null)throw new IllegalArgumentException("oprindelse må ikke være null.");

        Råvare råvare = new Råvare(navn, type, mængde, høstDato, oprindelse);
        storage.storeRåvare(råvare);
        notifyObservers();
        return råvare;
    }

    public static Oprindelse createOprindelse(String mark, String gaard){
        if (mark == null || mark.isBlank())throw new IllegalArgumentException("Mark skal udfyldes.");
        if (gaard == null || gaard.isBlank())throw new IllegalArgumentException("Gaard skal udfyldes.");
        Oprindelse oprindelse = new Oprindelse(mark, gaard);
        storage.storeOprindelse(oprindelse);
        notifyObservers();
        return oprindelse;
    }

    public static Lager createLager(String navn, int antalKvadratMeter){
        if (navn == null || navn.isBlank())throw new IllegalArgumentException("navn skal være udfyldt.");
        if (antalKvadratMeter <= 0)throw new IllegalArgumentException("Antal kvadratmeter skal være over 0.");
        Lager lager = new Lager(navn, antalKvadratMeter);
        storage.storeLager(lager);
        notifyObservers();
        return lager;
    }

    public static Reol createReol(int nr, Lager lager){
        if (nr < 1)throw new IllegalArgumentException("nummer skal være et positivt tal.");
        if (lager == null)throw new IllegalArgumentException("Lager må ikke være null.");
        Reol reol = new Reol(nr, lager);
        lager.addReolTilLager(reol);
        storage.storeReol(reol);
        notifyObservers();
        return reol;
    }

    public static Række createRække(int nr, Reol reol){
        if (nr < 1)throw new IllegalArgumentException("nummer skal være et positivt tal.");
        if (reol == null)throw new IllegalArgumentException("Reol må ikke være null.");
        Række række = new Række(nr, reol);
        reol.addRækkeTilReol(række);
        storage.storeRække(række);
        notifyObservers();
        return række;
    }

    public static Hylde createHylde(int nr, Række række){
        if (nr < 1)throw new IllegalArgumentException("nummer skal være et positivt tal.");
        if(række == null) throw new IllegalArgumentException("Række må ikke være null");
        Hylde hylde = new Hylde(nr, række);
        række.addHyldeTilRække(hylde);
        storage.storeHylde(hylde);
        notifyObservers();
        return hylde;
    }

    public static String findFadPåLager(int fadID){
        if (fadID < 1)throw new IllegalArgumentException("fadID skal være 1 eller over.");
        boolean iBrug = false;
        for (Fad fad : storage.getFad()) {
            if (fadID == fad.getId()) iBrug = true;
        }

        StringBuilder sb = new StringBuilder();
        if (!iBrug){
            sb.append("FadID ikke i brug.");
        } else {
            for (Fad fad : storage.getFad()) {
                if (fad.getId() == fadID && fad.getHylde() != null){
                    sb.append("Fad ID: ").append(fadID);
                    sb.append("\nLager: ").append(fad.getHylde().getRække().getReol().getLager().getNavn());
                    sb.append("\nReol nummer: ").append(fad.getHylde().getRække().getReol().getNr());
                    sb.append("\nRække nummer: ").append(fad.getHylde().getRække().getNr());
                    sb.append("\nHylde nummer: ").append(fad.getHylde().getNr());
                }
            }
        }
        return sb.toString();
    }

    public static int antalLedigePladserPåLager(Lager lager){
        int count = 0;
        if(lager == null) throw new IllegalArgumentException("Du skal vælge et lager");
        for (Reol reol : lager.getReoler()) {
            for (Række række : reol.getRækker()) {
                for (Hylde hylde : række.getHylder()) {
                    if(!hylde.isErOptaget()){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static String printHistorie(FærdigVare færdigVare){
        return færdigVare.printInformationFraFærdigvare();
    }

    public static List<Destillering> getDestillering() {
        return storage.getDestillering();
    }

    public static List<Destillat> getDestillater() {
        return storage.getDestillat();
    }

    public static List<Leverandør> getLeverandører() {
        return storage.getLeverandør();
    }

    public static List<Fad> getFade(){
        return storage.getFad();
    }

    public static List<FærdigVare> getFærdigvare(){
        return storage.getFærdigvare();}

    public static List<Råvare> getRåvare(){
        return storage.getRåvare();
    }

    public static List<Medarbejder> getMedarbejder(){
        return storage.getMedarbejder();
    }

    public static List<Oprindelse> getOprindelser(){
        return storage.getOprindelse();
    }

    public static List<Lager> getLagere(){
        return storage.getLager();
    }

    public static List<Reol> getReoler(){
        return storage.getReol();
    }

    public static List<Række> getRækker(){
        return storage.getRækker();
    }

    public static List<Hylde> getHylder(){
        return storage.getHylde();
    }

    private static final List<Updatable> observers = new ArrayList<>();

    public static void addObserver(Updatable observer) {
        observers.add(observer);
    }

    private static void notifyObservers() {
        for (Updatable observer : observers) {
            observer.update();
        }
    }
}

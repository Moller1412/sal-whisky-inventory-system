package Storage;

import model.*;

import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Destillat> destillater = new ArrayList<>();
    private static final ArrayList<Fad> fade = new ArrayList<>();
    private static final ArrayList<Destillering> destilleringer = new ArrayList<>();
    private static final ArrayList<Leverandør> leverandører = new ArrayList<>();
    private static final ArrayList<FærdigVare> færdigvarer = new ArrayList<>();
    private static final ArrayList<Medarbejder> medarbejdere = new ArrayList<>();
    private static final ArrayList<Råvare> råvarer = new ArrayList<>();
    private static final ArrayList<Oprindelse> oprindelser = new ArrayList<>();

    public static void storeDestillat(Destillat destillat){
        destillater.add(destillat);
    }
    public static ArrayList<Destillat> getDestillater(){
        return destillater;
    }

    public static void storeFad(Fad fad){
        fade.add(fad);
    }
    public static ArrayList<Fad> getFade(){
        return fade;
    }

    public static void storeDestillering(Destillering destillering){
        destilleringer.add(destillering);
    }
    public static ArrayList<Destillering> getDestillering(){
        return destilleringer;
    }

    public static void storeLeverandører(Leverandør leverandør){
        leverandører.add(leverandør);
    }
    public static ArrayList<Leverandør> getLeverandører(){
        return leverandører;
    }

    public static void storeFærdigvare(FærdigVare færdigVare) {færdigvarer.add(færdigVare);}
    public static ArrayList<FærdigVare> getFærdigvarer(){return færdigvarer;}

    public static void storeRåvarer(Råvare råvare) {råvarer.add(råvare);}
    public static ArrayList<Råvare> getRåvarer(){return råvarer;}

    public static void storeMedarbejder(Medarbejder medarbejder) {medarbejdere.add(medarbejder);}
    public static ArrayList<Medarbejder> getMedarbejdere(){return medarbejdere;}

    public static void storeOprindelse(Oprindelse oprindelse) {oprindelser.add(oprindelse);}
    public static ArrayList<Oprindelse> getOprindelser(){return oprindelser;}
}

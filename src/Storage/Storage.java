package Storage;

import model.Destillat;
import model.Destillering;
import model.Fad;
import model.Leverandør;

import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Destillat> destillater = new ArrayList<>();
    private static final ArrayList<Fad> fade = new ArrayList<>();
    private static final ArrayList<Destillering> destilleringer = new ArrayList<>();
    private static final ArrayList<Leverandør> leverandører = new ArrayList<>();

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


}

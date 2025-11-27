package Storage;

import model.Destillat;
import model.Fad;

import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Destillat> destillater = new ArrayList<>();
    private static final ArrayList<Fad> fade = new ArrayList<>();

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
}

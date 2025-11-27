package model;

import java.util.ArrayList;

public class Lager {
    private String navn;
    private int antalKvadratMeter;
    private ArrayList<Reol> reoler = new ArrayList<>();

    public Lager(String navn, int antalKvadratMeter) {
        this.navn = navn;
        this.antalKvadratMeter = antalKvadratMeter;
    }
    public void addReolTilLager(Reol reol){
        reoler.add(reol);
    }
    public void removeReolFraLager(Reol reol){
        reoler.remove(reol);
    }

    public ArrayList<Reol> getReoler() {
        return reoler;
    }
}

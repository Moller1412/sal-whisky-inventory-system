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

    public String getNavn() {
        return navn;
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

    @Override
    public String toString() {
        return "Navn: " + navn + ", KVM: " + antalKvadratMeter;
    }
}

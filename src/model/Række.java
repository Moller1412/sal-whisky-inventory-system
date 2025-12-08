package model;

import java.util.ArrayList;

public class Række {
    private int nr;
    private Reol reol;
    private ArrayList<Hylde> hylder = new ArrayList<>();

    public Række(int nr, Reol reol) {
        this.nr = nr;
        this.reol = reol;
    }

    public int getNr() {
        return nr;
    }

    public Reol getReol() {
        return reol;
    }

    public void addHyldeTilRække(Hylde hylde){
        hylder.add(hylde);
    }

    public void removeHyldeFraRække(Hylde hylde){
        hylder.remove(hylde);
    }

    public ArrayList<Hylde> getHylder() {
        return hylder;
    }
}

package model;

import java.util.ArrayList;

public class Række {
    private int nr;
    private ArrayList<Hylde> hylder = new ArrayList<>();

    public Række(int nr) {
        this.nr = nr;
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

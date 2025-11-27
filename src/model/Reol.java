package model;

import java.util.ArrayList;

public class Reol {
    private int nr;
    private ArrayList<Række> rækker = new ArrayList<>();

    public Reol(int nr) {
        this.nr = nr;
    }

    public void addRækkeTilReol(Række række){
        rækker.add(række);
    }

    public void removeRækkeFraReol(Række række){
        rækker.remove(række);
    }

    public ArrayList<Række> getRækker() {
        return rækker;
    }
}

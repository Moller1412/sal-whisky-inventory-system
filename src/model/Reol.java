package model;

import java.util.ArrayList;

public class Reol {
    private int nr;
    private Lager lager;
    private ArrayList<Række> rækker = new ArrayList<>();

    public Reol(int nr, Lager lager) {
        this.nr = nr;
        this.lager = lager;
    }

    public int getNr() {
        return nr;
    }

    public Lager getLager() {
        return lager;
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

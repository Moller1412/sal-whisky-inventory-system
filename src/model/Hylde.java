package model;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Hylde {
    private int nr;
    private Række række;
    private boolean erOptaget;
    private Fad fad;


    public Hylde(int nr, Række række) {
        this.nr = nr;
        this.række = række;
        this.erOptaget = false;
    }

    public Fad getFad() {
        return fad;
    }

    public int getNr() {
        return nr;
    }

    public Række getRække() {
        return række;
    }

    public boolean isErOptaget() {
        return erOptaget;
    }

    public void setErOptaget(boolean erOptaget) {
        this.erOptaget = erOptaget;
    }

    @Override
    public String toString() {
        return "NR: " + nr + ", række nr: " + række.getNr();
    }
}

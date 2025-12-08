package model;

import java.util.ArrayList;

public class Hylde {
    private int nr;
    private Række række;
    private boolean erOptaget;


    public Hylde(int nr, Række række) {
        this.nr = nr;
        this.række = række;
        this.erOptaget = false;
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
}

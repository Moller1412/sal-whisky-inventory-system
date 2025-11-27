package model;

import java.util.ArrayList;

public class Hylde {
    private int nr;
    private boolean erOptaget;


    public Hylde(int nr) {
        this.nr = nr;
        this.erOptaget = false;
    }

    public boolean isErOptaget() {
        return erOptaget;
    }

    public void setErOptaget(boolean erOptaget) {
        this.erOptaget = erOptaget;
    }
}

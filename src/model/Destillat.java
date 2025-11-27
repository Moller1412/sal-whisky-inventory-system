package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillat {
    private int nr;
    private double maengde;
    private double vandPåfyldt;
    private double alkoholProcent;


    //Links
    private ArrayList<Fad> fade = new ArrayList<>();
    private Destillering destillering;
    private FærdigVare færdigVare;


    public Destillat(int nr, double maengde, double alkoholProcent,
                      Destillering destillering) {
        this.nr = nr;
        this.maengde = maengde;
        this.alkoholProcent = alkoholProcent;
        this.destillering = destillering;

    }

    public void addFad(Fad fad){
        fade.add(fad);
    }

    public void removeFad(Fad fad){
        fade.remove(fad);
    }

    public ArrayList<Fad> getFade() {
        return fade;
    }

    public void setFærdigVare(FærdigVare færdigVare) {
        this.færdigVare = færdigVare;
    }

    public double getMaengde() {
        return maengde;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }
}

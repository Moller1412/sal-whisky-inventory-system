package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillat {
    private int nr;
    private double maengde;
    private double vandPåfyldt;
    private double alkoholProcent;
    private LocalDate slutDato;

    //Links
    private ArrayList<Fad> fade = new ArrayList<>();
    private Destillering destillering;
    private FærdigVare færdigVare;


    public Destillat(int nr, double maengde, double vandPåfyldt, double alkoholProcent,
                     LocalDate slutDato, Destillering destillering, FærdigVare færdigVare) {
        this.nr = nr;
        this.maengde = maengde;
        this.vandPåfyldt = vandPåfyldt;
        this.alkoholProcent = alkoholProcent;
        this.slutDato = slutDato;
        this.destillering = destillering;
        this.færdigVare = færdigVare;
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
}

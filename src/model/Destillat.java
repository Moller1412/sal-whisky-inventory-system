package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillat {
    private int nr;
    private double maengde;
    private double vandPåfyldt;
    private double alkoholProcent;
    private LocalDate slutDato;

    //Link til Fad, ArrayList 0..*
    //Link til Destillering, 1 (Skal med i constructor)
    //Link til Færdigvare, 1 (Skal med i constructor)


    public Destillat(int nr, double maengde, double vandPåfyldt, double alkoholProcent, LocalDate slutDato) {
        this.nr = nr;
        this.maengde = maengde;
        this.vandPåfyldt = vandPåfyldt;
        this.alkoholProcent = alkoholProcent;
        this.slutDato = slutDato;
    }

}

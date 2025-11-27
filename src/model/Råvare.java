package model;

import java.time.LocalDate;

public class Råvare {

    private String navn;
    private  String type;
    private int mængde;
    private LocalDate høstDato;

    public Råvare(String navn, String type, int mængde, LocalDate høstDato) {
        this.navn = navn;
        this.type = type;
        this.mængde = mængde;
        this.høstDato = høstDato;
    }
}

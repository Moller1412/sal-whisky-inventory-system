package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Råvare {

    private String navn;
    private  String type;
    private int mængde;
    private LocalDate høstDato;
    private Oprindelse oprindelse;

    public Råvare(String navn, String type, int mængde, LocalDate høstDato, Oprindelse oprindelse) {
        this.navn = navn;
        this.type = type;
        this.mængde = mængde;
        this.høstDato = høstDato;
        this.oprindelse = oprindelse;
    }

}

package model;

public class FærdigVare {
    private String navn;
    private int pris;
    private int mængde;

    //Link til destillat
    private Fad fad;


    public FærdigVare(String navn, int pris, int mængde, Fad fad) {
        this.navn = navn;
        this.pris = pris;
        this.mængde = mængde;
        this.fad = fad;
    }

}

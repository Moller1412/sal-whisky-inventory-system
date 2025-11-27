package model;

public class FærdigVare {
    private String navn;
    private int pris;
    private int mængde;

    //Link til destillat
    private Destillat destillat;


    public FærdigVare(String navn, int pris, int mængde, Destillat destillat) {
        this.navn = navn;
        this.pris = pris;
        this.mængde = mængde;
        this.destillat = destillat;
    }

}

package model;

public class FærdigVare {
    private String navn;
    private int pris;
    private int mængde;

    //Link til destillat, 1 (Skal med i constructor)

    public FærdigVare(String navn, int pris, int mængde) {
        this.navn = navn;
        this.pris = pris;
        this.mængde = mængde;
    }
}

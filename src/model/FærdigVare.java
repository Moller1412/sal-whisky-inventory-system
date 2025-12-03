package model;

public class FærdigVare {
    private String navn;
    private int pris;
    private int mængde;
    private Fad fad;


    public FærdigVare(String navn, int pris, int mængde, Fad fad) {
        this.navn = navn;
        this.pris = pris;
        this.mængde = mængde;
        this.fad = fad;
    }

    public void udregnAntalFlaskerFraFærdigvare(double flaskeStørrelseLiter){
        double antalFlaskerTilgængelig = fad.getLiterIFad() / flaskeStørrelseLiter;
        double rest = fad.getLiterIFad() -antalFlaskerTilgængelig * flaskeStørrelseLiter;

        System.out.println("Der er " + antalFlaskerTilgængelig + " antal flasker tilgængelig fra denne færdigvare");
        System.out.println("der er " + rest + " til overs");
    }

}

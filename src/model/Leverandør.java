package model;

public class Leverandør {
    private String navn;
    private String adresse;
    private int mobilnummer;
    private Fad fad;

    public Leverandør(String navn, String adresse, int mobilnummer, Fad fad) {
        this.navn = navn;
        this.adresse = adresse;
        this.mobilnummer = mobilnummer;
        this.fad = fad;
    }

    public Fad getFad() {
        return fad;
    }

    public void setFad(Fad fad) {
        this.fad = fad;
    }
}

package model;

public class Leverandør {
    private String navn;
    private String adresse;
    private int mobilnummer;
    private Fad fad;

    public Leverandør(String navn, String adresse, int mobilnummer ) {
        this.navn = navn;
        this.adresse = adresse;
        this.mobilnummer = mobilnummer;

    }

    public Fad getFad() {
        return fad;
    }

    public void setFad(Fad fad) {
        this.fad = fad;
    }

    @Override
    public String toString() {
        return  "NAVN: " + navn  +
                ", TLF: " + mobilnummer +
                ", ADRESSE: '" + adresse +
                '}';
    }
}

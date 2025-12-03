package model;

public class Leverandør {
    private String navn;
    private String adresse;
    private String mobilnummer;

    public Leverandør(String navn, String adresse, String mobilnummer ) {
        this.navn = navn;
        this.adresse = adresse;
        this.mobilnummer = mobilnummer;
    }

    @Override
    public String toString() {
        return  "NAVN: " + navn  +
                ", TLF: " + mobilnummer +
                ", ADRESSE: '" + adresse +
                '}';
    }
}

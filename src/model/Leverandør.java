package model;

import java.io.Serializable;

public class Leverandør implements Serializable {
    private String navn;
    private String adresse;
    private String mobilnummer;

    public Leverandør(String navn, String adresse, String mobilnummer ) {
        this.navn = navn;
        this.adresse = adresse;
        this.mobilnummer = mobilnummer;
    }

    public String getNavn() {
        return navn;
    }

    public String printInformationOmLeverandør(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nInformation om Leverandør:");
        sb.append("\nNavn: "); sb.append(navn);
        sb.append("\nAdresse: "); sb.append(adresse);
        return sb.toString();
    }

    @Override
    public String toString() {
        return  "Navn: " + navn + " " + "Adresse: " + adresse +
                " " + "MobilNr: " + mobilnummer;
    }
}

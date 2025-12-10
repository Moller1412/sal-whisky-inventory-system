package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Medarbejder implements Serializable {

    private int medarbejderNr;
    private String navn;
    private String tlf;
    private List<Destillering> destilleringer = new ArrayList<>();

    public Medarbejder(int medarbejderNr, String navn, String tlf) {
        this.medarbejderNr = medarbejderNr;
        this.navn = navn;
        this.tlf = tlf;
    }

    public void addDestillering(Destillering destillering){
        destilleringer.add(destillering);
    }

    public void removeDestillering(Destillering destillering){
        destilleringer.remove(destillering);
    }

    public List<Destillering> getDestilleringer() {
        return destilleringer;
    }

    public int getMedarbejderNr() {
        return medarbejderNr;
    }

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return "Navn: " + navn + " " + "MedarbejderNr: " + medarbejderNr +
                " " + "MobilNr: " + tlf;
    }
}

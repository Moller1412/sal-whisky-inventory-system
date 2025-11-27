package model;

import java.time.LocalDate;
import java.time.Period;

public class Fad {
    private int id;
    private double størrelse;
    private boolean erAktiv = false;
    private double literIFad;
    private Destillat destillat;
    private FadType fadType;
    private Hylde hylde;
    private Leverandør leverandør;
    private LocalDate startLagring;

    public Fad(int id, double størrelse, FadType fadType, Leverandør leverandør) {
        this.id = id;
        this.størrelse = størrelse;
        this.fadType = fadType;
        this.leverandør = leverandør;
        this.literIFad = 0;

    }

    public Destillat getDestillat() {
        return destillat;
    }

    public void setDestillat(Destillat destillat) {
        this.destillat = destillat;
    }

    public Hylde getHylde() {
        return hylde;
    }

    public void setHylde(Hylde hylde) {
        this.hylde = hylde;
    }

    public LocalDate getStartLagring() {
        return startLagring;
    }

    public void setStartLagring(LocalDate startLagring) {
        this.startLagring = startLagring;
    }

    public double getLiterIFad() {
        return literIFad;
    }

    public void setLiterIFad(double literIFad) {
        this.literIFad = literIFad;
    }

    public boolean isErAktiv() {
        return erAktiv;
    }

    public void setErAktiv(boolean erAktiv) {
        this.erAktiv = erAktiv;
    }

    public double getStørrelse() {
        return størrelse;
    }

    public boolean erFadKlarTilTapning() {
        if (erAktiv && Period.between(startLagring, LocalDate.now()).getYears() >= 3) {
            return true;
        }
        return false;
    }

    private double getAngelShare(double nuVærendeMængde){
        double angelShare = literIFad - nuVærendeMængde;
        literIFad = nuVærendeMængde;
        destillat.setMaengde(destillat.getMaengde()-angelShare);
        return angelShare;
    }

    private double addVandTilFad(double literVand){
        double nyMængde = literVand + literIFad;
        if(nyMængde > størrelse) throw new IllegalArgumentException("Denne mængde kan ikke være i fadet ");
        else {
            literIFad = nyMængde;
            destillat.setMaengde(destillat.getMaengde()+literVand);
        }
        return nyMængde;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", STR: " + størrelse +
                ", AKTIV: " + erAktiv +
                ", TYPE: " + fadType +
                '}';
    }
}

package model;

import java.time.LocalDate;
import java.time.Period;

public class Fad {
    private int id;
    private double størrelse;
    private boolean erAktiv;
    private Destillat destillat;
    private FadType fadType;
    private Hylde hylde;
    private Leverandør leverandør;
    private LocalDate startLagring;

    public Fad(int id, double størrelse, boolean erAktiv, FadType fadType, Leverandør leverandør) {
        this.id = id;
        this.størrelse = størrelse;
        this.erAktiv = erAktiv;
        this.fadType = fadType;
        this.leverandør = leverandør;

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
}

package model;

import java.time.LocalDate;
import java.time.Period;

public class FærdigVare {
    private String navn;
    private int pris;
    private double mængde;
    private Fad fad;
    private double restVærdi;
    private LocalDate datoForTabning;

    public FærdigVare(String navn, int pris, Fad fad) {
        this.navn = navn;
        this.pris = pris;
        this.mængde = 0.7;
        this.fad = fad;
    }

    public double udregnAntalFlaskerFraFærdigvare(){
        double antalFlaskerTilgængelig = fad.getLiterIFad() /  mængde;
        double rest = antalFlaskerTilgængelig * mængde - fad.getLiterIFad();
        restVærdi = rest;
        return antalFlaskerTilgængelig;
    }

    public void printInformationFraFærdigvare(){
        StringBuilder sb = new StringBuilder();
        sb.append("Information om færdigvaren");
        sb.append("Navn ");  sb.append(navn);
        sb.append("Pris pr flaske ");  sb.append(pris);
        sb.append("Størrelse på flaske "); sb.append(mængde);
        sb.append("antal flasker "); sb.append(udregnAntalFlaskerFraFærdigvare());
        sb.append("Resterende mængde "); sb.append(restVærdi);
        sb.append("Alkohol procent "); sb.append(fad.getAlkoholProcent());
        sb.append("Antal år på fad "); sb.append(Period.between(fad.getStartLagring(), LocalDate.ofEpochDay(datoForTabning.getYear())));
    }

    public String getNavn() {
        return navn;
    }

    public int getPris() {
        return pris;
    }

    public void setMængde(double mængde) {
        this.mængde = mængde;
    }

    public double getRestVærdi() {
        return restVærdi;
    }

    public Fad getFad() {
        return fad;
    }

    public double getMængde() {
        return mængde;
    }

    public LocalDate getDatoForTabning() {
        return datoForTabning;
    }

    public void setDatoForTabning(LocalDate datoForTabning) {
        this.datoForTabning = datoForTabning;
    }
}

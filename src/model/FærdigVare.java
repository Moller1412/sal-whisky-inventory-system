package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class FærdigVare implements Serializable {
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
        if (fad.getLiterIFad() <= 0)throw new IllegalArgumentException("Fadet er tom");
        if (mængde <= 0)throw new IllegalArgumentException("Mængden på flasker er 0. Ikke muligt at fylde op.");
        if (fad.getLiterIFad() < mængde){
            System.out.println("Ikke nok whisky i fadet til at fylde flasker op.");
        }
        double antalFlaskerTilgængelig = Math.floor(fad.getLiterIFad()/mængde);
        double rest = fad.getLiterIFad() - (antalFlaskerTilgængelig * mængde);
        restVærdi = rest;
        return antalFlaskerTilgængelig;
    }

    public String printInformationFraFærdigvare(){
        StringBuilder sb = new StringBuilder();
        sb.append("Information om færdigvaren:");
        sb.append("\nNavn: ");  sb.append(navn);
        sb.append("\nPris pr flaske: ");  sb.append(pris);
        sb.append("\nStørrelse på flaske: "); sb.append(mængde);
        sb.append("\nAntal flasker: "); sb.append(udregnAntalFlaskerFraFærdigvare());
        sb.append("\nResterende mængde: "); sb.append(restVærdi);
        sb.append("\nAlkohol procent: "); sb.append(fad.getAlkoholProcent());
        Period p = Period.between(LocalDate.ofEpochDay(datoForTabning.getYear()), fad.getStartLagring());
        String antalÅr = String.format("%d år, %d måneder og %d dage.", p.getYears(), p.getMonths(), p.getDays());
        sb.append("\nAntal år på fad: "); sb.append(antalÅr);
        sb.append("\n").append(fad.printInformationOmFad());
        return sb.toString();
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

package model;

import java.time.LocalDate;
import java.time.Period;

public class Fad {
    private int id;
    private double størrelse;
    private boolean erAktiv;
    private double literIFad;
    private Destillat destillat;
    private FadType fadType;
    private Hylde hylde;
    private Leverandør leverandør;
    private LocalDate startLagring;
    private double alkoholProcent;

    public Fad(int id, double størrelse, FadType fadType, Leverandør leverandør) {
        this.id = id;
        this.størrelse = størrelse;
        this.fadType = fadType;
        this.leverandør = leverandør;
        this.literIFad = 0;
        this.erAktiv = false;
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

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setAlkoholProcent(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    public boolean erFadKlarTilTapning() {
        if (erAktiv && Period.between(startLagring, LocalDate.now()).getYears() >= 3) {
            return true;
        }
        return false;
    }

    public double getAngelShare(double nuVærendeMængde, double nyAlkoholProcent){
        if(nuVærendeMængde > literIFad || nuVærendeMængde < 0 ) throw new IllegalArgumentException("ugyldig mængde ændret");
        if(nyAlkoholProcent > alkoholProcent || nyAlkoholProcent < 40) throw new IllegalArgumentException("ugyldig alkoholprocent ændret");
        double angelShare = literIFad - nuVærendeMængde;
        alkoholProcent = nyAlkoholProcent;
        literIFad = nuVærendeMængde;
        destillat.setMaengde(destillat.getMaengde()-angelShare);
        return angelShare;
    }

    public double addVandTilFad(double literVand){
        double nyMængde = literVand + literIFad;
        if (literVand <= 0) throw new IllegalArgumentException("Denne mængde er ugyldig, " +
                "indtast venligst et tal større end 0.");
        if(nyMængde > størrelse) throw new IllegalArgumentException("Denne mængde kan ikke være i fadet ");
        else {
            destillat.setMaengde(destillat.getMaengde()+literVand);

            double renAlkohol = literIFad * (alkoholProcent / 100);
            double samletVolume = literIFad + literVand;
            alkoholProcent = renAlkohol / samletVolume * 100;
            literIFad = nyMængde;
        }
        return nyMængde;
    }

    public String printInformationOmFad(){
        StringBuilder sb = new StringBuilder();
        sb.append("Information om fad ");
        sb.append("\nId "); sb.append(id);
        sb.append("\nFad type "); sb.append(fadType);
        return sb.toString();
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

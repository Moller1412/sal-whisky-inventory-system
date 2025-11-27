package model;

public class Fad {
    private int id;
    private double størrelse;
    private boolean erAktiv;
    private Destillat destillat;
    private FadType fadType;
    private Hylde hylde;
    private Leverandør leverandør;

    public Fad(int id, double størrelse, boolean erAktiv, FadType fadType, Leverandør leverandør) {
        this.id = id;
        this.størrelse = størrelse;
        this.erAktiv = erAktiv;
        this.fadType = fadType;
        this.leverandør = leverandør;

    }

    public Destillat getDestillat(){
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

    public boolean isErAktiv() {
        return erAktiv;
    }

    public double getStørrelse() {
        return størrelse;
    }
}

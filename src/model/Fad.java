package model;

public class Fad {
    private int id;
    private double størrelse;
    private boolean erAktiv;
    private Destillat destillat;
    private FadType fadType;
    private Leverandør leverandør;
    private Hylde hylde;

    public Fad(int id, double størrelse, boolean erAktiv, FadType fadType,
               Leverandør leverandør, Hylde hylde) {
        this.id = id;
        this.størrelse = størrelse;
        this.erAktiv = erAktiv;
        this.fadType = fadType;
        this.leverandør = leverandør;
        this.hylde = hylde;
    }
}

package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillat {
    private int nr;
    private double maengde;
    private double alkoholProcent;


    //Links
    private ArrayList<Fad> fade = new ArrayList<>();
    private Destillering destillering;



    public Destillat(int nr, double maengde, double alkoholProcent,
                      Destillering destillering) {
        this.nr = nr;
        this.maengde = maengde;
        this.alkoholProcent = alkoholProcent;
        this.destillering = destillering;

    }

    public void addFad(Fad fad){
        fade.add(fad);
    }

    public void removeFad(Fad fad){
        fade.remove(fad);
    }

    public ArrayList<Fad> getFade() {
        return fade;
    }


    public double getMaengde() {
        return maengde;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public int getNr() {
        return nr;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    public String printInformationFraDestillat(){
        StringBuilder sb = new StringBuilder();
        sb.append("Sall Whisky Destillat Nummer: "); sb.append(nr);
        sb.append("\n");
        sb.append(destillering.printInformationFraDestillering());

        return sb.toString();
    }

    @Override
    public String toString() {
        return
                "NR: " + nr +
                " | MÆNGDE: " + maengde +
                " | ALC: " + alkoholProcent;
    }
}

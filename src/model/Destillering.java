package model;

import java.io.Serializable;

public class Destillering implements Serializable {

    private int nr;
    private boolean erRøget;
    private int antalRåvare;
    private Råvare råvare;
    private Medarbejder medarbejder;

    public Destillering(int nr, boolean erRøget, int antalRåvare, Råvare råvare, Medarbejder medarbejder) {
        this.nr = nr;
        this.erRøget = erRøget;
        this.antalRåvare = antalRåvare;
        this.råvare = råvare;
        this.medarbejder = medarbejder;
    }

    public String printInformationFraDestillering(){
        StringBuilder sb = new StringBuilder();
        sb.append("Destillering nummmer:" ).append(nr);
        if (erRøget)sb.append("\nDenne whisky er røget");
        else sb.append("\nDenne whisky er ikke røget.");
        sb.append("\n");
        sb.append(råvare.printInformationFraRåvare());

        return sb.toString();
    }

    public Råvare getRåvare() {
        return råvare;
    }

    @Override
    public String toString() {
        return "Nr: " + nr + " " + "Røget: " + erRøget + " " +
                "Antal råvarer: " + antalRåvare + " " +
                "Råvarer: " + råvare.getNavn() + " " + "Medarbejder: " + medarbejder.getNavn();
    }
}

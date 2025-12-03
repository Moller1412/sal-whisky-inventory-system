package model;

public class Destillering {

    private boolean erRøget;
    private int antalRåvare;
    private Råvare råvare;
    private Medarbejder medarbejder;

    public Destillering( boolean erRøget, int antalRåvare, Råvare råvare, Medarbejder medarbejder) {
        this.erRøget = erRøget;
        this.antalRåvare = antalRåvare;
        this.råvare = råvare;
        this.medarbejder = medarbejder;
    }

    public String printInformationFraDestillering(){
        StringBuilder sb = new StringBuilder();
        if (erRøget)sb.append("Denne whisky er røget");
        else sb.append("Denne whisky er ikke røget.");
        sb.append("\n");
        sb.append(printInformationFraDestillering());

        return sb.toString();
    }

    public Råvare getRåvare() {
        return råvare;
    }

    @Override
    public String toString() {
        return
                "RÅVARE: " + råvare + ", RØGET: " + erRøget +
                ", ANTAL RÅVARE: " + antalRåvare +
                ", MEDARBEJDER: " + medarbejder;
    }
}

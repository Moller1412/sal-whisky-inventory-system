package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString() {
        return
                "RÅVARE: " + råvare + ", RØGET: " + erRøget +
                ", ANTAL RÅVARE: " + antalRåvare +
                ", MEDARBEJDER: " + medarbejder;
    }
}

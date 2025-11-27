package model;

import java.time.LocalDate;

public class Destillering {

    private LocalDate startDato;
    private boolean erRøget;
    private int antalRåvare;

    public Destillering(LocalDate startDato, boolean erRøget, int antalRåvare) {
        this.startDato = startDato;
        this.erRøget = erRøget;
        this.antalRåvare = antalRåvare;
    }
}

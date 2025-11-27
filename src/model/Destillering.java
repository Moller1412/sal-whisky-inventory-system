package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillering {

    private LocalDate startDato;
    private boolean erRøget;
    private int antalRåvare;
    private Råvare råvare;
    private Medarbejder medarbejder;

    public Destillering(LocalDate startDato, boolean erRøget, int antalRåvare, Råvare råvare, Medarbejder medarbejder) {
        this.startDato = startDato;
        this.erRøget = erRøget;
        this.antalRåvare = antalRåvare;
        this.råvare = råvare;
        this.medarbejder = medarbejder;
    }




}

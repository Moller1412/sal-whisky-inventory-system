package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Råvare {

    private String navn;
    private  String type;
    private int mængde;
    private LocalDate høstDato;
    private Oprindelse oprindelse;

    public Råvare(String navn, String type, int mængde, LocalDate høstDato, Oprindelse oprindelse) {
        this.navn = navn;
        this.type = type;
        this.mængde = mængde;
        this.høstDato = høstDato;
        this.oprindelse = oprindelse;
    }

    public String printInformationFraRåvare(){
        StringBuilder sb = new StringBuilder();
        sb.append("Råvare navn: ").append(navn);
        sb.append("\nType: ").append(type);
        sb.append("\n");
        sb.append(oprindelse.printInformationFraOprindelse());

        return sb.toString();
    }

    public Oprindelse getOprindelse() {
        return oprindelse;
    }

    @Override
    public String toString() {
        return navn;}
}

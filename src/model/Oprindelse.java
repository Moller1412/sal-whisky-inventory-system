package model;

import java.util.ArrayList;
import java.util.List;

public class Oprindelse {
    private String mark;
    private String gaard;

    public Oprindelse(String mark, String gaard) {
        this.mark = mark;
        this.gaard = gaard;
    }

    public String printInformationFraOprindelse(){
        StringBuilder sb = new StringBuilder();
        sb.append("Oprindelse på råvarer.");
        sb.append("\nMark: ").append(mark);
        sb.append("\nGaard: ").append(gaard);
        return sb.toString();
    }
}

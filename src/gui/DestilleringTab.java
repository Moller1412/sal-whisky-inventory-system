package gui;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DestilleringTab {

    private TextField nrTxtF = new TextField();
    private TextField mængdeTxtF = new TextField();


    public GridPane getContent() {
        GridPane pane = new GridPane();

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        pane.add(new Label("Nr."), 0, 0);
        pane.add(nrTxtF,1,0);

        pane.add(new Label("Nr."), 0, 0);
        pane.add(nrTxtF,1,0);

        return pane;
    }
}
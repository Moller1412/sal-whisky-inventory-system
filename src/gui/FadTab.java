package gui;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FadTab {

    private TextField IDTxtF = new TextField();
    private TextField strTxtF = new TextField();
    private CheckBox erAktivCheckBox = new CheckBox();

    public GridPane getContent() {
        GridPane pane = new GridPane();

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);


        pane.add(new Label("ID"), 0, 0);
        pane.add(IDTxtF,1,0);

        pane.add(new Label("str."), 0, 1);
        pane.add(strTxtF,1,1);

        pane.add(new Label("er aktiv."), 0, 2);
        pane.add(erAktivCheckBox,1,2);

        return pane;
    }
}
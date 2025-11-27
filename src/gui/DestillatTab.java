package gui;

import Controller.Controller;
import Storage.Storage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Destillat;
import model.Destillering;
import model.Fad;

public class DestillatTab {

    private TextField nrTxtF = new TextField();
    private TextField mængdeTxtF = new TextField();
    private TextField alkoholPrcentTxtf = new TextField();
    private ListView<Destillat> eksisterendeDestillater = new ListView<>();
    private ListView<Destillering> destilleringListView = new ListView<>();
    private Button opret = new Button("Opret destillat");


    public GridPane getContent() {
        GridPane pane = new GridPane();

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        pane.add(new Label("Nr:"), 0, 0);
        pane.add(nrTxtF,1,0);

        pane.add(new Label("Mængde:"), 0, 1);
        pane.add(mængdeTxtF,1,1);
        pane.add(new Label("L"), 2, 1);

        pane.add(new Label("Alkohol procent:"), 0, 2);
        pane.add(alkoholPrcentTxtf,1,2);
        pane.add(new Label("%"), 2, 2);

        pane.add(new Label("Vælg destillering:"), 0, 3);
        destilleringListView.getItems().setAll(Controller.getDestillering()); // ændre til at hente gennem controller
        pane.add(destilleringListView,0,4,2,1);

        pane.add(new Label("Destillater:"), 2, 3);
        pane.add(eksisterendeDestillater,2,4,2,1);

        pane.add(opret,0,5);
        opret.setOnAction(event -> opretDestillat());


        return pane;
    }

    private void opretDestillat(){
        destilleringListView.getSelectionModel().getSelectedItem();
        Controller.createDestillat(
                Integer.parseInt(nrTxtF.getText()),Double.parseDouble(mængdeTxtF.getText()),
                Double.parseDouble(alkoholPrcentTxtf.getText()), destilleringListView.getSelectionModel().getSelectedItem());

        eksisterendeDestillater.getItems().setAll(Controller.getDestillater());
    }
}
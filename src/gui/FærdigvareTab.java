package gui;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Fad;
import model.FærdigVare;

import java.util.ArrayList;

public class FærdigvareTab implements Updatable{

    private TextField navn = new TextField();
    private TextField pris = new TextField();
    private ListView<FærdigVare> færdigvareListView = new ListView<>();
    private Button opretFærdigvareBtn = new Button("Opret færdigvare");
    private Button opretPrintHistorieBtn = new Button("Print historie");
    private ListView<Fad> fadListView = new ListView<>();
    private TextArea historieTxtArea = new TextArea();

    public GridPane getContent() {
        GridPane pane = new GridPane();

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);


        pane.add(new Label("Navn"), 0, 0);
        pane.add(navn,1,0);

        pane.add(new Label("Pris:"), 0, 1);
        pane.add(pris,1,1);

        pane.add(new Label("Nuværende færdigvare:"), 3,3,2,1);
        færdigvareListView.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
                    if (newItem != null) {
                        historieTxtArea.clear();
                        historieTxtArea.appendText(Controller.printHistorie(færdigvareListView.getSelectionModel().getSelectedItem()));

                    }
        });
        pane.add(færdigvareListView, 3,4,2,1);

        pane.add(new Label("Vælg fad:"), 0,3,2,1);
        fadListView.getItems().setAll(Controller.fadeDerErKlarTilFærdigvare());
        pane.add(fadListView,0,4,2,1);

        pane.add(new Label("Historie over valgt færdigvare"), 5,3,2,1);
        historieTxtArea.setEditable(false);
        pane.add(historieTxtArea,5,4,2,1);

        pane.add(opretFærdigvareBtn,0,5);
        opretFærdigvareBtn.setOnAction(event -> opretFærdigvare());

        return pane;
    }

    private void opretFærdigvare() {

        if (navn.getText().isBlank()) {
            alert("Fejl", "Du skal indtaste et navn på færdigvaren.");
            return;
        }

        int prisInt;
        try {
            prisInt = Integer.parseInt(pris.getText());
        } catch (NumberFormatException e) {
            alert("Fejl", "Pris skal være et gyldigt tal.");
            return;
        }


        Fad valgtFad = fadListView.getSelectionModel().getSelectedItem();
        if (valgtFad == null) {
            alert("Fejl", "Du skal vælge et fad.");
            return;
        }

        try {

            Controller.createFærdigvare(navn.getText(), prisInt, valgtFad);

            alert("Success", "Færdigvaren er oprettet!");
            update();
        }
        catch (Exception ex) {
            alert("Fejl", ex.getMessage());
        }
    }

    private void alert(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.show();
    }


    @Override
    public void update() {
        fadListView.getItems().setAll(Controller.fadeDerErKlarTilFærdigvare());
        færdigvareListView.getItems().setAll(Controller.getFærdigvare());

    }


}

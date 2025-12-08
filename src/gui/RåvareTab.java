package gui;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Oprindelse;
import model.Råvare;

import java.time.LocalDate;

public class RåvareTab implements Updatable {
    private DatePicker høstDatePicker = new DatePicker();
    private TextField navnTxtF = new TextField();
    private TextField typeTxtF = new TextField();
    private TextField mængdeTxtF = new TextField();
    private ListView<Oprindelse> oprindelseListView = new ListView<>();
    private Button opretRåvare = new Button("Opret råvare");
    private ListView<Råvare> råvareListView = new ListView<>();
    private Button opretOprindelseBtn = new Button("Opret oprindelse");
    private TextField oprindelseMark = new TextField();
    private TextField oprindelseGaard = new TextField();


    private Stage popup;

    public GridPane getContent() {
        GridPane pane = new GridPane();

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);


        pane.add(new Label("Navn"), 0, 0);
        pane.add(navnTxtF,1,0);

        pane.add(new Label("Type:"), 0, 1);
        pane.add(typeTxtF,1,1);

        pane.add(new Label("Mængde:"), 0, 2);
        pane.add(mængdeTxtF, 1, 2);

        pane.add(new Label("Høst dato: "), 0, 3);
        høstDatePicker.setPromptText("dd-mm-år");
        høstDatePicker.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && newValue.isAfter(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ugyldig dato");
                alert.setHeaderText("Datoen kan ikke lægge ude i fremtiden");
                alert.showAndWait();
            }
        });
        pane.add(høstDatePicker, 1, 3);


        pane.add(new Label("Vælg oprindelse:"), 0,4,2,1);
        oprindelseListView.getItems().setAll(Controller.oprindelser());
        pane.add(oprindelseListView, 0,5,2,1);

        pane.add(new Label("Nuværende råvarer:"), 2,4,2,1);
        råvareListView.getItems().setAll(Controller.getRåvare());
        pane.add(råvareListView, 2,5,2,1);

        pane.add(opretRåvare,3,6);
        opretRåvare.setOnAction(event -> opretRåvare());

        pane.add(opretOprindelseBtn,0,6);
        opretOprindelseBtn.setOnAction(event -> opretOprindelsePopUp());

        return pane;
    }

    private void opretRåvare(){
        if (oprindelseListView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Manger oprindelse");
            alert.setHeaderText("Vælg en oprindelse");
            alert.setContentText("Opret oprindelse hvis der mangler nogen");
            alert.showAndWait();
        }

        Controller.createRåvare(navnTxtF.getText(), typeTxtF.getText(), Integer.parseInt(mængdeTxtF.getText()),
                høstDatePicker.getValue(), oprindelseListView.getSelectionModel().getSelectedItem());

        råvareListView.getItems().setAll(Controller.getRåvare());
    }

    private void opretOprindelsePopUp() {
        popup = new Stage();
        popup.setTitle("Opret oprindelse");

        Button knap = new Button("Opret oprindelse");

        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.getChildren().add(new Label("Mark: "));
        box.getChildren().add(oprindelseMark);
        box.getChildren().add(new Label("Gaard: "));
        box.getChildren().add(oprindelseGaard);
        box.getChildren().add(knap);

        knap.setOnAction(event -> opretOprindelse());

        popup.setScene(new Scene(box, 200, 250));
        popup.show();


    }

    private void opretOprindelse(){
        if (!oprindelseMark.getText().isEmpty() && !oprindelseGaard.getText().isEmpty()){

            update();

            oprindelseGaard.clear();
            oprindelseMark.clear();

            popup.close();
        }

    }

    @Override
    public void update() {
        oprindelseListView.getItems().setAll(Controller.oprindelser());
        råvareListView.getItems().setAll(Controller.getRåvare());
    }
}

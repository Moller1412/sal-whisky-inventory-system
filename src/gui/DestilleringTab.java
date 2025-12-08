package gui;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Medarbejder;
import model.Råvare;

public class DestilleringTab implements Updatable{
    private TextField NRTxtF = new TextField();
    private TextField antalRåvareTxtF = new TextField();
    private ComboBox<Boolean> erRøgetComboBox = new ComboBox<>();
    private ListView<Råvare> råvareListView = new ListView<>();
    private Button opretDestilleringBtn = new Button("Opret destillering");
    private ListView<Medarbejder> medarbejderListView = new ListView<>();
    private Button opretMedarbejderBtn = new Button("Opret medarbejder");
    private TextField medNavn = new TextField();
    private TextField medNr = new TextField();
    private TextField medTLF = new TextField();

    private Stage popup;

    public GridPane getContent() {
        GridPane pane = new GridPane();

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);


        pane.add(new Label("NR:"), 0, 0);
        pane.add(NRTxtF,1,0);

        pane.add(new Label("Antal Råvare:"), 0, 1);
        pane.add(antalRåvareTxtF,1,1);

        pane.add(new Label("Røget status:"), 0, 2);
        erRøgetComboBox.getItems().setAll(true, false);
        erRøgetComboBox.setValue(false);
        pane.add(erRøgetComboBox, 1, 2);

        pane.add(new Label("Vælg råvare:"), 0,3,2,1);
        råvareListView.getItems().setAll(Controller.getRåvare());
        pane.add(råvareListView, 0,4,2,1);

        pane.add(new Label("Vælg medarbejder:"), 2,3,2,1);
        medarbejderListView.getItems().setAll(Controller.getMedarbejder());
        pane.add(medarbejderListView, 2,4,2,1);

        pane.add(opretDestilleringBtn,3,5);
        opretDestilleringBtn.setOnAction(event -> opretFad());

        pane.add(opretMedarbejderBtn,0,5);
        opretMedarbejderBtn.setOnAction(event -> opretMedarbejderPopUp());

        return pane;
    }

    private void opretFad(){
        //Controller.createFad(Integer.parseInt(NRTxtF.getText()), Double.parseDouble(strTxtF.getText())
               // , erRøgetComboBox.getValue(),leverandørListView.getSelectionModel().getSelectedItem());

        medarbejderListView.getItems().setAll(Controller.getMedarbejder());
    }

    private void opretMedarbejderPopUp() {
        popup = new Stage();
        popup.setTitle("Opret medarbejder");

        Button knap = new Button("Opret medarbejder");

        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.getChildren().add(new Label("Navn: "));
        box.getChildren().add(medNavn);
        box.getChildren().add(new Label("Nr: "));
        box.getChildren().add(medNr);
        box.getChildren().add(new Label("TLF: "));
        box.getChildren().add(medTLF);
        box.getChildren().add(knap);

        knap.setOnAction(event -> opretMedarbejder());

        popup.setScene(new Scene(box, 200, 250));
        popup.show();


    }

    private void opretMedarbejder(){

        if (!medNavn.getText().isEmpty() && !medNr.getText().isEmpty() && !medTLF.getText().isEmpty()){

            Controller.createMedarbejder(Integer.parseInt(medNr.getText()),medNavn.getText(),medTLF.getText());
            update();

            medTLF.clear();
            medNr.clear();
            medNavn.clear();

            popup.close();
        }

    }


    @Override
    public void update() {
        råvareListView.getItems().setAll(Controller.getRåvare());
        medarbejderListView.getItems().setAll(Controller.getMedarbejder());
    }
}

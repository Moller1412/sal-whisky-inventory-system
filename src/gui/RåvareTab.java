package gui;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Fad;
import model.FadType;
import model.Leverandør;

public class RåvareTab implements Updatable {
    private TextField IDTxtF = new TextField();
    private TextField strTxtF = new TextField();
    private ComboBox<FadType> fadTypeComboBox = new ComboBox<>();
    private ListView<Leverandør> leverandørListView = new ListView<>();
    private Button opretFadBtn = new Button("Opret Fad");
    private ListView<Fad> fadListView = new ListView<>();
    private Button opretLeverandørBtn = new Button("Opret leverandør");
    private TextField levNavn = new TextField();
    private TextField levAdresse = new TextField();
    private TextField levTLF= new TextField();

    private Stage popup;

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

        pane.add(new Label("str:"), 0, 1);
        pane.add(strTxtF,1,1);

        pane.add(new Label("Fad type:"), 0, 2);
        fadTypeComboBox.getItems().setAll(FadType.values());
        fadTypeComboBox.setValue(FadType.Sherry);
        pane.add(fadTypeComboBox, 1, 2);

        pane.add(new Label("Vælg leverandør:"), 0,3,2,1);
        leverandørListView.getItems().setAll(Controller.getLeverandører());
        pane.add(leverandørListView, 0,4,2,1);

        pane.add(new Label("Nuværende fade:"), 2,3,2,1);
        fadListView.getItems().setAll(Controller.getFade());
        pane.add(fadListView, 2,4,2,1);

        pane.add(opretFadBtn,3,5);
        opretFadBtn.setOnAction(event -> opretFad());

        pane.add(opretLeverandørBtn,0,5);
        opretLeverandørBtn.setOnAction(event -> opretLeverandørPopUp());

        return pane;
    }

    private void opretFad(){
        Controller.createFad(Integer.parseInt(IDTxtF.getText()), Double.parseDouble(strTxtF.getText())
                ,fadTypeComboBox.getValue(),leverandørListView.getSelectionModel().getSelectedItem());

        fadListView.getItems().setAll(Controller.getFade());
    }

    private void opretLeverandørPopUp() {
        popup = new Stage();
        popup.setTitle("Opret leverandør");

        Button knap = new Button("Opret leverandør");

        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.getChildren().add(new Label("Navn: "));
        box.getChildren().add(levNavn);
        box.getChildren().add(new Label("Adresse: "));
        box.getChildren().add(levAdresse);
        box.getChildren().add(new Label("TLF: "));
        box.getChildren().add(levTLF);
        box.getChildren().add(knap);

        knap.setOnAction(event -> opretLeverandør());

        popup.setScene(new Scene(box, 200, 250));
        popup.show();


    }

    private void opretLeverandør(){

        if (!levNavn.getText().isEmpty() && !levAdresse.getText().isEmpty() && !levTLF.getText().isEmpty()){
            Controller.createLeverandør(levNavn.getText(),levAdresse.getText(),levTLF.getText());
            leverandørListView.getItems().setAll(Controller.getLeverandører());

            levTLF.clear();
            levAdresse.clear();
            levNavn.clear();

            popup.close();
        }

    }

    @Override
    public void update() {

    }
}

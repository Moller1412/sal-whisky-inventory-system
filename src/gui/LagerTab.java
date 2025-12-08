package gui;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class LagerTab implements Updatable{

    private ListView<Lager> LagerListView = new ListView<>();
    private Button opretLagerBtn = new Button("Opret lager");

    private ListView<Reol> ReolListView = new ListView<>();
    private Button opretReolBtn = new Button("Opret Reol");

    private ListView<Række> rækkeListView = new ListView<>();
    private Button opretRækkeBtn = new Button("Opret Række");

    private ListView<Hylde> hyldeListView = new ListView<>();
    Button opretHylde = new Button("Opret Hylde");

    //lager popup
    private Stage lagerPopup;
    private TextField lagerNavn = new TextField();
    private TextField antalKVM = new TextField();

    // Reol popup
    private Stage reolPopup;
    private TextField RoelNr = new TextField();

    public GridPane getContent() {
        GridPane pane = new GridPane();

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);


        pane.add(new Label("Lager:"), 0, 0);
        LagerListView.getItems().setAll(Controller.getLagere());
        pane.add(LagerListView, 0,1,1,1);

        ReolListView.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                ReolListView
                historieTxtArea.appendText(Controller.printHistorie(færdigvareListView.getSelectionModel().getSelectedItem()));

            }
        });

        pane.add(new Label("Reol"), 1,0);
        ReolListView.getItems().setAll(Controller.getReoler());
        pane.add(ReolListView,1,1,1,1);

        pane.add(new Label("Række"), 2,0);
        rækkeListView.getItems().setAll(Controller.getRækker());
        pane.add(rækkeListView,2,1,1,1);

        pane.add(new Label("Række"), 3,0);
        hyldeListView.getItems().setAll(Controller.getHylder());
        pane.add(hyldeListView,3,1,1,1);

        pane.add(opretLagerBtn,0,2);
        opretLagerBtn.setOnAction(event -> opretLagerPopUp());

        pane.add(opretReolBtn,1,2);
        opretReolBtn.setOnAction(event -> setOpretReolPopup());

        pane.add(opretRækkeBtn,2,2);
        opretRækkeBtn.setOnAction(event -> opretRække());

        pane.add(opretHylde,3,2);
        opretHylde.setOnAction(event -> opretHylde());

        return pane;
    }

    private void opretLagerPopUp() {

        lagerPopup = new Stage();
        lagerPopup.setTitle("Opret lager");

        Button knap = new Button("Opret lager");

        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.getChildren().add(new Label("Navn: "));
        box.getChildren().add(lagerNavn);
        box.getChildren().add(new Label("Antal kvadratmeter: "));
        box.getChildren().add(antalKVM);
        box.getChildren().add(knap);

        knap.setOnAction(event -> opretLager());

        lagerPopup.setScene(new Scene(box, 200, 200));
        lagerPopup.show();
    }

    public void opretLager(){
        Controller.createLager(lagerNavn.getText(),Integer.parseInt(antalKVM.getText()));
        lagerNavn.clear();
        antalKVM.clear();
        lagerPopup.close();
        update();
    }

    public void setOpretReolPopup(){

        if (LagerListView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vælg venligst et lager du vil oprette en reol til");
            alert.show();
        } else {
            update();
            reolPopup = new Stage();
            reolPopup.setTitle("Opret reol");

            VBox box = new VBox(10);
            box.setPadding(new Insets(10));
            box.getChildren().add(new Label("NR"));
            box.getChildren().add(RoelNr);

            reolPopup.setScene(new Scene(box, 200, 200));
            reolPopup.show();
        }

    }

    public void opretReol(){

    }

    public void opretRække(){

    }

    public void opretHylde(){

    }

    @Override
    public void update() {
        LagerListView.getItems().setAll(Controller.getLagere());
        ReolListView.getItems().setAll(Controller.getReoler());
        rækkeListView.getItems().setAll(Controller.getRækker());
        hyldeListView.getItems().setAll(Controller.getHylder());
    }

}

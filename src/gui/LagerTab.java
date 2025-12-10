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

    private ListView<Fad> fadListViewListView = new ListView<>();

    //lager popup
    private Stage lagerPopup;
    private TextField lagerNavn = new TextField();
    private TextField antalKVM = new TextField();


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

        // reol
        LagerListView.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            update();

            if (newItem != null) {
                ReolListView.getItems().setAll(LagerListView.getSelectionModel().getSelectedItem().getReoler());

                rækkeListView.getItems().clear();
                hyldeListView.getItems().clear();
                fadListViewListView.getItems().clear();
            }

        });
        pane.add(new Label("Reol"), 1,0);
        pane.add(ReolListView,1,1,1,1);


        // række
        ReolListView.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) ->{
            update();
            if (newItem != null){
                rækkeListView.getItems().setAll(ReolListView.getSelectionModel().getSelectedItem().getRækker());

                hyldeListView.getItems().clear();
                fadListViewListView.getItems().clear();
            }


        });
        pane.add(new Label("Række"), 2,0);
        pane.add(rækkeListView,2,1,1,1);

        // hylde
        rækkeListView.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) ->{
            update();
            if (newItem != null){
                hyldeListView.getItems().setAll(rækkeListView.getSelectionModel().getSelectedItem().getHylder());
                fadListViewListView.getItems().clear();
            }

        });
        pane.add(new Label("Hylde"), 3,0);
        pane.add(hyldeListView,3,1,1,1);

        // fad
        hyldeListView.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) ->{
            update();
            if (newItem != null){
                fadListViewListView.getItems().setAll(hyldeListView.getSelectionModel().getSelectedItem().getFad());
            }
        });
        pane.add(new Label("Fadet på Lager"), 3,3);
        pane.add(fadListViewListView,3,4,1,1);
        fadListViewListView.setPrefHeight(100);

        //knapper
        pane.add(opretLagerBtn,0,2);
        opretLagerBtn.setOnAction(event -> opretLagerPopUp());

        pane.add(opretReolBtn,1,2);
        opretReolBtn.setOnAction(event -> opretReol());

        pane.add(opretRækkeBtn,2,2);
        opretRækkeBtn.setOnAction(event -> opretRække());

        pane.add(opretHylde,3,2);
        opretHylde.setOnAction(event -> opretHylde());

        return pane;
    }

    /// ///////////////////////////////////////////////////////////////////////////////////////////
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


    public void opretReol(){

        if (LagerListView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vælg venligst et lager du vil oprette en reol til");
            alert.show();
        } else {
            int num = 1;
            if (LagerListView.getSelectionModel().getSelectedItem().getReoler().size() > 0) {
                num = LagerListView.getSelectionModel().getSelectedItem().getReoler().size() + 1;
            }

            Controller.createReol(num, LagerListView.getSelectionModel().getSelectedItem());
            update();
        }
    }

    public void opretRække(){

        if (ReolListView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vælg venligst et reol du vil oprette en række til");
            alert.show();
        } else {
            int num = 1;
            if (ReolListView.getSelectionModel().getSelectedItem().getRækker().size() > 0) {
                num = ReolListView.getSelectionModel().getSelectedItem().getRækker().size() + 1;
            }

            Controller.createRække(num, ReolListView.getSelectionModel().getSelectedItem());
            update();
        }

    }

    public void opretHylde(){

        if (rækkeListView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vælg venligst et række du vil oprette en hylde til");
            alert.show();
        } else {
            int num = 1;
            if (rækkeListView.getSelectionModel().getSelectedItem().getHylder().size() > 0) {
                num = rækkeListView.getSelectionModel().getSelectedItem().getHylder().size() + 1;
            }

            Controller.createHylde(num, rækkeListView.getSelectionModel().getSelectedItem());
            update();
        }

    }

    @Override
    public void update() {
        Lager valgtLager = LagerListView.getSelectionModel().getSelectedItem();
        Reol valgtReol = ReolListView.getSelectionModel().getSelectedItem();
        Række valgtRække = rækkeListView.getSelectionModel().getSelectedItem();
        Hylde valgtHylde = hyldeListView.getSelectionModel().getSelectedItem();


        LagerListView.getItems().setAll(Controller.getLagere());


        if (valgtLager != null){
            ReolListView.getItems().setAll(valgtLager.getReoler());

        }
        if (valgtReol != null) {
            rækkeListView.getItems().setAll(valgtReol.getRækker());

        }
        if (valgtRække != null) {
            hyldeListView.getItems().setAll(valgtRække.getHylder());

        }
        if (valgtHylde != null) {
            fadListViewListView.getItems().setAll(valgtHylde.getFad());
        }

    }

    public void clearAllLvw(){
        ReolListView.getItems().clear();
        rækkeListView.getItems().clear();
        hyldeListView.getItems().clear();
        fadListViewListView.getItems().clear();

    }


}
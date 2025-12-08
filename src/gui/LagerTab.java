package gui;

import Controller.Controller;
import Storage.Storage;
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


        pane.add(new Label("Lager:"), 0, 0);
        LagerListView.getItems().setAll(Storage.getLagere());
        pane.add(LagerListView, 0,1,1,1);

        pane.add(new Label("Reol"), 1,0);
        ReolListView.getItems().setAll(Storage.getReoler());
        pane.add(ReolListView,1,1,1,1);

        pane.add(new Label("Række"), 2,0);
        rækkeListView.getItems().setAll(Storage.getRækker());
        pane.add(rækkeListView,2,1,1,1);

        pane.add(opretLagerBtn,0,2);
        //opretLagerBtn.setOnAction(event -> opretDestillering());

        pane.add(opretReolBtn,1,2);
        //opretReolBtn.setOnAction(event -> opretMedarbejderPopUp());

        pane.add(opretRækkeBtn,2,2);

        return pane;
    }




    @Override
    public void update() {

    }

}

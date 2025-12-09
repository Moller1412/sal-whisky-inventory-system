package gui;

import Controller.Controller;
import Storage.ListStorage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Destillat;
import model.Fad;
import model.FærdigVare;
import Controller.Storage;

public class Gui extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        TabPane tabPane = new TabPane();

        //opret tabs og content
        DestillatTab destillatContent = new DestillatTab();
        Tab destillatTab = new Tab("Opret destillat",destillatContent.getContent());

        FadTab fadContent = new FadTab();
        Tab fadTab = new Tab("Opret fad", fadContent.getContent());

        FærdigvareTab færdigvareContent = new FærdigvareTab();
        Tab færdigvareTab = new Tab("Opret færdigvare", færdigvareContent.getContent());

        RåvareTab råvareContent = new RåvareTab();
        Tab råvareTab = new Tab("Opret Råvare", råvareContent.getContent());

        DestilleringTab destilleringContent = new DestilleringTab();
        Tab destilleringTab = new Tab("Opret destillering", destilleringContent.getContent());

        LagerTab lagerContent = new LagerTab();
        Tab lagerTab = new Tab("Lager", lagerContent.getContent());

        // brug updatable
        færdigvareTab.setOnSelectionChanged(event -> færdigvareContent.update());
        fadTab.setOnSelectionChanged(event -> fadContent.update());
        destillatTab.setOnSelectionChanged(event -> destillatContent.update());
        råvareTab.setOnSelectionChanged(event -> råvareContent.update());
        destilleringTab.setOnSelectionChanged(event -> destilleringContent.update());

        destillatTab.setClosable(false);
        færdigvareTab.setClosable(false);
        fadTab.setClosable(false);
        råvareTab.setClosable(false);
        destilleringTab.setClosable(false);
        lagerTab.setClosable(false);

        tabPane.getTabs().addAll(lagerTab, destillatTab, fadTab, færdigvareTab, råvareTab, destilleringTab);

        stage.setTitle("Sall Whisky");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void stop() {
        String fileName = "src/storage.ser";
        Storage storage = Controller.getStorage();
        ListStorage.saveStorage(fileName, storage);
        System.out.println("Storage er gemt i " + fileName);
    }

    // -------------------------------------------------------------------------

    private void initContent(GridPane pane) {

    }
}

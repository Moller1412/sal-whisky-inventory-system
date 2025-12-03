package gui;

import Controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Fad;
import model.FærdigVare;

public class Gui extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        TabPane tabPane = new TabPane();

        Tab destillatTab = new Tab("Opret destillat",new DestillatTab().getContent());
        Tab fadTab = new Tab("Opret fad", new FadTab().getContent());

        FærdigvareTab færdigvareContent = new FærdigvareTab();
        Tab færdigvareTab = new Tab("Opret færdigvare", færdigvareContent.getContent());


        færdigvareTab.setOnSelectionChanged(event -> færdigvareContent.update());

        destillatTab.setClosable(false);
        færdigvareTab.setClosable(false);
        fadTab.setClosable(false);

        tabPane.getTabs().addAll(destillatTab, fadTab, færdigvareTab);

        stage.setTitle("Sall Whisky");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();


    }

    // -------------------------------------------------------------------------

    private void initContent(GridPane pane) {

    }
}

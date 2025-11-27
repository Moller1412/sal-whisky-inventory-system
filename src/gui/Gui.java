package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        TabPane tabPane = new TabPane();
        Tab destillatTab = new Tab("Opret destillat",new DestillatTab().getContent());
        Tab fadTab = new Tab("opret fad", new FadTab().getContent());

        tabPane.getTabs().addAll(destillatTab, fadTab);

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

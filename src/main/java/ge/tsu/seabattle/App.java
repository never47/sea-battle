package ge.tsu.seabattle;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        SceneBuilder sceneBuilder = new SceneBuilder(stage);
        sceneBuilder.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
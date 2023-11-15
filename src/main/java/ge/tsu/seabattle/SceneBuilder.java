package ge.tsu.seabattle;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class SceneBuilder {
    private static SceneBuilder instance;
    private Stage stage;
    private final String first_scene = "fxml_files/point_ship.fxml";
    private final String second_scene = "fxml_files/game-view.fxml";
    private final int default_width = 1280;
    private final int default_height = 720;

    public SceneBuilder(Stage stage) {
        this.stage=stage;
        stage.setTitle("Sea Battle");
        instance = this;
    }

    void load(){
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(first_scene));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), default_width, default_height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }


    void change(){
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(second_scene));
        javafx.scene.Scene scene = null;
        try {
            scene = new javafx.scene.Scene(fxmlLoader.load(), default_width, default_height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static SceneBuilder getInstance(){
        return instance;
    }
}

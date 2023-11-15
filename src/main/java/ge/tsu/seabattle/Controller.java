package ge.tsu.seabattle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Controller {
    @FXML
    private Pane rectanglesPane;
    private Board board;

    @FXML
    public void initialize() {
        board = new Board(rectanglesPane);
    }

    public void startGame(ActionEvent actionEvent) {
        board.detectShips();
    }
}
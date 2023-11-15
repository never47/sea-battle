package ge.tsu.seabattle;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private boolean[][] my_grid = new boolean[10][10];
    private List<DraggedRectangle> draggedRectangleList = new ArrayList<>();
    private int ships_count = 0;

    public Board() {
    }

    public Board(Pane rectanglesPane) {
        rectanglesPane.getChildren().forEach(node -> {
            if (node instanceof Rectangle) {
                DraggedRectangle draggedRectangle = new DraggedRectangle((Rectangle) node, draggedRectangleList);
                draggedRectangleList.add(draggedRectangle);
                draggedRectangle.setupMouseEvents();
                ships_count++;
            }
        });
    }

    public void detectShips(){

    }
}

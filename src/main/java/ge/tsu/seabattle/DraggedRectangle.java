package ge.tsu.seabattle;

import javafx.scene.shape.Rectangle;

import java.util.List;

/*
заменю прямоугольник на контейнер для фоток, после выставления корабля ->
    сменю фрейм типа красное поле вокруг(маржин)
 */
public class DraggedRectangle{
    private List<DraggedRectangle> draggedRectangleList;
    private Rectangle rectangle;
    private double mouseAnchorX;
    private double mouseAnchorY;
    private int length;
    private boolean isHorizontal = true;
    private int x; // coords
    private int y; // coords

    private int saved_x; //saved coords in grid
    private int saved_y; //saved coords in grid
    private final int margin = 50;
    private final int cell_size = 60;

    public DraggedRectangle(Rectangle rectangle, List<DraggedRectangle> draggedRectangleList) {
        this.rectangle = rectangle;
        saved_x = (int) rectangle.getLayoutX();
        saved_y = (int) rectangle.getLayoutY();
        length = (int) (rectangle.getWidth()/ cell_size);
        this.draggedRectangleList=draggedRectangleList;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    private boolean isCollision() {
        for (DraggedRectangle otherRectangle : draggedRectangleList) {
            if (otherRectangle.getRectangle() != rectangle) {
                double x1 = rectangle.getLayoutX() - margin;
                double y1 = rectangle.getLayoutY() - margin;
                double width1 = rectangle.getWidth() + 2 * margin;
                double height1 = rectangle.getHeight() + 2 * margin;

                double x2 = otherRectangle.getRectangle().getLayoutX();
                double y2 = otherRectangle.getRectangle().getLayoutY();
                double width2 = otherRectangle.getRectangle().getWidth();
                double height2 = otherRectangle.getRectangle().getHeight();

                if (x1 < x2 + width2 &&
                        x1 + width1 > x2 &&
                        y1 < y2 + height2 &&
                        y1 + height1 > y2) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkGridBorderds(){
        if(x == 0) x = cell_size;
        if(y == 0) y = cell_size;

        if(isHorizontal && x+length*cell_size>660) x=660-length*cell_size;
        if(!isHorizontal && y+length*cell_size>660) y=660-length*cell_size;

        if(isCollision() || mouseAnchorX>720) {
            rectangle.setLayoutX(saved_x);
            rectangle.setLayoutY(saved_y);
        }else{
            rectangle.setLayoutX(x);
            rectangle.setLayoutY(y);
            saved_x = x;
            saved_y = y;
        }
    }

    private void changeWidthHeight(){
        double temp = rectangle.getWidth();
        rectangle.setWidth(rectangle.getHeight());
        rectangle.setHeight(temp);
    }

    public void setupMouseEvents() {
        //станавливает обработчик события для события "нажатие мыши" на прямоугольнике.
        rectangle.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });

        //станавливает обработчик события для события "перетаскивание мыши" на прямоугольнике
        rectangle.setOnMouseDragged(mouseEvent -> {
            rectangle.setLayoutX(mouseEvent.getSceneX()-mouseAnchorX);
            rectangle.setLayoutY(mouseEvent.getSceneY()-mouseAnchorY);
        });

        //устанавливает обработчик события для события "отпускание кнопки мыши" над прямоугольником.
        rectangle.setOnMouseReleased(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();

            x = (int) ((mouseAnchorX/cell_size) % 11) * cell_size;
            y = (int) ((mouseAnchorY/cell_size) % 11) * cell_size;

            checkGridBorderds();
        });

        //дважды клик
        rectangle.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==2){
                if (isHorizontal) {
                    isHorizontal = false;
                    changeWidthHeight();
                } else {
                    isHorizontal = true;
                    changeWidthHeight();
                }
                if (isCollision()
                 || isHorizontal && x+length*cell_size>660
                        || !isHorizontal && y+length*cell_size>660
                    ) {
                    changeWidthHeight();
                    isHorizontal = !isHorizontal;
                }
            }
        });
    }
}

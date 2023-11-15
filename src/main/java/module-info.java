module ge.tsu.seabattle {
    requires javafx.controls;
    requires javafx.fxml;


    opens ge.tsu.seabattle to javafx.fxml;
    exports ge.tsu.seabattle;
}
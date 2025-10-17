module co.edu.uniquindio.poo.parcial2 {
    requires javafx.controls;
    requires javafx.fxml;

    // Export packages

    exports co.edu.uniquindio.poo.parcial2.model;
    exports co.edu.uniquindio.poo.parcial2.facade;
    exports co.edu.uniquindio.poo.parcial2.decorator;

    // Open packages to javafx.fxml for reflection
    opens co.edu.uniquindio.poo.parcial2 to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial2.controller to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial2.model to javafx.fxml;
}
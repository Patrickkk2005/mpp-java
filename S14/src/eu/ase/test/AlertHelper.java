package eu.ase.test;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertHelper {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setAlertType(alertType);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}

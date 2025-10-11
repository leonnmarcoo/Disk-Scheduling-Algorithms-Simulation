package Frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class LandingController {

    @FXML
    private void handleLaunchSimulator(ActionEvent event) {
        try {
            // Load the main simulator view
            Parent mainView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Scene mainScene = new Scene(mainView, 1400, 800);
            
            // Get the current stage
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Set the new scene
            currentStage.setScene(mainScene);
            currentStage.setTitle("Disk Scheduling Algorithms Simulator");
            currentStage.setMinWidth(1200);
            currentStage.setMinHeight(700);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

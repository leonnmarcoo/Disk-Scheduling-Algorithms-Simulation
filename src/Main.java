import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the landing page first
        Parent root = FXMLLoader.load(getClass().getResource("Frontend/LandingPage.fxml"));
        
        Scene scene = new Scene(root, 1400, 850);
        
        primaryStage.setTitle("Disk Scheduling Algorithms - Home");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

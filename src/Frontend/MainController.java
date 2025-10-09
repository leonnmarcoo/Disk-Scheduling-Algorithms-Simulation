package Frontend;

import Backend.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML private ComboBox<String> algorithmComboBox;
    @FXML private ComboBox<String> scenarioComboBox;
    @FXML private TextField requestCountField;
    @FXML private TextField diskSizeField;
    @FXML private TextField headPositionField;
    @FXML private TextField customRequestsField;
    @FXML private Button generateButton;
    @FXML private Button runButton;
    @FXML private Button resetButton;
    @FXML private ListView<String> requestListView;
    @FXML private LineChart<Number, Number> seekChart;
    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private Label totalSeekLabel;
    @FXML private Label avgSeekLabel;
    @FXML private Label algorithmLabel;
    @FXML private TextArea resultTextArea;
    @FXML private VBox customRequestsBox;

    private List<DiskRequest> currentRequests;
    private RequestGenerator requestGenerator;
    private SchedulingResult currentResult;

    @FXML
    public void initialize() {
        requestGenerator = new RequestGenerator();
        currentRequests = new ArrayList<>();

        // Setup algorithm combo box
        algorithmComboBox.setItems(FXCollections.observableArrayList(
            "FCFS", "SSTF", "SCAN", "C-SCAN"
        ));
        algorithmComboBox.getSelectionModel().selectFirst();

        // Setup scenario combo box
        scenarioComboBox.setItems(FXCollections.observableArrayList(
            "Random", "Clustered", "Sequential (Asc)", "Sequential (Desc)", "Custom"
        ));
        scenarioComboBox.getSelectionModel().selectFirst();

        // Set default values
        requestCountField.setText("10");
        diskSizeField.setText("200");
        headPositionField.setText("50");

        // Setup chart
        seekChart.setAnimated(false);
        seekChart.setCreateSymbols(true);
        seekChart.setLegendVisible(false);

        // Show/hide custom requests field based on scenario
        scenarioComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            customRequestsBox.setVisible("Custom".equals(newVal));
            customRequestsBox.setManaged("Custom".equals(newVal));
        });

        customRequestsBox.setVisible(false);
        customRequestsBox.setManaged(false);
    }

    @FXML
    private void handleGenerate() {
        try {
            int count = Integer.parseInt(requestCountField.getText());
            int diskSize = Integer.parseInt(diskSizeField.getText());
            String scenario = scenarioComboBox.getValue();

            if (count <= 0 || diskSize <= 0) {
                showAlert("Invalid Input", "Request count and disk size must be positive numbers.");
                return;
            }

            switch (scenario) {
                case "Random":
                    currentRequests = requestGenerator.generateRandom(count, diskSize);
                    break;
                case "Clustered":
                    currentRequests = requestGenerator.generateClustered(count, diskSize, 3);
                    break;
                case "Sequential (Asc)":
                    currentRequests = requestGenerator.generateSequential(count, diskSize, true);
                    break;
                case "Sequential (Desc)":
                    currentRequests = requestGenerator.generateSequential(count, diskSize, false);
                    break;
                case "Custom":
                    currentRequests = parseCustomRequests(customRequestsField.getText(), diskSize);
                    break;
            }

            updateRequestList();
            resultTextArea.setText("Requests generated. Click 'Run Algorithm' to simulate.");

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for request count and disk size.");
        }
    }

    private List<DiskRequest> parseCustomRequests(String input, int diskSize) {
        List<DiskRequest> requests = new ArrayList<>();
        String[] parts = input.trim().split("[,\\s]+");
        
        for (int i = 0; i < parts.length; i++) {
            try {
                int track = Integer.parseInt(parts[i].trim());
                if (track >= 0 && track < diskSize) {
                    requests.add(new DiskRequest(track, i));
                }
            } catch (NumberFormatException e) {
                // Skip invalid entries
            }
        }
        
        return requests;
    }

    @FXML
    private void handleRun() {
        if (currentRequests.isEmpty()) {
            showAlert("No Requests", "Please generate or enter disk requests first.");
            return;
        }

        try {
            int headPosition = Integer.parseInt(headPositionField.getText());
            int diskSize = Integer.parseInt(diskSizeField.getText());
            String algorithm = algorithmComboBox.getValue();

            if (headPosition < 0 || headPosition >= diskSize) {
                showAlert("Invalid Input", "Head position must be within disk size range.");
                return;
            }

            DiskScheduler scheduler = getScheduler(algorithm);
            currentResult = scheduler.schedule(currentRequests, headPosition, diskSize);

            displayResults(scheduler.getAlgorithmName());
            visualizeResults();

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid head position.");
        }
    }

    private DiskScheduler getScheduler(String algorithm) {
        switch (algorithm) {
            case "FCFS":
                return new FCFSScheduler();
            case "SSTF":
                return new SSTFScheduler();
            case "SCAN":
                return new SCANScheduler();
            case "C-SCAN":
                return new CSCANScheduler();
            default:
                return new FCFSScheduler();
        }
    }

    private void displayResults(String algorithmName) {
        algorithmLabel.setText(algorithmName);
        totalSeekLabel.setText(String.valueOf(currentResult.getTotalSeekTime()));
        avgSeekLabel.setText(String.format("%.2f", currentResult.getAverageSeekTime()));

        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(algorithmName).append(" Results ===\n\n");
        sb.append("Seek Sequence: ");
        sb.append(currentResult.getSeekSequence().toString()).append("\n\n");
        
        sb.append("Seek Details:\n");
        List<Integer> sequence = currentResult.getSeekSequence();
        List<Integer> distances = currentResult.getSeekDistances();
        
        for (int i = 0; i < distances.size(); i++) {
            sb.append(String.format("%d → %d : %d tracks\n", 
                sequence.get(i), sequence.get(i + 1), distances.get(i)));
        }
        
        sb.append(String.format("\nTotal Seek Time: %d tracks\n", currentResult.getTotalSeekTime()));
        sb.append(String.format("Average Seek Time: %.2f tracks\n", currentResult.getAverageSeekTime()));

        resultTextArea.setText(sb.toString());
    }

    private void visualizeResults() {
        seekChart.getData().clear();
        
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Head Movement");

        List<Integer> sequence = currentResult.getSeekSequence();
        for (int i = 0; i < sequence.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, sequence.get(i)));
        }

        seekChart.getData().add(series);
        
        // Animate the chart
        animateChart(series);
    }

    private void animateChart(XYChart.Series<Number, Number> series) {
        Timeline timeline = new Timeline();
        
        for (int i = 0; i < series.getData().size(); i++) {
            XYChart.Data<Number, Number> data = series.getData().get(i);
            
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(i * 300),
                event -> {
                    if (data.getNode() != null) {
                        data.getNode().setStyle("-fx-background-color: #FF6B6B; -fx-background-radius: 5px;");
                    }
                }
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        
        timeline.play();
    }

    private void updateRequestList() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < currentRequests.size(); i++) {
            items.add(String.format("Request %d: Track %d", 
                i + 1, currentRequests.get(i).getTrackNumber()));
        }
        requestListView.setItems(items);
    }

    @FXML
    private void handleReset() {
        currentRequests.clear();
        requestListView.getItems().clear();
        seekChart.getData().clear();
        resultTextArea.clear();
        totalSeekLabel.setText("0");
        avgSeekLabel.setText("0.00");
        algorithmLabel.setText("None");
        
        requestCountField.setText("10");
        diskSizeField.setText("200");
        headPositionField.setText("50");
        customRequestsField.clear();
        
        algorithmComboBox.getSelectionModel().selectFirst();
        scenarioComboBox.getSelectionModel().selectFirst();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

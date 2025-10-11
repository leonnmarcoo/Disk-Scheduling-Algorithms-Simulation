# Quick Start Guide

## Running the Application

### Option 1: Using the run script (macOS/Linux)
```bash
./run.sh
```

### Option 2: Manual compilation and execution

**Compile:**
```bash
javac --module-path lib --add-modules javafx.controls,javafx.fxml -d bin src/Backend/*.java src/Frontend/*.java src/Main.java
```

**Run:**
```bash
java --module-path lib --add-modules javafx.controls,javafx.fxml -cp bin Main
```

## How to Use

### Landing Page
When you first launch the application, you'll see a beautiful landing page featuring:
- **Hero Section**: Introduction to the project
- **Algorithms Overview**: Cards showing all 4 algorithms with descriptions
- **Features**: Showcase of key features
- **Tech Stack**: Technologies and tools used
- **Team**: Meet the developers and contributors

Click the **"Launch Simulator"** button to access the main application.

### Main Simulator

1. **Choose an Algorithm**: Select from FCFS, SSTF, SCAN, or C-SCAN
2. **Select Request Scenario**: 
   - Random: Randomly distributed requests
   - Clustered: Requests grouped around certain areas
   - Sequential (Asc/Desc): Sequential patterns
   - Custom: Enter your own requests
3. **Configure Parameters**:
   - Request Count: Number of requests to generate (e.g., 10)
   - Disk Size: Total disk tracks (e.g., 200)
   - Head Position: Starting position (e.g., 50)
4. **Generate**: Click "Generate" to create requests
5. **Run**: Click "Run Algorithm" to simulate
6. **Analyze**: View the visualization and metrics

## Example Scenarios

### Test Case 1: Classic Example
- Algorithm: SSTF
- Requests: 98, 183, 37, 122, 14, 124, 65, 67
- Head Position: 53
- Disk Size: 200

### Test Case 2: Heavy Load
- Algorithm: SCAN
- Request Count: 20
- Scenario: Random
- Head Position: 100
- Disk Size: 200

### Test Case 3: Comparison
Run all 4 algorithms with the same request set to compare performance:
- Use Custom scenario with: 82, 170, 43, 140, 24, 16, 190
- Head Position: 50
- Disk Size: 200

## Tips

- Use the Reset button to clear all data and start fresh
- The chart animates the head movement for better visualization
- Custom requests should be comma or space separated
- The algorithm showing the lowest Total Seek Time is most efficient for that particular request pattern

## Opening in Scene Builder

The UI file `src/Frontend/MainView.fxml` can be opened in Scene Builder for visual editing:
1. Download Scene Builder from https://gluonhq.com/products/scene-builder/
2. Open `src/Frontend/MainView.fxml`
3. Edit the UI visually
4. Save and recompile

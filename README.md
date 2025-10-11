# Disk Scheduling Algorithms Simulation

A comprehensive JavaFX application that simulates and visualizes various disk scheduling algorithms used in operating systems for I/O management.

## 🎯 Features

### Implemented Algorithms

1. **FCFS (First-Come, First-Served)**
   - Processes disk requests in the order they arrive
   - Simple but may not be optimal for performance

2. **SSTF (Shortest Seek Time First)**
   - Prioritizes disk requests closest to the current head position
   - Reduces average seek time but may cause starvation

3. **SCAN (Elevator Algorithm)**
   - Disk arm moves in one direction servicing requests
   - Reverses direction when reaching the end
   - Fair distribution of service

4. **C-SCAN (Circular SCAN)**
   - Similar to SCAN but jumps to the opposite end
   - More uniform wait time distribution

### Request Scenarios

- **Random**: Randomly distributed requests across the disk
- **Clustered**: Requests grouped around certain areas (simulates real-world file access patterns)
- **Sequential (Ascending/Descending)**: Sequential access patterns
- **Custom**: User-defined request sequences

### Performance Metrics

- **Total Seek Time**: Sum of all head movements
- **Average Seek Time**: Mean distance traveled per request
- **Seek Sequence Visualization**: Real-time animated chart showing head movement
- **Detailed Results**: Step-by-step breakdown of head movements

## 🎨 User Interface

The application features a modern, flat design with:
- **Landing Page**: Beautiful hero section introducing the project, algorithms overview, features showcase, tech stack display, and team member profiles
- **Simulator Interface**: Clean 2-color palette (Blue #4A90E2 and Dark Gray-Blue #2C3E50)
- Intuitive layout with configuration panel and visualization area
- Real-time animated visualization of disk head movement
- Responsive design with proper spacing and shadows
- Smooth transitions between landing page and simulator

## 🚀 Getting Started

### Prerequisites

- Java JDK 11 or higher
- JavaFX SDK (included in `lib/` folder)

### Running the Application

#### On macOS/Linux:

```bash
javac --module-path lib --add-modules javafx.controls,javafx.fxml -d bin src/**/*.java src/*.java
java --module-path lib --add-modules javafx.controls,javafx.fxml -cp bin Main
```

#### On Windows:

```cmd
javac --module-path lib --add-modules javafx.controls,javafx.fxml -d bin src\**\*.java src\*.java
java --module-path lib --add-modules javafx.controls,javafx.fxml -cp bin Main
```

### Using the Application

1. **Landing Page**: When you start the application, you'll see a beautiful landing page with:
   - Hero section introducing the project
   - Overview of all 4 algorithms with descriptions
   - Feature highlights
   - Tech stack information
   - Team member profiles
2. **Launch Simulator**: Click the "Launch Simulator" button to enter the main application
3. **Select Algorithm**: Choose from FCFS, SSTF, SCAN, or C-SCAN
4. **Choose Scenario**: Select a request generation pattern or use custom requests
5. **Set Parameters**:
   - Request Count: Number of disk requests to generate
   - Disk Size: Total number of tracks on the disk
   - Head Position: Initial position of the disk head
6. **Generate Requests**: Click "Generate" to create request sequence
7. **Run Simulation**: Click "Run Algorithm" to execute the scheduling algorithm
8. **View Results**: Analyze the visualization and detailed metrics

## 📁 Project Structure

```
src/
├── Main.java                          # Application entry point
├── Backend/
│   ├── DiskRequest.java              # Request data model
│   ├── DiskScheduler.java            # Scheduler interface
│   ├── FCFSScheduler.java            # FCFS implementation
│   ├── SSTFScheduler.java            # SSTF implementation
│   ├── SCANScheduler.java            # SCAN implementation
│   ├── CSCANScheduler.java           # C-SCAN implementation
│   ├── SchedulingResult.java         # Result data model
│   ├── RequestGenerator.java         # Request generation utility
│   └── AlgorithmTest.java            # Testing utility
├── Frontend/
│   ├── LandingController.java        # Landing page controller
│   ├── LandingPage.fxml              # Landing page layout
│   ├── MainController.java           # Simulator controller
│   └── MainView.fxml                 # Simulator UI layout (Scene Builder compatible)
└── CSS/
    ├── landing.css                   # Landing page styles
    └── style.css                     # Simulator styles
```

## 🎓 Educational Value

This project demonstrates:
- **Operating Systems Concepts**: Practical implementation of disk scheduling algorithms
- **JavaFX Development**: Modern UI development with FXML and CSS
- **Algorithm Analysis**: Performance comparison between different strategies
- **Software Design Patterns**: MVC architecture, interface-based design
- **Data Visualization**: Real-time charting and animation

## 🔧 Customization

### Editing the UI

The UI is built using FXML, which can be opened and edited in Scene Builder:
1. Download Scene Builder from [Gluon](https://gluonhq.com/products/scene-builder/)
2. Open `src/Frontend/MainView.fxml`
3. Make your modifications
4. Save and run the application

### Modifying Colors

Edit `src/CSS/style.css` to change the color scheme:
- Primary color: `#4A90E2` (Blue)
- Secondary color: `#2C3E50` (Dark Gray-Blue)
- Accent color: `#FF6B6B` (Coral Red)
- Background: `#F5F7FA` (Light Gray)

### Adding New Algorithms

1. Create a new class implementing `DiskScheduler` interface
2. Implement the `schedule()` method with your algorithm logic
3. Add the algorithm to the combo box in `MainController.java`
4. Update the `getScheduler()` method to return your new scheduler

## 📊 Algorithm Comparison

| Algorithm | Average Case | Best Case | Worst Case | Starvation Risk |
|-----------|--------------|-----------|------------|-----------------|
| FCFS      | O(n)         | O(n)      | O(n)       | No              |
| SSTF      | O(n²)        | O(n)      | O(n²)      | Yes             |
| SCAN      | O(n)         | O(n)      | O(n)       | No              |
| C-SCAN    | O(n)         | O(n)      | O(n)       | No              |

## 📝 License

This project is open source and available for educational purposes.

## 👨‍💻 Author

Created as an educational project for operating systems coursework.


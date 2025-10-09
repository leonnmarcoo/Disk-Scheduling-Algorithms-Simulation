#!/bin/bash

# Compile the project
echo "Compiling project..."
javac --module-path lib --add-modules javafx.controls,javafx.fxml -d bin src/Backend/*.java src/Frontend/*.java src/Main.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running application..."
    java --module-path lib --add-modules javafx.controls,javafx.fxml -cp bin Main
else
    echo "Compilation failed!"
    exit 1
fi

#!/bin/bash

echo "=== Disk Scheduling Algorithms - Quick Test ==="
echo ""

# Compile the test
echo "Compiling test suite..."
javac -d bin src/Backend/*.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo ""
    
    # Run the algorithm test
    java -cp bin Backend.AlgorithmTest
    
    echo ""
    echo "=== Test Complete ==="
    echo ""
    echo "To run the GUI application, execute: ./run.sh"
else
    echo "✗ Compilation failed!"
    exit 1
fi

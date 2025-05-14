Ex3 - Algorithms for 2D Arrays, Object-Oriented Programming, and JUnit
Ariel University, School of Computer Science, 2024

Overview
This project implements a set of algorithms for 2D arrays that represent a maze, along with a comprehensive testing suite to verify correctness and runtime efficiency. The implementation follows object-oriented principles and includes a graphical user interface to visualize the algorithms in action.
Project Description
The project focuses on designing and implementing various algorithms for navigating and manipulating 2D arrays (representing a maze). The implementation includes:

Core data structures for representing 2D coordinates and pixels
Maze representation using 2D arrays
Pathfinding algorithms including shortest path
JUnit testing to ensure algorithm correctness
A graphical user interface to demonstrate the solution

Project Structure
The project consists of five primary Java files:

Index2D.java - Implementation of 2D coordinates
Pixel2D.java - Interface for representing pixels in a 2D array
Map2D.java - Interface defining the maze model and required algorithms
Map.java - Main implementation class with all required algorithms
MapTest.java - JUnit tests for the Map class

Additionally:

Ex3.java - Main class with GUI implementation using StdDraw

Algorithms Implemented
The project implements several key algorithms for 2D array manipulation, including:

Shortest path finding algorithm
Connected components analysis
Distance calculation between points in the maze
Path validation and optimization

All algorithms are documented within the Map.java file, with detailed explanations of their functionality and efficiency.
Requirements

Java JDK 11 or higher
JUnit 5 for running tests
StdDraw library for GUI visualization

Setup and Execution

Clone this repository
Make sure all dependencies are properly configured
Compile the project using your preferred Java IDE or command line tools
Run the Ex3 class to launch the GUI demonstration
Execute the JUnit tests to verify algorithm correctness

Documentation
Detailed documentation is provided within the code, with special emphasis on the algorithmic methods in the Map.java file. The documentation explains the algorithms' logic, time complexity, and implementation details.
References

Shortest Path Algorithm Explanation

My Pathfinding Visualizer
======================

Description
-----------

Pathfinding Visualizer is a java swing application for visualizing pathfinding algorithms like BFS, dijkstra's and A*. It consists of a 56x28 grid that simply steps through the pathfinding algorithm.

How to Run
----------
1. Download the java (if you haven't already)
2. Download the [github-file](https://github.com/)
3. Run the jar file

Setup
--

* Basic user controls (start & reset)
* User defined execution speed
* User defined start/goal/wall cells

![Setup](MPV/src/Setup.gif)

Run with Variable Speed
--

You may change the frequency of the visualization during runtime.
- By default, the frequency is 250ms/step.
- Frequency range is (10 - 1000)ms/step.
- You can vary the frequency with a step of 20ms/steps.

See the running visualizer with A* algorithm:

![Run](MPV/src/Run.gif)

###  Color Key

* Magenta - Start cell
* Red     - Goal cell
* Black   - Wall cell or a cell with highest weight
* Green   - Cell has been visited and all its edges examined.
* Blue    - Cell has been found by the pathfinder but is yet to be examined
* Cyan    - Cell is part of the shortest path from the start cell to the goal

Algorithms
----------
The program currently allows you to search the grid with BFS, dijkstra's algorithm or A*.


 
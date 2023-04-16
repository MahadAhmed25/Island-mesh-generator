# PathFinder Library

# Author

This implementation is made by Mahad Ahmed as a part of an academic project in the course "Software Design I - Introduction to Software Development" (SE2AA4) at McMaster University.

# Rationale

The Graph class is a fundamental data structure in programming, which is used to represent many real-life problems. Pathfinding is one such application that uses graphs to represent a network of connections, where nodes represent the locations, and edges represent the paths between them. A pathfinding algorithm's goal is to find the shortest path between two nodes in the graph.

This implementation adds the GraphShortestPath class, which is an algorithm based on Djiskstras algorithm of finding a path between 2 nodes.

# Implementation

The implementation uses Dijkstra's algorithm, a famous algorithm for finding the shortest path between two nodes in a graph with non-negative edge weights. It starts by marking the starting node with a distance of 0 and all other nodes with infinity distance. It then visits each node in the graph, updating its distance to the starting node if a shorter path is found. It does so by considering the neighboring nodes and adding the distance to the current node's distance. The algorithm then selects the node with the shortest distance from the unvisited nodes and repeats the process.

The implementation uses a priority queue to keep track of unvisited nodes, sorted by their tentative distance. It also keeps track of visited nodes to avoid revisiting them. Finally, it uses two maps, distances and predecessors, to keep track of the tentative distance of each node and its predecessor in the path, respectively.

# Usage

To use this implementation, first, create a Graph object, add nodes, and edges to it. Then, create a new instance of GraphShortestPath, passing the Graph object to its constructor. Finally, call the findPath method on the GraphShortestPath object, passing the start and end nodes, to get the list of nodes in the shortest path.

```
Graph graph = new Graph();
graph.addNode(1);
graph.addNode(2);
graph.addNode(3);
graph.addEdge(graph.getNode(1), graph.getNode(2));
graph.addEdge(graph.getNode(1), graph.getNode(3));
graph.addEdge(graph.getNode(2), graph.getNode(3));

PathFinder pathFinder = new GraphShortestPath(graph);
List<Node> shortestPath = pathFinder.findPath(graph.getNode(1), graph.getNode(3));
```
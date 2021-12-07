# Ex2-OOP
Prof. Boaz Ben Moshe
<br>Alon Barak - 213487598
<br>Omer Adar - 325022952
<br>This repository represent an assignment in OOP course.
<br>Design and implementation of a directed and weighted graphs - Java
---
### 1. Main page
#### Literature review:
Projects we thought might be helpful for this assignment:

### 2. Table of contents
   1. Main page
   2. Table of contents
   3. How to run the project
   4. Explanation on our project classes
      <br>4.1 Geo_Location
      <br>4.2 Node_Data
      <br>4.3 Edge_Data
      <br>4.4 Directed_Weighted_Graph
      <br>4.5 Directed_Wheighet_Graph_Algorithm
   5. Explanation on our Algorithms
      <br>5.1 Is_Connected
      <br>5.2 Shortest_Path
      <br>5.3 Center
      <br>5.4 TSP
   6. GUI - Instrocturs
   7. How to run the Jar file
---
### 3. How to download and install the project (on IntelliJ workspace):
#### - Install JSON support file :  JSON-simple-1.1.Jar
   -
     - 
---
### 4. Explanation on our project classes
#### <br>4.1 Geo_Location:
This particular class helps us to represent a geo location <x,y,z>, (aka Point3D data).
#### <br>4.2 Node_Data:
This particular class helps us represent the set of operations applicable on a node (vertex) in a directed-weighted graph.
#### <br>4.3 Edge_Data:
This particular class helps us to represent the set of operations applicable on a directional edge(src,dest) in a directed-weighted graph.
#### <br> 4.4 Directed_Weighted_Graph:
This particular class represent a directed-weighted graph, the interface that this class relay on is containing in mind a road-system or a communication network that helps the class to support a graph with large number of nodes (over 100,00).
<br> Our compact representation for the implementation of the graph include two Hashmaps, one is for the vetrexes (nodes) of the graph and the second is for the edges of the graph.<br> 
The key of the first Hashmap is the id of the vetrexes and the value is the actual vetrex (node).<br>
The second one is a Hashmap of Hashmaps with two keys when the first of the keys is the source vetrex and the second key is the destination vetrex allongside the value which is the actual edge. 
#### <br>4.5 Directed_Wheighet_Graph_Algorithm
This particular class helps us to represent a directed (positive) Weighted Graph Theory Algorithms including the following functions:<br>
 - copy() - Returns an exact copy of the graph.
 - init() - Inits the graph on which a specific set of algorithms operates on.
 - isConnected() - Returns true if and only if there is a valid path from each node to each other node.
 - shortestPathDist() - Computes the shortest path between src vetrex to dest vetrex, as a double variable that represents the sum of distances by the wheights of each and every edge it pasts on from src to dest. 
 - shortestPath() - Computes the the shortest path between src to dest - as an ordered List of nodes, for example:<br> src ⮕  n1 ⮕ n2 ⮕ ...dest
 - center() - Finds the NodeData which minimizes the max distance to all the other nodes while assuming the graph isConnected, else returns null.
 - tsp() - Computes a list of consecutive nodes which will go over all the nodes in cities while the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution - the lower the better.
 - save() - Saves this directed-weighted graph to the given file name - in JSON format.
 - load() - This method loads a graph to this graph algorithm - 
     * If the file was successfully loaded ⮕ the underlying graph of this class will be changed (to the loaded one)
     * Else - in case the graph was not loaded ⮕ the original graph should remain "as is".<br>
 ---
### 5. Algorithm explanation

---
### 6. GUI - Instructurs

---
### 7. How to run the Jar file


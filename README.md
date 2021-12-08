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
This particular class helps us represent the set of operations applicable on a node (vertex) in a directed-weighted graph.<br>
Each data-node contains these methods:<br>
- geyKey() - Returns the key (id) associated with this node.
- getLocation() -  Returns the location of this node if its exists otherwise return null.
- setLocation() - Allows changing this node's location.
- getWeight() - Returns the weight associated with this node.
- setWeight() - Allows changing this node's weight.
- getInfo() - Returns the remark (meta data) associated with this node.
- setInfo() - Allows changing the remark (meta data) associated with this node.
- getTag() - Temporal data (aka color: e,g, white, gray, black) which can be used by algorithms.
- setTag() - Allows setting the "tag" value for temporal marking an node - common practice for marking by algorithms.
  
#### <br>4.3 Edge_Data:
This particular class helps us to represent the set of operations applicable on a directional edge(src,dest) in a directed-weighted graph.<br>
Each data-edge contains these methods:<br>
- getSrc() - Returns the id of the source node of this edge.
- getDest() - Returns the id of the destination node of this edge.
- getWeight() - Returns the weight of this edge (positive value).
- getInfo() - Returns the remark (meta data) associated with this edge.
- setInfo() - Allows changing the remark (meta data) associated with this edge.
- getTag() - Temporal data (aka color: e,g, white, gray, black) which can be used by algorithms.
- setTag() - Allows setting the "tag" value for temporal marking an edge - common practice for marking by algorithms.

#### <br> 4.4 Directed_Weighted_Graph:
This particular class represent a directed-weighted graph, the interface that this class relay on is containing in mind a road-system or a communication network that helps the class to support a graph with large number of nodes (over 100,000).
<br> Our compact representation for the implementation of the graph include two Hashmaps, one is for the vertexes (nodes) of the graph and the second is for the edges of the graph.<br> 
The key of the first Hashmap is the id of the vertexes and the value is the actual vertex (node).<br>
The second one is a Hashmap of Hashmaps with two keys when the first of the keys is the source vetrex and the second key is the destination vertex allongside the value which is the actual edge. 
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
### 5. Algorithms explanation
####  <br>5.1 Is_Connected 
 - **briefly explanation for the algorithm** - the goal of this particular algorithm is to check if there's a path between each node to all the nodes n the given graph, returns true if there is, otherwise returns false.<br>
 - **pseudo-code of the algorithm:**
```ruby
 1. visited[] ⬅ False Array
 2. V ⬅ random vertex from given graph G
 3. DFS(V , visited) ⬅ run DFS on the copy graph - G
 4. Loop on visited:
    4.1. if visited[i] == false:
      4.1.1.return false
 5. reset visited ⬅ false
 6. create a graph copy with reversed edges ⬅ G (transpose) 
 7. DFS(V , visited) ⬅ run DFS on the copy graph - G (transpose)
 8. Loop on visited:
   8.1 if visited[i] == false
     8.1.1. return false
 9. return true
``` 
#### <br>5.2 Shortest_Path()
 -  **briefly explanation for the algorithm** - the goal of this particular algorithm is to find the shortest path between src node to Dest node.<br>
 -  **pseudo-code of the algorithm:**
   - Shortest_Path(src , Dest):
```ruby
1. nodeWp ⬅ new HashMap
2. Q ⬅ priority Queue
3. path ⬅ new HashSet
4. nodeWp.add(src)
5. Q.add(src)
6. While(!Q.isEmpty){
  6.1. node ⬅ Q.poll
  6.2. path.add(node)
  6.3. if node == Dest
    6.3.1. return reversed(path)
  6.4. neighbor ⬅ node.adj()
  6.5. for neighbor in neighbors:
    6.5.1. if Path contain neighbor
      6.5.1.1. continue 
    6.5.2. dist ⬅ W(node.neighbor)
    6.5.3. totalDist ⬅ node.totalDist + dist
    6.5.4. if neighbor !dicoverd:
      6.5.4.1. nodeWp.add(neighbor)
      6.5.4.2. Q.add(neighbor)
    6.5.5. else if (totalDist < node.totalDist)
      6.5.5.1. node.totalDist ⬅ totalDist 
      6.5.5.2. Q.remove(node)
      6.5.5.3. Q.add(node)
   }
7. return null
```
####  <br>5.3 Center()
- **briefly explanation for the algorithm** - the goal of this particular algorithm is to find the NodeData which minimizes the max distance to every other nodes.<br>
- **pseudo-code of the algorithm:**
  - Center():
```ruby
1. if G is !connected:
  1.1. return null
2. min_max_path ⬅ Infinity
3. ChosenNode ⬅ -1
4. Iterate over the Graph nodes:
  4.1. max_Sp ⬅ Dijakstra(node)
  4.2. if max_Sp < min_max_Sp:
    4.2.1. min_max_Sp ⬅ max_Sp
    4.2.2. ChosenNode ⬅ node
5. return ChosenNode
```
####       <br>5.4 TSP()
- **briefly explanation for the algorithm** - the goal of this particular algorithm is to Save the given directed-weighted graph to the given file name - in JSON format.
- **pseudo-code of the algorithm:**
   - TSP(cities)
```ruby
1. SetUnvisited(cities)
2. V ⬅ cities[0]
3. List.add(v)
4. V.tag ⬅ 1
5. While(!allVisited(cities){
  5.1. U ⬅ MinShortestPath(V)
  5.2. path ⬅ ShortestPath(V,U)
  5.3. for i < path.Size:
    5.3.1. if cities contain path[i]
      5.3.1.1. path[i].tag ⬅ 1
    5.3.2. List.add(path[i])
  5.4. V ⬅ U 
 }
6. return List
```

---
### 6. GUI - Instructurs

---
### 7. How to run the Jar file




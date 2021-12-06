package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Directed_Weighted_Graph_Algorithms implements DirectedWeightedGraphAlgorithms{

    private DirectedWeightedGraph graph;
    public static List<NodeData> list;
    public static List<NodeData> city;


    public Directed_Weighted_Graph_Algorithms(){
        this.graph = null;
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = new Directed_Weighted_Graph(g);
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return new Directed_Weighted_Graph(this.graph);
    }

    @Override
    public boolean isConnected() {
        // a boolean array to see whether a vertex is visited or not
        boolean[] visited = new boolean[this.graph.nodeSize()];

        NodeData v = this.graph.nodeIter().next();     // random vertex

        // run DFS starting at v
        this.DFS(this.graph, v, visited);

        // if DFS traversal doesn't visit all vertices
        // then the graph is not connected, return false
        for(boolean b: visited){
            if(!b)
                return false;
        }

        Arrays.fill(visited, false);        // reset the array for the second DFS

        Directed_Weighted_Graph copy = new Directed_Weighted_Graph();

        Iterator<NodeData> itrn = this.graph.nodeIter();

        while(itrn.hasNext()){      // copy the nodes
            copy.addNode(itrn.next());
        }

        Iterator<EdgeData> itre = this.graph.edgeIter();
        while(itre.hasNext()){      // copy the edges and reverse them
            EdgeData e = itre.next();
            copy.connect(e.getDest(), e.getSrc(),e.getWeight());  // reversed edge
        }

        // run DFS on the transformed graph
        this.DFS(copy,v,visited);

        // if DFS traversal doesn't visit all vertices
        // then the graph is not connected, return false
        for(boolean b: visited){
            if(!b)
                return false;
        }

        return true;
    }

    private void DFS(DirectedWeightedGraph graph, NodeData v, boolean[] visited) {
        // visited the vertex v
        visited[v.getKey() % visited.length] = true;
        // iterator on the adj of v
        Iterator<EdgeData> itr = graph.edgeIter(v.getKey());

        // do so for every edge who adj to v
        while(itr.hasNext()) {
            int u = itr.next().getDest();
            if (!visited[u % visited.length]) {
                DFS(graph, graph.getNode(u), visited);
            }
        }
    }

    @Override
    public double shortestPathDist(int src, int dest) {

        double distance = 0;
        List<NodeData> nodesList = this.shortestPath(src,dest);
        Iterator<NodeData> itrSrc = nodesList.listIterator();
        Iterator<NodeData> itrDest = nodesList.listIterator();
        itrDest.next();
        while(itrDest.hasNext()){

           NodeData srcNode = itrSrc.next();
           NodeData destNode = itrDest.next();
           EdgeData edge = this.graph.getEdge(srcNode.getKey(),destNode.getKey());
           distance += edge.getWeight();
        }
        return distance;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return this.shortestPath2(this.graph.getNode(src),this.graph.getNode(dest));
    }

    private List<NodeData> shortestPath2(NodeData src, NodeData dest){

        Map<NodeData, NodeWrap<NodeData>> nodeWrappers = new HashMap<>();
        PriorityQueue<NodeWrap<NodeData>> queue = new PriorityQueue<>();
        Set<NodeData> shortestPathFound = new HashSet<>();

        // Add src Node to queue
        NodeWrap<NodeData> sourceWrapper = new NodeWrap<>(src,0.0,null);
        nodeWrappers.put(src, sourceWrapper);
        queue.add(sourceWrapper);

        while(!queue.isEmpty()){
            NodeWrap<NodeData> nodeWrapper = queue.poll();
            NodeData node = nodeWrapper.getNode();
            shortestPathFound.add(node);

            // if we reached to destination --> Build and return the path
            if (node.equals(dest)){
                return buildPath(nodeWrapper);
            }

            // Iterate over all neighbors
            Set<NodeData> neighbors = new HashSet<>();
            Iterator<EdgeData> itr = this.graph.edgeIter(node.getKey());
            while(itr.hasNext()){
                NodeData neighbor = this.graph.getNode(itr.next().getDest());
                neighbors.add(neighbor);
            }
            for (NodeData neighber : neighbors){
                // Ignore neighbor if shortest path already found
                if (shortestPathFound.contains(neighber))
                    continue;

                double distance = this.graph.getEdge(node.getKey(),neighber.getKey()).getWeight();
                double totalDistance = nodeWrapper.getTotalDistance() + distance;
                // if the neighbor not discovered yet
                NodeWrap<NodeData> neighborWrapper = nodeWrappers.get(neighber);
                if (neighborWrapper == null){
                    neighborWrapper = new NodeWrap<>(neighber,totalDistance,nodeWrapper);
                    nodeWrappers.put(neighber,neighborWrapper);
                    queue.add(neighborWrapper);
                }
                // neighbor discovered, but total distance via current node i shorter?
                // --> update total distance and predecessor
                else if (totalDistance < neighborWrapper.getTotalDistance()){
                    neighborWrapper.setTotalDistance(totalDistance);
                    neighborWrapper.setPredecessor(nodeWrapper);

                    queue.remove(neighborWrapper);
                    queue.add(neighborWrapper);
                }

            }

        }

        // destination was not found -> return null
        return null;
    }

    private List<NodeData> buildPath(NodeWrap<NodeData> nodeWrapper){
        List<NodeData> path = new ArrayList<>();
        while(nodeWrapper != null){
            path.add(nodeWrapper.getNode());
            //nodeWrapper.getNode().setTag(1);        // visited
            nodeWrapper = nodeWrapper.getPredecessor();
        }
        Collections.reverse(path);
        return path;
    }

    @Override
    public NodeData center() {
        // if the graph in not strongly connected -> return null
        if (!this.isConnected())
            return null;
        // else
        double min_max_sp = Integer.MAX_VALUE;
        int chosenNode = -1;
        Iterator<NodeData> itr = this.graph.nodeIter();
        // iterate over the graph nodes
        while(itr.hasNext()){
            int node = itr.next().getKey();
            // find the maximum shortest path for each node
            double max_sp = maxShortestPath(node);
            if(max_sp < min_max_sp){
                min_max_sp = max_sp;
                chosenNode = node;
            }
        }
        // return the node with the minimized maximum shortest path
        return this.graph.getNode(chosenNode);
    }

    private double maxShortestPath(int src){
        double maxSP = 0;
        Iterator<NodeData> itr = this.graph.nodeIter();
        while(itr.hasNext()){
            NodeData n = itr.next();
            if (n.getKey() != src) {
                double sp = this.shortestPathDist(src, n.getKey());
                if (sp > maxSP)
                    maxSP = sp;
            }
        }
        return maxSP;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {      // TO DO - SALESMAN PROBLEM

        /**
         * find the closest node -> find the shortest path for each unvisited node.
         * go to the closest node and add to the res list.
         * return to the first line until all the cities nodes are visited.
         * add the first node again to the list to close a circle.
         * return the list.
         */

        // initialize city and list
        city = cities;
        list = new LinkedList<>();

        setUnvisited(city);

        NodeData v = city.get(0);       // start node
        list.add(v);
        while(!allVisit(city)){

            NodeData u = minShortestPath(v.getKey());       // return the closest unvisited node
            // mark visited nodes
            List<NodeData> path = shortestPath2(v,u);
            for (int i = 1; i < path.size();i++){
                if (city.contains(path.get(i))){
                    path.get(i).setTag(1);  // visited
                }
                list.add(path.get(i));      // add visited nodes to the list
            }

            v = u;
        }
        return list;
    }

    private NodeData minShortestPath(int key) {

        double min = Integer.MAX_VALUE;
        int nodeKey = key;
        for (int i = 0; i < city.size(); i++){
            if(city.get(i).getTag() == 0 && city.get(i).getKey() != key){
                double path = shortestPathDist(key,city.get(i).getKey());
                if (path < min){
                    min = path;
                    nodeKey = city.get(i).getKey();
                }
            }
        }
        NodeData u = this.graph.getNode(nodeKey);
        return u;
    }

    private void setUnvisited(List<NodeData> city) {
        int i = 0;
        while(i < city.size()){
            city.get(i).setTag(0);
            i++;
        }
    }

    private boolean allVisit(List<NodeData> cities) {

        for(int i = 0; i < cities.size();i++){
            if (cities.get(i).getTag() != 1){
                return false;
            }
        }
        return true;

    }


    @Override @SuppressWarnings("unchecked")
    public boolean save(String file) {

        Iterator<NodeData> itrNode = this.graph.nodeIter();
        Iterator<EdgeData> itrEdge =  this.graph.edgeIter();
        JSONObject obj = new JSONObject();
        JSONArray edges = new JSONArray();
        while(itrEdge.hasNext()){
            EdgeData e = itrEdge.next();
            JSONObject edge = new JSONObject();
            edge.put("dest", e.getDest());
            edge.put("w", e.getWeight());
            edge.put("src", e.getSrc());
            edges.add(edge);
        }

        JSONArray nodes = new JSONArray();
        while(itrNode.hasNext()){
            NodeData n = itrNode.next();
            JSONObject node = new JSONObject();
            node.put("id" , n.getKey());
            node.put("pos" , n.getLocation().toString());
            nodes.add(node);

        }
        FileWriter file2 = null;
        try{
            file2 = new FileWriter(file);
            obj.put("Nodes", nodes);
            obj.put("Edges", edges);
            file2.write(obj.toString());
        }catch(IOException e){
            return false;
        } finally{
            if (file2 != null) {
                try {
                    file2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean load(String file) {


        Directed_Weighted_Graph grp = new Directed_Weighted_Graph(file);
        if (this.graph == null){
            this.graph = new Directed_Weighted_Graph();
            this.init(grp);
            return true;
        }
        else if (grp.nodeSize() != 0 || grp.edgeSize() != 0) {
            this.init(grp);
            return true;
        }
        else
            return false;
    }
}
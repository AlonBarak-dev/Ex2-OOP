package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Directed_Weighted_Graph implements DirectedWeightedGraph{

    private int modeCounter;
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer,EdgeData>> edges;

    public Directed_Weighted_Graph(){
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        this.modeCounter = 0;
    }


    public Directed_Weighted_Graph(DirectedWeightedGraph g){
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        Iterator<NodeData> itr = g.nodeIter();

        while(itr != null && itr.hasNext()){
            this.addNode(itr.next());
        }

        for (int i = 0; i<g.nodeSize();i++){
            Iterator<EdgeData> tmp = g.edgeIter(i);
            while(tmp!= null && tmp.hasNext()){
                EdgeData e = tmp.next();
                this.connect(e.getSrc(),e.getDest(),e.getWeight());
            }
        }
        this.modeCounter = g.getMC();
    }


    @SuppressWarnings("unchecked")
    public Directed_Weighted_Graph(String fileName){
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        this.modeCounter = 0;

        JSONParser jsonParser = new JSONParser();
        File f = new File(fileName);      // should receive file path as input -> fileName
        try(FileReader reader = new FileReader(f)){

            Object obj =  jsonParser.parse(reader);
            JSONObject NodeObject = (JSONObject) obj;

            JSONArray NodeList = (JSONArray) NodeObject.get("Nodes");
            JSONArray EdgeList = (JSONArray) NodeObject.get("Edges");

            NodeList.forEach(node -> ppNode((JSONObject) node));
            EdgeList.forEach(edge -> ppEdge((JSONObject) edge));

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private void ppEdge(JSONObject node) {
        if(node != null) {
            int src = ((Long) node.get("src")).intValue();
            int dest = ((Long) node.get("dest")).intValue();
            double w = (double) node.get("w");
            this.connect(src, dest, w);
        }
    }


    private void ppNode(JSONObject edge) {
        if (edge != null) {
            int id = ((Long) edge.get("id")).intValue();
            String pos = (String) edge.get("pos");
            Geo_Location g = new Geo_Location(pos);
            Node_Data n = new Node_Data(g, id);
            this.addNode(n);
        }
    }

    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return this.edges.getOrDefault(src, new HashMap<>()).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        if(!this.nodes.containsKey(n.getKey())){
            this.nodes.put(n.getKey(),n);
            this.edges.put(n.getKey(), new HashMap<>());
            this.modeCounter++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {

        if (this.edges.containsKey(src)){
            Edge_Data edge = (Edge_Data) this.edges.get(src).get(dest);
            if (edge != null && edge.getWeight() == w){
                return;
            }
            if (!this.edges.get(src).containsKey(dest)){
                EdgeData newEdge = new Edge_Data(src,dest,w);
                this.edges.get(src).put(dest, newEdge);
                this.modeCounter++;
            }
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return this.nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        HashMap<Integer,EdgeData> tmp = new HashMap<>();
        int key = 0;
        for (int i = 0; i < this.nodeSize();i++){
            Iterator<EdgeData> e = edgeIter(i);
            while(e != null && e.hasNext()){
                tmp.put(key,e.next());
                key++;
            }
        }
        return tmp.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (this.edges.get(node_id) != null) {
            return this.edges.get(node_id).values().iterator();
        }
        return null;
    }

    @Override
    public NodeData removeNode(int key) {

        NodeData rmNode = this.getNode(key);

        if (rmNode == null)
            return null;

        for(Integer dest : new HashSet<>(this.edges.get(key).keySet())){
            this.removeEdge(key,dest);          // removing the edges coming out of the node
        }

        for(Map.Entry<Integer, HashMap<Integer, EdgeData>> src : this.edges.entrySet()){
            this.removeEdge(src.getKey(), key);     // removing all the edges coming in this node
        }

        this.modeCounter++;
        this.edges.remove(key);
        this.nodes.remove(key);
        return rmNode;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {

        EdgeData rmEdge = null;

        if(this.edges.containsKey(src)){
            rmEdge = this.edges.get(src).remove(dest);
        }

        if (rmEdge != null){
            this.modeCounter++;
        }

        return rmEdge;
    }

    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.edges.size();
    }

    @Override
    public int getMC() {
        return this.modeCounter;
    }
}

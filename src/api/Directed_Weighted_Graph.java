package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Directed_Weighted_Graph implements DirectedWeightedGraph{


    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer,EdgeData>> edges;
    @SuppressWarnings("unchecked")
    public Directed_Weighted_Graph(String fileName){
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        JSONParser jsonParser = new JSONParser();

        File f = new File("data/G1.json");
        try(FileReader reader = new FileReader(f)){

            Object obj =  jsonParser.parse(reader);
            JSONObject NodeObject = (JSONObject) obj;
            JSONArray NodeList = (JSONArray) NodeObject.get("Nodes");
            JSONArray EdgeList = (JSONArray) NodeObject.get("Edges");
            NodeList.forEach(node -> ppNode((JSONObject) node));
            EdgeList.forEach(node -> ppEdge((JSONObject) node));

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private void ppEdge(JSONObject node) {
        int src = ((Long) node.get("src")).intValue();
        int dest = ((Long) node.get("dest")).intValue();
        double w = (double)node.get("w");
        Edge_Data e = new Edge_Data(src,dest,w);
        //this.edges.put(src, src, e);
        System.out.println(e);
    }


    private void ppNode(JSONObject node) {
        int id = ((Long) node.get("id")).intValue();
        String pos = (String) node.get("pos");
        Geo_Location g = new Geo_Location(pos);
        Node_Data n = new Node_Data(g,id);
        this.nodes.put(id,n);
    }

    @Override
    public NodeData getNode(int key) {
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(NodeData n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }
}

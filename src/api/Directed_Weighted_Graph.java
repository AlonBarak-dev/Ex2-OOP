package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

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

    private void ppEdge(JSONObject edge) {
        if(edge != null) {
            int src = ((Long) edge.get("src")).intValue();
            int dest = ((Long) edge.get("dest")).intValue();
            double w = (double) edge.get("w");
            this.connect(src, dest, w);
        }
    }


    private void ppNode(JSONObject node) {
        if (node != null) {
            int id = ((Long) node.get("id")).intValue();
            String pos = (String) node.get("pos");
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
            else if (this.edges.get(src).containsKey(dest)) {
                EdgeData newEdge = new Edge_Data(src, dest, w);
                this.edges.get(src).remove(dest);
                this.edges.get(src).put(dest, newEdge);
                this.modeCounter++;
            }
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return new Iterator<NodeData>() {
            Iterator<NodeData> itr = nodes.values().iterator();
            int mode = modeCounter;

            @Override
            public void remove(){
                checkMC();
                NodeData n = itr.next();
                removeNode(n.getKey());
                mode++;
                itr.remove();
            }

            @Override
            public void forEachRemaining(Consumer<? super NodeData> action){
                checkMC();
                itr.forEachRemaining(action);
            }

            @Override
            public boolean hasNext() {
                checkMC();
                return itr.hasNext();
            }

            @Override
            public NodeData next() {
                checkMC();
                return itr.next();
            }

            private void checkMC() throws NoSuchElementException{
                if(mode != modeCounter)
                    throw new NoSuchElementException();
            }
        };
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return new Iterator<EdgeData>() {
            Iterator<HashMap<Integer, EdgeData>> first = edges.values().iterator();
            Iterator<EdgeData> second = first.next().values().iterator();
            int mode = modeCounter;

            @Override
            public void remove() {
                checkMC();
                EdgeData e = second.next();
                removeEdge(e.getSrc(),e.getDest());
                mode++;
                second.remove();
            }

            @Override
            public boolean hasNext() {
                checkMC();
                if (!second.hasNext()) {
                    if (first.hasNext()) {
                        second = first.next().values().iterator();
                        return (second.hasNext());
                    }
                    return false;
                }
                return true;
            }

            @Override
            public EdgeData next() throws NoSuchElementException {
                checkMC();
                if (!second.hasNext()) {
                    if (first.hasNext()) {
                        second = first.next().values().iterator();
                        return second.next();
                    }
                    throw new NoSuchElementException();
                }
                return second.next();
            }
            @Override
            public void forEachRemaining(Consumer<? super EdgeData> action){
                checkMC();
                while(first.hasNext()){
                    second.forEachRemaining(action);
                    second = first.next().values().iterator();
                }
            }

            private void checkMC() throws NoSuchElementException{
                if(mode != modeCounter)
                    throw new NoSuchElementException();
            }
        };
    }


    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return new Iterator<EdgeData>() {
            int mode = modeCounter;
            Iterator<EdgeData> itr = edges.get(node_id).values().iterator();

            @Override
            public void remove(){
                checkMC();
                EdgeData e = itr.next();
                removeEdge(e.getSrc(),e.getDest());
                mode++;
                itr.remove();

            }

            @Override
            public boolean hasNext() {
                checkMC();
                return itr.hasNext();
            }

            @Override
            public EdgeData next() {
               checkMC();
               return itr.next();
            }

            @Override
            public void forEachRemaining(Consumer<? super EdgeData> action) throws NullPointerException{
                checkMC();
                Iterator.super.forEachRemaining(action);
            }

            private void checkMC() throws NoSuchElementException{
                if(mode != modeCounter)
                    throw new NoSuchElementException();
            }
        };
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
        int sum = 0;
        Iterator<EdgeData> itr = this.edgeIter();
        while(itr.hasNext()){
            sum++;
            itr.next();
        }
        return sum;
    }

    @Override
    public int getMC() {
        return this.modeCounter;
    }
}

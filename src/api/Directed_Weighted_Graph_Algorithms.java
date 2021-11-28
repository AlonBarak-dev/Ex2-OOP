package api;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Directed_Weighted_Graph_Algorithms implements DirectedWeightedGraphAlgorithms{

    private DirectedWeightedGraph graph;

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

        NodeData v = this.graph.getNode(0);     // random vertex

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
            copy.connect(itre.next().getDest(), itre.next().getSrc(),itre.next().getWeight());  // reversed edge
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
        visited[v.getKey()] = true;
        // iterator on the adj of v
        Iterator<EdgeData> itr = graph.edgeIter(v.getKey());

        // do so for every edge who adj to v
        while(itr.hasNext()) {
            int u = itr.next().getDest();
            if (!visited[u]) {
                DFS(graph, graph.getNode(u), visited);
            }
        }
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}

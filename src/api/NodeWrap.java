package api;

public class NodeWrap<NodeData> implements Comparable<NodeWrap>{

    private final NodeData node;
    private double totalDistance;
    private NodeWrap<NodeData> predecessor;

    public NodeWrap(NodeData n, double totalDistance, NodeWrap<NodeData> predecessor){
        this.node = n;
        this.totalDistance = totalDistance;
        this.predecessor = predecessor;
    }

    public NodeData getNode(){
        return this.node;
    }

    public void setTotalDistance(double totalDistance){
        this.totalDistance = totalDistance;
    }

    public double getTotalDistance(){
        return this.totalDistance;
    }

    public void setPredecessor(NodeWrap<NodeData> predecessor){
        this.predecessor = predecessor;
    }

    public NodeWrap<NodeData> getPredecessor(){
        return this.predecessor;
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(NodeWrap other) {
        return Double.compare(this.totalDistance, other.totalDistance);
    }
}

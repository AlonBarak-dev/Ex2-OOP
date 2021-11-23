package api;

public class Edge_Data implements EdgeData{

    private int src;
    private int dest;
    private double weight;
    private String info;
    private int tag;

    public Edge_Data(int src, int dest, double weight, String info, int tag){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.info = info;
        this.tag = tag;
    }

    public Edge_Data(int src, int dest, double w){
        this.src = src;
        this.dest = dest;
        this.weight = w;
        this.info = "";         //default
        this.tag = 0;       //default
    }


    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString(){
        return "SRC: " + this.src + " DEST: " + this.dest + " WEIGHT: " + this.weight;
    }
}

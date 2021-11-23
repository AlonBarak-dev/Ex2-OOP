package api;


public class Node_Data implements NodeData{

    private GeoLocation position;
    private int id;
    private double weight;
    private String info;
    private int tag;

    public Node_Data(GeoLocation pos, int id, double weight){
        this.id = id;
        this.position = new Geo_Location(pos);
        this.weight = weight;
        this.info = "";      //default
        this.tag = 0;       //default
    }

    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.position;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.position = new Geo_Location(p);
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
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
        this.tag = tag;
    }
}

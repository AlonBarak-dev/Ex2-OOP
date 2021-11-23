package api;

/**
 * this class is build from the "pos" field in the Node data in the
 * json file.
 */
public class Geo_Location implements GeoLocation {

    private double x;
    private double y;
    private double z;

    public Geo_Location(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Geo_Location(String pos){
        String[] indexes = pos.split(",");
        this.x = Double.parseDouble(indexes[0]);
        this.y = Double.parseDouble(indexes[1]);
        this.z = Double.parseDouble(indexes[2]);
    }

    public Geo_Location(GeoLocation g){
        this(g.x(),g.y(),g.y());
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(api.GeoLocation g) {
        double distx = Math.pow(this.x - g.x(), 2);
        double disty = Math.pow(this.y - g.y(), 2);

        double dist = Math.sqrt(distx - disty);
        return dist;
    }
}

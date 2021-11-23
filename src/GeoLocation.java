

public class GeoLocation implements api.GeoLocation {

    private double x;
    private double y;
    private double z;

    public GeoLocation(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
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

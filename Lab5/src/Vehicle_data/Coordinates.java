package Vehicle_data;

public class Coordinates {

    private double x;
    private long y;

    public double getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public Coordinates(double x, long y) {
        this.x = x;
        this.y = y;
    }

}

package assignments.ex3;

import java.io.Serializable;

public class Index2D implements Pixel2D, Serializable{
    public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;

    private int _x, _y;
    public Index2D() {this(0,0);}
    public Index2D(int x, int y) {_x=x;_y=y;}
    public Index2D(Pixel2D t) {this(t.getX(), t.getY());}

    public Index2D(String pos) {
        // add you code here
        try { //try to take a string "x,y" and splits it into two parts
            String[] a = pos.split(",");
            if (a.length != 2) {
                throw new IllegalArgumentException("String must be: x,y");
            }
            _x = Integer.parseInt(a[0]);
            _y = Integer.parseInt(a[1]);
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException("String must contain valid integers: " + pos, e);
        }
        ////////////////////
    }

    @Override
    public int getX() {
        return _x;
    }

    @Override
    public int getY() {
        return _y;
    }
    public double distance2D(Pixel2D t) {
        double ans = -1;
        // add you code here
        if (t==null)
        {throw new IllegalArgumentException("Cannot compute distance to null Pixel2D");}
        ans = Math.sqrt(Math.pow(((double)(this.getX()-t.getX())),2.0)
                +Math.pow(((double)(this.getY()-t.getY())),2.0)); //distance2D = sqrt((x2 - x1)^2 + (y2 - y1)^2)
        ////////////////////
        return ans;
    }
    @Override
    public String toString() {
        String ans = "";
        // add you code here
        ans = _x+","+_y;
        ////////////////////
        return ans;
    }
    @Override
    public boolean equals(Object t) {
        boolean ans = false;
        // add you code here
        if(t == null || !(t instanceof Pixel2D)) {return false;} //correctness check
        Pixel2D t1 = (Pixel2D) t;
        if ((_x == t1.getX()) && (_y == t1.getY())) {ans = true;}
        ////////////////////
        return ans;
    }
}

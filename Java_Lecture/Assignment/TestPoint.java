class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getXY() { return new int[]{x, y}; }
    public void setXY(int x, int y) { this.x = x; this.y = y; }

    public double distance(int x, int y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    public double distance(Point another) {
        return distance(another.x, another.y);
    }

    public double distance() { return distance(0, 0); }

    @Override
    public String toString() { return "(" + x + "," + y + ")"; }
}

class TestPoint {
    public static void main(String[] args) {
        Point p1 = new Point(3, 4);
        System.out.println("p1 is " + p1);  
        
        Point p2 = new Point(5, 6);
        System.out.println("p2 is " + p2);
        System.out.println("p1 coordinates: x=" + p1.getXY()[0] + ", y=" + p1.getXY()[1]);
        p1.setXY(9, 8);
        System.out.println("After setXY(9,8), p1 is " + p1);
        System.out.println("Distance from p1 to origin: " + p1.distance());
        System.out.println("Distance from p1 to p2: " + p1.distance(p2));
        System.out.println("Distance from p1 to point(3,4): " + p1.distance(3, 4));
    }
}

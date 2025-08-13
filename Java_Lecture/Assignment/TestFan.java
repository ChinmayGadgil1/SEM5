class Fan {
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;

    private int speed;
    private boolean on;
    private double radius;
    private String color;

    public Fan() {
        speed = SLOW;
        on = false;
        radius = 5;
        color = "blue";
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public boolean isOn() { return on; }
    public void setOn(boolean on) { this.on = on; }
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    @Override
    public String toString() {
        if (on) {
            return "Fan speed: " + speed + ", Color: " + color + ", Radius: " + radius;
        }
        else {
            return "Fan is off";
        }
    }
}

class TestFan {
    public static void main(String[] args) {
        Fan fan1 = new Fan();
        fan1.setOn(true);
        fan1.setSpeed(Fan.SLOW);
        fan1.setColor("blue");
        fan1.setRadius(8);

        Fan fan2 = new Fan();
        fan2.setOn(false);
        fan2.setSpeed(Fan.FAST);
        fan2.setColor("yellow");
        fan2.setRadius(10);

        System.out.println(fan1);
        System.out.println(fan2);
    }
}

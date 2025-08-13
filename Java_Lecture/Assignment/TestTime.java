class Time {
    private int hour;
    private int minute;
    private int second;
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void setTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Time nextSecond() {
        second++;
        if (second == 60) {
            second = 0;
            minute++;
        }
        if (minute == 60) {
            minute = 0;
            hour++;
        }
        if (hour == 24) {
            hour = 0;
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}

class TestTime {
    public static void main(String[] args) {
        Time t1 = new Time(23, 59, 59);
        System.out.println("Time 1: " + t1);
        System.out.println("After one second: " + t1.nextSecond());
        
        Time t2 = new Time(12, 30, 45);
        System.out.println("\nTime 2: " + t2);
        t2.setTime(14, 45, 30);
        System.out.println("After setting new time: " + t2);
        System.out.println("Multiple seconds later: " + t2.nextSecond().nextSecond().nextSecond());
    }
}
 

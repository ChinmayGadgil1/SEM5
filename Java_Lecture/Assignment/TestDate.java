class MyDate {
    private int day;
    private int month;
    private int year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}

class TestDate {
    public static void main(String[] args) {
        MyDate date1 = new MyDate(5, 8, 2025);
        System.out.println("First Date: " + date1);
        
        MyDate date2 = new MyDate(1, 1, 2024);
        System.out.println("Second Date: " + date2);
        
        System.out.println("Date1 - Day: " + date1.getDay() + ", Month: " + date1.getMonth() + ", Year: " + date1.getYear());
        
        date1.setDate(15, 12, 2023);
        System.out.println("Updated First Date: " + date1);
    }
}

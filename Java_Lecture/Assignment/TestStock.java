class Stock {
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    public Stock(String symbol, String name, double previousClosingPrice, double currentPrice) {
        this.symbol = symbol;
        this.name = name;
        this.previousClosingPrice = previousClosingPrice;
        this.currentPrice = currentPrice;
    }

    public double changePercent() {
        return ((currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }

    public double getPreviousClosingPrice() { return previousClosingPrice; }

    @Override
    public String toString() {
        return symbol + " (" + name + ") Previous: " + previousClosingPrice +
               ", Current: " + currentPrice + ", Change: " + changePercent() + "%";
    }
}

class TestStock {
    public static void main(String[] args) {
        Stock[] stocks = {
            new Stock("TM", "Toyota", 145.50, 150.75),
            new Stock("SONY", "Sony", 82.25, 85.50),
            new Stock("SAMSUNG", "Samsung", 298.75, 312.25),
            new Stock("TATA", "Tata Motors", 398.50, 445.25),
            new Stock("HONDA", "Honda", 98.75, 95.50),
            new Stock("NISSAN", "Nissan", 32.25, 31.75),
            new Stock("HYUNDAI", "Hyundai", 208.50, 198.25),
            new Stock("SUZUKI", "Suzuki", 87.75, 92.50),
            new Stock("YAMAHA", "Yamaha", 139.25, 143.75),
            new Stock("SUBARU", "Subaru", 49.75, 52.25)
        };

        Stock highest = stocks[0];
        Stock lowest = stocks[0];
        for (Stock s : stocks) {
            if (s.getPreviousClosingPrice() > highest.getPreviousClosingPrice())
                highest = s;
            if (s.getPreviousClosingPrice() < lowest.getPreviousClosingPrice())
                lowest = s;
        }

        System.out.println("Highest Closing Price: " + highest);
        System.out.println("Lowest Closing Price: " + lowest);
    }
}

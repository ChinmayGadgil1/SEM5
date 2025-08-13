import java.util.Date;

class Account {
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;

    public Account() {
        this.id = 0;
        this.balance = 0;
        this.annualInterestRate = 0;
        this.dateCreated = new Date();
    }

    public Account(int id, double balance, double annualInterestRate) {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.dateCreated = new Date();
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    public void withdraw(double amount) {
        if (balance >= amount)
            balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return "Account ID: " + id + ", Balance: " + balance + ", Created: " + dateCreated;
    }
}

class TestAccount {
    public static void main(String[] args) {
        Account[] accounts = {
            new Account(1, 10000, 7.5),
            new Account(2, 5780, 3.5),
            new Account(3, 9600, 3.3)
        };

        Account highest = accounts[0];
        for (Account acc : accounts) {
            if (acc.getBalance() > highest.getBalance()) {
                highest = acc;
            }
        }

        System.out.println("Account having highest balance: " + highest);
    }
}

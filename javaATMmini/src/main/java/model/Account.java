package model;

import java.util.ArrayList;
import java.util.List;

// Lớp Account biểu diễn dữ liệu tài khoản
public class Account {
    private String accountNumber;    // Số tài khoản
    private String accountHolder;   // Tên chủ tài khoản
    private double balance;         // Số dư tài khoản
    private List<String> history;   // Lịch sử giao dịch

    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.history = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        history.add("Gửi tiền: +" + amount + ", Số dư: " + balance);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            history.add("Rút tiền: -" + amount + ", Số dư: " + balance);
            return true;
        } else {
            history.add("Rút tiền thất bại: Số dư không đủ");
            return false;
        }
    }

    public boolean transfer(Account toAccount, double amount) {
        if (amount <= balance) {
            balance -= amount;
            toAccount.deposit(amount);
            history.add("Chuyển tiền: -" + amount + " đến " + toAccount.getAccountNumber() + ", Số dư: " + balance);
            return true;
        } else {
            history.add("Chuyển tiền thất bại: Số dư không đủ");
            return false;
        }
    }

    public List<String> getHistory() {
        return history;
    }
}
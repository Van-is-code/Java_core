package controller;

import model.Account;
import model.AccountService;

public class ATMController {

    private final AccountService accountService;

    public ATMController() {
        this.accountService = new AccountService();
    }

    // Phương thức rút tiền
    public String withdraw(String accountNumber, double amount) {
        Account account = accountService.getAccount(accountNumber);
        if (account == null) {
            return "Tài khoản không tồn tại.";
        }
        if (account.withdraw(amount)) {
            return "Rút tiền thành công. Số dư hiện tại: " + account.getBalance();
        }
        return "Rút tiền thất bại. Số dư không đủ.";
    }

    // Phương thức gửi tiền
    public String deposit(String accountNumber, double amount) {
        Account account = accountService.getAccount(accountNumber);
        if (account == null) {
            return "Tài khoản không tồn tại.";
        }
        account.deposit(amount);
        return "Gửi tiền thành công. Số dư hiện tại: " + account.getBalance();
    }

    // Phương thức chuyển tiền
    public String transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accountService.getAccount(fromAccountNumber);
        if (fromAccount == null) {
            return "Tài khoản nguồn không tồn tại.";
        }
        Account toAccount = accountService.getAccount(toAccountNumber);
        if (toAccount == null) {
            return "Tài khoản đích không tồn tại.";
        }
        if (fromAccount.transfer(toAccount, amount)) {
            return "Chuyển khoản thành công. Số dư tài khoản nguồn: " + fromAccount.getBalance();
        }
        return "Chuyển khoản thất bại. Số dư không đủ.";
    }

    // Kiểm tra số dư tài khoản
    public String getBalance(String accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        if (account == null) {
            return "Tài khoản không tồn tại.";
        }
        return "Số dư hiện tại của tài khoản: " + account.getBalance();
    }

    // Lấy lịch sử giao dịch
    public String getTransactionHistory(String accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        if (account == null) {
            return "Tài khoản không tồn tại.";
        }
        StringBuilder history = new StringBuilder();
        history.append("=== Lịch sử giao dịch ===\n");
        for (String transaction : account.getHistory()) {
            history.append(transaction).append("\n");
        }
        if (account.getHistory().isEmpty()) {
            history.append("Không có giao dịch nào.");
        }
        return history.toString();
    }
}
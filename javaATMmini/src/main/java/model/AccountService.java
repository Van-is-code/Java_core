package model;

import model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private Map<String, Account> accounts = new HashMap<>();

    // Khởi tạo dữ liệu tài khoản mẫu
    public AccountService() {
        accounts.put("11111", new Account("11111", "Nguyen Van A", 5000.0));
        accounts.put("222", new Account("222", "Tran Thi B", 2000.0));
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean accountExists(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public Map<String, Account> getAllAccounts() {
        return accounts;
    }
}
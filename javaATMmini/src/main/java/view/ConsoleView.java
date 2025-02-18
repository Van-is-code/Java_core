package view;

import controller.ATMController;

import java.util.Scanner;

public class ConsoleView {

    private final ATMController atmController;

    public ConsoleView() {
        this.atmController = new ATMController(); // Khởi tạo controller
    }

    // Hiển thị menu và xử lý các lựa chọn
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== HỆ THỐNG ATM MINI ===");
            System.out.println("1. Rút tiền");
            System.out.println("2. Gửi tiền");
            System.out.println("3. Chuyển khoản");
            System.out.println("4. Kiểm tra số dư");
            System.out.println("5. Lịch sử giao dịch");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ ký tự xuống dòng

            switch (choice) {
                case 1 -> handleWithdraw(scanner);
                case 2 -> handleDeposit(scanner);
                case 3 -> handleTransfer(scanner);
                case 4 -> handleBalanceCheck(scanner);
                case 5 -> handleTransactionHistory(scanner);
                case 6 -> {
                    System.out.println("Cảm ơn đã sử dụng hệ thống. Tạm biệt!");
                    isRunning = false;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
        scanner.close();
    }

    // Xử lý rút tiền
    private void handleWithdraw(Scanner scanner) {
        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Nhập số tiền cần rút: ");
        double amount = scanner.nextDouble();
        String message = atmController.withdraw(accountNumber, amount);
        System.out.println(message);
    }

    // Xử lý gửi tiền
    private void handleDeposit(Scanner scanner) {
        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Nhập số tiền cần gửi: ");
        double amount = scanner.nextDouble();
        String message = atmController.deposit(accountNumber, amount);
        System.out.println(message);
    }

    // Xử lý chuyển khoản
    private void handleTransfer(Scanner scanner) {
        System.out.print("Nhập số tài khoản nguồn: ");
        String fromAccountNumber = scanner.nextLine();
        System.out.print("Nhập số tài khoản đích: ");
        String toAccountNumber = scanner.nextLine();
        System.out.print("Nhập số tiền cần chuyển: ");
        double amount = scanner.nextDouble();
        String message = atmController.transfer(fromAccountNumber, toAccountNumber, amount);
        System.out.println(message);
    }

    // Xử lý kiểm tra số dư
    private void handleBalanceCheck(Scanner scanner) {
        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        String message = atmController.getBalance(accountNumber);
        System.out.println(message);
    }

    // Xử lý xem lịch sử giao dịch
    private void handleTransactionHistory(Scanner scanner) {
        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        String message = atmController.getTransactionHistory(accountNumber);
        System.out.println(message);
    }
}
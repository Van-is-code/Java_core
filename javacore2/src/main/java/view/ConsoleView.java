// src/main/java/view/ConsoleView.java

package view;

import controller.EmployeeController;
import model.Employee;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    // Khai báo controller để quản lý nhân viên
    private EmployeeController controller;

    // Khai báo scanner để nhận đầu vào từ người dùng
    private Scanner scanner;

    public ConsoleView() {
        this.controller = new EmployeeController();
        this.scanner = new Scanner(System.in);
    }

    // Hiển thị menu chính
    public void ShowMainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("===== MENU QUAN LY NHAN VIEN =====");
            System.out.println("1. Thêm mới nhân viên");
            System.out.println("2. Xem ds nhân viên theo ArrayList");
            System.out.println("3. Xem ds nhân viên theo LinkedList");
            System.out.println("4. Tìm nhân viên theo ID");
            System.out.println("5. Cập nhật nhân viên");
            System.out.println("6. Xóa nhân viên");
            System.out.println("7. Kích thước của các cấu trúc dữ liệu");
            System.out.println("8. Thoát");
            System.out.println("Chọn chức năng: ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }
            switch (choice) {
                case 1:
                    createEmployeeView();
                    break;
                case 2:
                    showEmployeeArrayList();
                    break;
                case 3:
                    showEmployeeLinkedList();
                    break;
                case 4:
                    findEmployeeByIdView();
                    break;
                case 5:
                    updateEmployeeView();
                    break;
                case 6:
                    deleteEmployeeView();
                    break;
                case 7:
                    DataStructureSize();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    // Tạo mới nhân viên
    private void createEmployeeView() {
        System.out.println("Nhập tên nhân viên: ");
        String name = scanner.nextLine();
        System.out.println("Nhập lương nhân viên: ");
        double salary;
        try {
            salary = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lương không hợp lệ");
            return;
        }
        controller.createEmployee(name, salary);
        System.out.println("Thêm mới nhân viên thành công");
    }

    // Hiển thị danh sách nhân viên theo ArrayList
    private void showEmployeeArrayList() {
        ArrayList<Employee> employees = controller.getAllEmployeeFromArrayList();
        System.out.println("Danh sách nhân viên theo ArrayList:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // Hiển thị danh sách nhân viên theo LinkedList
    private void showEmployeeLinkedList() {
        LinkedList<Employee> employees = controller.getAllEmployeeFromLinkedList();
        System.out.println("Danh sách nhân viên theo LinkedList:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // Tìm nhân viên theo ID
    private void findEmployeeByIdView() {
        System.out.println("Nhập ID nhân viên: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ");
            return;
        }
        Employee employee = controller.getEmployeeById(id);
        if (employee != null) {
            System.out.println("Thông tin nhân viên: " + employee);
        } else {
            System.out.println("Không tìm thấy nhân viên với ID: " + id);
        }
    }

    // Cập nhật thông tin nhân viên
    private void updateEmployeeView() {
        System.out.println("Nhập ID nhân viên cần cập nhật: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ");
            return;
        }
        System.out.println("Nhập tên mới của nhân viên: ");
        String name = scanner.nextLine();
        System.out.println("Nhập lương mới của nhân viên: ");
        double salary;
        try {
            salary = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lương không hợp lệ");
            return;
        }
        boolean success = controller.updateEmployee(id, name, salary);
        if (success) {
            System.out.println("Cập nhật nhân viên thành công");
        } else {
            System.out.println("Không tìm thấy nhân viên với ID: " + id);
        }
    }

    // Xóa nhân viên
    private void deleteEmployeeView() {
        System.out.println("Nhập ID nhân viên cần xóa: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ");
            return;
        }
        boolean success = controller.deleteEmployee(id);
        if (success) {
            System.out.println("Xóa nhân viên thành công");
        } else {
            System.out.println("Không tìm thấy nhân viên với ID: " + id);
        }
    }

    // In kích thước của các cấu trúc dữ liệu
    private void DataStructureSize() {
        controller.printDataStructureSize();
    }
}
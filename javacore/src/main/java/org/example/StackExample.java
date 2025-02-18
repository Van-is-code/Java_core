package org.example;

import java.util.Scanner;
import java.util.Stack;

public class StackExample {
    private static Scanner scanner = new Scanner(System.in);
    private static Stack<String> stack = new Stack<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu Stack:");
            System.out.println("1. Thêm");
            System.out.println("2. Sửa");
            System.out.println("3. Xóa");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Hiển thị Stack");
            System.out.println("6. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addElement();
                    break;
                case 2:
                    editElement();
                    break;
                case 3:
                    deleteElement();
                    break;
                case 4:
                    searchElement();
                    break;
                case 5:
                    displayStack();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void addElement() {
        System.out.print("Nhập tên học sinh để thêm: ");
        String element = scanner.nextLine();
        stack.add(element);
        System.out.println("Học sinh đã được thêm vào Stack.");
        displayStack();
    }

    private static void editElement() {
        System.out.print("Nhập học sinh để sửa: ");
        String oldElement = scanner.nextLine();
        System.out.print("Nhập học sinh mới: ");
        String newElement = scanner.nextLine();
        if (stack.contains(oldElement)) {
            stack.set(stack.indexOf(oldElement), newElement);
            System.out.println("Học sinh đã được sửa trong Stack.");
        } else {
            System.out.println("Không tìm thấy học sinh trong Stack.");
        }
        displayStack();
    }

    private static void deleteElement() {
        System.out.print("Nhập học sinh để xóa: ");
        String element = scanner.nextLine();
        if (stack.remove(element)) {
            System.out.println("Học sinh đã được xóa khỏi Stack.");
        } else {
            System.out.println("Không tìm thấy học sinh trong Stack.");
        }
        displayStack();
    }

    private static void searchElement() {
        System.out.print("Nhập học sinh để tìm kiếm: ");
        String element = scanner.nextLine();
        if (stack.contains(element)) {
            System.out.println("Học sinh được tìm thấy trong Stack.");
        } else {
            System.out.println("Không tìm thấy học sinh trong Stack.");
        }
    }

    private static void displayStack() {
        System.out.println("Stack hiện tại: " + stack);
        System.out.println("Tổng số học sinh: " + stack.size());
    }
}
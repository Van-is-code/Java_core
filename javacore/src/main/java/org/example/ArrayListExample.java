package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListExample {
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu Quản Lí Học Sinh:");
            System.out.println("1. Thêm");
            System.out.println("2. Sửa");
            System.out.println("3. Xóa");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Hiển thị danh sách");
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
                    displayList();
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
        arrayList.add(element);
        System.out.println("Học sinh đã được thêm vào danh sách.");
        displayList();
    }

    private static void editElement() {
        System.out.print("Nhập STT học sinh để sửa: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (index >= 0 && index < arrayList.size()) {
            System.out.print("Nhập tên học sinh mới: ");
            String newElement = scanner.nextLine();
            arrayList.set(index, newElement);
            System.out.println("Học sinh đã được sửa trong danh sách.");
        } else {
            System.out.println("Không tìm thấy học sinh trong danh sách.");
        }
        displayList();
    }

    private static void deleteElement() {
        System.out.print("Nhập STT học sinh để xóa: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (index >= 0 && index < arrayList.size()) {
            arrayList.remove(index);
            System.out.println("Học sinh đã được xóa khỏi danh sách.");
        } else {
            System.out.println("Không tìm thấy học sinh trong danh sách.");
        }
        displayList();
    }

    private static void searchElement() {
        System.out.print("Nhập STT học sinh để tìm kiếm: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (index >= 0 && index < arrayList.size()) {
            System.out.println("Học sinh được tìm thấy: " + arrayList.get(index));
        } else {
            System.out.println("Không tìm thấy học sinh trong danh sách.");
        }
    }

    private static void displayList() {
        System.out.println("Danh sách hiện tại:");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("STT " + i + ": " + arrayList.get(i));
        }
        System.out.println("Tổng số học sinh: " + arrayList.size());
    }
}

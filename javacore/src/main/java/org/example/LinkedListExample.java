package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LinkedListExample {
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> linkedList = new LinkedList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu LinkedList:");
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
        linkedList.add(element);
        System.out.println("Học sinh đã được thêm vào LinkedList.");
        displayList();
    }

    private static void editElement() {
        System.out.print("Nhập học sinh để sửa: ");
        String oldElement = scanner.nextLine();
        System.out.print("Nhập học sinh mới: ");
        String newElement = scanner.nextLine();
        int index = linkedList.indexOf(oldElement);
        if (index != -1) {
            linkedList.set(index, newElement);
            System.out.println("Học sinh đã được sửa trong LinkedList.");
        } else {
            System.out.println("Không tìm thấy học sinh trong LinkedList.");
        }
        displayList();
    }

    private static void deleteElement() {
        System.out.print("Nhập học sinh để xóa: ");
        String element = scanner.nextLine();
        if (linkedList.remove(element)) {
            System.out.println("Học sinh đã được xóa khỏi LinkedList.");
        } else {
            System.out.println("Không tìm thấy học sinh trong LinkedList.");
        }
        displayList();
    }

    private static void searchElement() {
        System.out.print("Nhập học sinh để tìm kiếm: ");
        String element = scanner.nextLine();
        if (linkedList.contains(element)) {
            System.out.println("Học sinh được tìm thấy trong LinkedList.");
        } else {
            System.out.println("Không tìm thấy học sinh trong LinkedList.");
        }
    }

    private static void displayList() {
        System.out.println("LinkedList hiện tại: " + linkedList);
        System.out.println("Tổng số học sinh: " + linkedList.size());
    }
}
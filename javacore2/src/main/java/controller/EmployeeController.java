// src/main/java/controller/EmployeeController.java

package controller;

import model.Employee;
import model.EmployeeService;

import java.util.ArrayList;
import java.util.LinkedList;

public class EmployeeController {
    private EmployeeService service;

    public EmployeeController() {
        this.service = new EmployeeService();
    }

    // Tạo mới một nhân viên
    public void createEmployee(String name, double salary) {
        service.createEmployee(name, salary);
    }

    // Lấy danh sách tất cả nhân viên theo ArrayList
    public ArrayList<Employee> getAllEmployeeFromArrayList() {
        return service.getAllEmployeesArrayList();
    }

    // Lấy danh sách tất cả nhân viên theo LinkedList
    public LinkedList<Employee> getAllEmployeeFromLinkedList() {
        return service.getAllEmployeesLinkedList();
    }

    // Lấy thông tin nhân viên theo ID
    public Employee getEmployeeById(int id) {
        return this.service.getEmployeeById(id);
    }

    // Cập nhật thông tin nhân viên theo ID
    public boolean updateEmployee(int id , String newName, double newSalary) {
        return this.service.updateEmployee(id, newName, newSalary);
    }

    // Xóa nhân viên theo ID
    public boolean deleteEmployee(int id) {
        return service.deleteEmployee(id);
    }

    // In kích thước của các cấu trúc dữ liệu
    public void printDataStructureSize() {
        service.printDataStructureSize();
    }
}
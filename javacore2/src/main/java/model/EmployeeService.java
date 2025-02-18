
// src/main/java/model/EmployeeService.java

        package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeService {
    // Lưu toàn bộ Employee trong một ArrayList
    private ArrayList<Employee> employeesArrayList = new ArrayList<>();

    // Lưu toàn bộ Employee trong một LinkedList
    private LinkedList<Employee> employeesLinkedList = new LinkedList<>();

    // Dùng HashMap để tra cứu nhanh theo ID (key: employeeID, value: Employee)
    private Map<Integer, Employee> employeesMap = new HashMap<>();

    // Tạo ID tự tăng đơn giản nhất
    private int autoIncrementId = 1;

    // Tạo mới một nhân viên và lưu vào các cấu trúc dữ liệu
    public Employee createEmployee(String name, double salary) {
        Employee employee = new Employee(name, autoIncrementId, salary);
        autoIncrementId++;
        employeesArrayList.add(employee);
        employeesLinkedList.add(employee);
        employeesMap.put(employee.getId(), employee);
        return employee;
    }

    // Lấy danh sách tất cả nhân viên theo ArrayList
    public ArrayList<Employee> getAllEmployeesArrayList() {
        return employeesArrayList;
    }

    // Lấy danh sách tất cả nhân viên theo LinkedList
    public LinkedList<Employee> getAllEmployeesLinkedList() {
        return employeesLinkedList;
    }

    // Lấy thông tin nhân viên theo ID
    public Employee getEmployeeById(int id) {
        return employeesMap.get(id);
    }

    // Cập nhật thông tin nhân viên theo ID
    public boolean updateEmployee(int id, String name, double salary) {
        Employee emp = employeesMap.get(id);
        if (emp == null) {
            return false;
        }
        emp.setName(name);
        emp.setSalary(salary);
        return true;
    }

    // Xóa nhân viên theo ID
    public boolean deleteEmployee(int id) {
        Employee emp = employeesMap.remove(id);
        if (emp == null) {
            return false;
        }
        employeesArrayList.remove(emp);
        employeesLinkedList.remove(emp);
        return true;
    }

    // In kích thước của các cấu trúc dữ liệu
    public void printDataStructureSize() {
        System.out.println("ArrayList size: " + employeesArrayList.size());
        System.out.println("LinkedList size: " + employeesLinkedList.size());
        System.out.println("Map size: " + employeesMap.size());
    }
}
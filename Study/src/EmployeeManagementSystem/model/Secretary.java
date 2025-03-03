package EmployeeManagementSystem.model;

// 비서 (Secretary)
public class Secretary extends Employee {
    public Secretary(String eno, String name, int enterYear, int enterMonth, int enterDay, String secno, int salary) {
        super(eno, name, enterYear, enterMonth, enterDay, "Secretary", null, salary);
    }
}

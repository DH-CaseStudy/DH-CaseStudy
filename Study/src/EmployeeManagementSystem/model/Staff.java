package EmployeeManagementSystem.model;

public class Staff extends Employee {
    public Staff(String eno, String name, int enterYear, int enterMonth, int enterDay, String secno, int salary) {
        super(eno, name, enterYear, enterMonth, enterDay, "STAFF", null, salary);
    }
}

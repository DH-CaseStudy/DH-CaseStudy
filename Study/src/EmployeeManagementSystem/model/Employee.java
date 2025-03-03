package EmployeeManagementSystem.model;

import lombok.Data;

@Data
public class Employee {
    private String eno;
    private String name;
    private int enterYear;
    private int enterMonth;
    private int enterDay;
    private String role;
    private String secno;
    private int salary;

    public Employee(String eno, String name, int enterYear, int enterMonth, int enterDay, String role, String secno, int salary) {
        this.eno = eno;
        this.name = name;
        this.enterYear = enterYear;
        this.enterMonth = enterMonth;
        this.enterDay = enterDay;
        this.role = role;
        this.secno = secno;
        this.salary = salary;
    }
}

package EmployeeManagementSystem.model.dao;

import EmployeeManagementSystem.io.EmployeeIO;
import EmployeeManagementSystem.model.Employee;
import EmployeeManagementSystem.model.ObjectIO;

import java.util.List;

//실제로 CRUD 구현 부

public class EmployeeDBIO extends ObjectIO implements EmployeeIO {

    @Override
    public boolean addEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return false;
    }

    @Override
    public Employee getEmployeeById(String eno) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return List.of();
    }

    @Override
    public Employee searchEmployeesByName(String name) {
        return new Employee();
    }

    @Override
    public List<Employee> searchEmployeesByRole(String role) {
        return List.of();
    }

}

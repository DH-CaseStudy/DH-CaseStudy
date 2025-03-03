package EmployeeManagementSystem.io;

import EmployeeManagementSystem.model.Employee;

import java.util.List;

public interface EmployeeSearch {
    Employee searchEmployeesByName(String name);         // 이름으로 직원 검색
    List<Employee> searchEmployeesByRole(String role); // 부서별 직원 검색
}

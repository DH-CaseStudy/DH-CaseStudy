package MainSystem.io;
import MainSystem.model.Employee;

public interface EmployeeInput {
    boolean addEmployee(Employee employee);   // 직원 추가
    boolean updateEmployee(Employee employee); // 직원 정보 수정
}

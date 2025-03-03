package EmployeeManagementSystem;
import EmployeeManagementSystem.controller.EmployeeController;
import EmployeeManagementSystem.model.Staff;
import EmployeeManagementSystem.view.EmployeeView;

public class Main {
    public static void main(String[] args) {
        EmployeeView view = new EmployeeView();
        EmployeeController controller = new EmployeeController(view);

        //입력
        //controller.addEmployee(new Staff("15232", "장항동", 2024, 3, 1, null, 1000000));

        //단일 테이블 데이터 출력
        //controller.getEmployeeById("15232");

        controller.listAllEmployees();
        controller.searchEmployeeByName("장항동");
        controller.searchEmployeesByRole("staff");

    }
}

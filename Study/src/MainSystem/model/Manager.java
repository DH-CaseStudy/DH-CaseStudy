package MainSystem.model;

// 매니저 (Manager)
public class Manager extends Employee {
    public Manager(String eno, String name, int enterYear, int enterMonth, int enterDay, String secno, int salary) {
        super(eno, name, enterYear, enterMonth, enterDay, "Manager", secno, salary);
    }
}

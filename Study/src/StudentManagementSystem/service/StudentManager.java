package StudentManagementSystem.service;
import StudentManagementSystem.dto.StudentDTO;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static StudentManager instance;
    public List<StudentDTO> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public static StudentManager getInstance(){
        if(instance == null){
            instance = new StudentManager();
        }

        return instance;
    }
}

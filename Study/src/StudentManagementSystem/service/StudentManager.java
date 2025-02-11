package StudentManagementSystem.service;
import StudentManagementSystem.dao.StudentDBIO;
import StudentManagementSystem.dto.StudentDTO;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static StudentManager instance;

    private List<StudentDTO> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public static StudentManager getInstance(){
        if(instance == null){
            instance = new StudentManager();
        }

        return instance;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public void loadStudentsFromDB() {
        students.clear();//갱신의 개념으로

        StudentDBIO studentDBIO = new StudentDBIO();
        students = studentDBIO.loadStudentData(); // ✅ 반환된 리스트를 직접 저장

    }

}

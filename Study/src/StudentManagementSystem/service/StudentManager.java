package StudentManagementSystem.service;
import StudentManagementSystem.dao.StudentFileIO;
import StudentManagementSystem.dto.StudentDTO;
import java.util.ArrayList;
import java.util.List;

public class StudentManager extends StudentFileIO {
    private static StudentManager instance;
    private List<StudentDTO> students;
    private static final String File = "studentsDB.txt";

    public StudentManager() {
        students = new ArrayList<>();
    }

    public static StudentManager getInstance(){
        if(instance == null){
            instance = new StudentManager();
        }

        return instance;
    }

    @Override
    public void saveStudentData(List<StudentDTO> students) {

    }

    @Override
    public void loadStudentData(List<StudentDTO> students) {

    }

    @Override
    public void search(String sno) {

    }

    @Override
    public void sort(double average) {

    }

    @Override
    public void sort(String sno) {

    }

    @Override
    public void input(StudentDTO studentDTO) {

    }

    @Override
    public void output() {

    }
}

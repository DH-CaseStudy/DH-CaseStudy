package StudentManagementSystem.dao;
import StudentManagementSystem.dto.StudentDTO;
import StudentManagementSystem.io.StudentIO;
import java.util.List;

public class StudentFileIO implements StudentIO {

    private static final String File = "studentsDB.txt";
    //입력과 출력을 담당하여 File Write
    public StudentFileIO() {
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
    public void sort(float average) {

    }

    @Override
    public void sort(String sno) {
    }

    @Override
    public void input(StudentDTO studentDTO) {

    }

    @Override
    public void output(StudentDTO studentDTO) {

    }
}

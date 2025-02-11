package StudentManagementSystem.service;
import StudentManagementSystem.dao.StudentDBIO;
import StudentManagementSystem.dto.StudentDTO;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static StudentManager instance;

    private List<StudentDTO> students;
    private StudentDTO student;

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

    public StudentDTO getStudent() {
        return student;
    }

    public void sortBySnoFromDB(){
        students.clear();
        this.students = StudentDBIO.getInstance().sortBySno();
    }

    public void sortByTotalFromDB(){
        students.clear();
        this.students = StudentDBIO.getInstance().sortByTotal();
    }

    public void searchStudentFromDB(String sno){
        this.student = StudentDBIO.getInstance().search(sno);
    }

    public void loadStudentsFromDB() {
        students.clear();//갱신하기 위해 담겨있는 데이터를 비운다.
        this.students = StudentDBIO.getInstance().loadStudentData(); // DBIO로 부터 조회를 요청하고 반환된 리스트를 직접 저장

    }

    public void saveStudentFromDB(StudentDTO studentDTO){
        this.student = StudentDBIO.getInstance().saveStudentData(studentDTO);
    }

}

package StudentManagementSystem.service;
import StudentManagementSystem.dao.StudentDBIO;
import StudentManagementSystem.dto.StudentDTO;
import StudentManagementSystem.io.StudentIO;

import java.util.ArrayList;
import java.util.List;

public class StudentManager extends StudentDBIO {
    private static StudentManager instance;

    private List<StudentDTO> students;

    private final StudentDBIO studentDBIO;

    public StudentManager() {
        studentDBIO = new StudentDBIO();
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


    public void sortBySnoFromDB(){
        students.clear();
        studentDBIO.sortBySno();
    }

    public void sortByTotalFromDB(){
        students.clear();
        studentDBIO.sortByTotal();
    }

    public void searchStudentFromDB(String sno){
         studentDBIO.search(sno);

    }

    public void loadStudentsFromDB() {
        students.clear();//갱신하기 위해 담겨있는 데이터를 비운다.
        // DBIO로 부터 조회를 요청하고 반환된 리스트를 직접 저장
        studentDBIO.output();

    }

    public void saveStudentFromDB(StudentDTO studentDTO){
        studentDBIO.input(studentDTO);
    }

}

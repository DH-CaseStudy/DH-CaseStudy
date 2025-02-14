package StudentManagementSystem.service;
import StudentManagementSystem.dao.StudentDBIO;
import StudentManagementSystem.dto.StudentDTO;
import StudentManagementSystem.io.StudentIO;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static StudentManager instance;

    private List<StudentDTO> students;

    private final StudentDBIO studentDBIO;

    public StudentManager() {
        studentDBIO = new StudentDBIO(); //싱글톤이니까 단 하나의 객체.
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
        studentDBIO.sortBySno();
    }

    public void sortByTotalFromDB(){
        studentDBIO.sortByTotal();
    }

    public void searchStudentFromDB(String sno){
         studentDBIO.search(sno);

    }

    public void loadStudentsFromDB() {
        this.students = studentDBIO.output();
//        studentDBIO.output();
    }

    public void saveStudentFromDB(StudentDTO studentDTO){
        studentDBIO.input(studentDTO);
    }

}
